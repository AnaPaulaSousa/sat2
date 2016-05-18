package gov.goias.mtb.repositories;

import gov.goias.excecao.InfraException;
import gov.goias.historico.annotation.Historico;
import gov.goias.mtb.entities.Perfil;
import gov.goias.mtb.entities.Usuario;
import gov.goias.mtb.mappers.UsuarioRowMapper;
import gov.goias.mtb.util.MsgModel;
import javaslang.control.Try;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.requireNonNull;

/**
 * Created by thiago-rs on 3/18/16.
 */
@Repository
public class UsuarioRepository extends PasRepository {

    @Override
    public String getTableName() {
        return UsuarioRowMapper.tableName;
    }

    @Override
    public String[] getInsertColumnNames() {
        return UsuarioRowMapper.insertColumnNames;
    }

    /**
     * Obtém usuário por código, podendo esser ser
     * nome de usuário ou cpf
     * @param nomeUsuario
     * @return Usuario
     * @throws DataAccessException
     */
    public Optional<Usuario> findByNomeUsuario(String nomeUsuario) {

        final String sql = new StringBuilder()
                .append(" SELECT USUA_CODG, USUA_STAT, USUA_INFO_SENHA,  ")
                .append("  USUA_DATA_ATIVACAO, USUA_INDI_SUPER_USUARIO, USUA_EMAIL, ")
                .append("  PFIS_IDEN, PFIS_NOME, PFIS_NOME_MAE, PFIS_NOME_PAI, ")
                .append("  PFIS_DATA_NASC, PFIS_NUMR_CPF, PFIS_NUMR_RG, PFIS_SIGL_SEXO ")
                .append("  FROM USUARIOS_SISTEMA LEFT JOIN PESSOAS_FISICAS ")
                .append("  ON USUARIOS_SISTEMA.USUA_PESS_ID = PESSOAS_FISICAS.PFIS_IDEN ")
                .append("  WHERE USUA_CODG = :usua_codg ").toString();

        try{

            return Optional.of(npJdbcTemplate.queryForObject(
                    sql, Collections.singletonMap("usua_codg", nomeUsuario),
                    new UsuarioRowMapper()));

        }catch (EmptyResultDataAccessException empty){
            LOGGER.debug(empty);
            return Optional.empty();
        }catch (Exception e){
            LOGGER.debug(e);
            throw new InfraException(e);
        }

    }

    /**
     * Adicionar um perfil ao usuário
     * @param p - perfil
     * @param u - usuário que receberá o perfil
     * @throws DataAccessException
     */
    @Historico
    @Transactional(rollbackFor = {Throwable.class})
    public void adicionarPerfil(Perfil p, Usuario u) {

        final String sql =
                String.format(
                        "INSERT INTO %s (ACES_USUA_CODG, ACES_PERF_CODG, ACES_INFO_REPASSE)" +
                                " values(:ACES_USUA_CODG, :ACES_PERF_CODG, :ACES_INFO_REPASSE)",
                        UsuarioRowMapper.USUA_REL_PERFIL);

        final Map<String, Object> params = new HashMap<String, Object>(){{
            put("ACES_PERF_CODG", p.getCodigo());
            put("ACES_USUA_CODG", u.getNomeUsuario());
            put("ACES_INFO_REPASSE", "N");
        }};

        Try.of(() -> getTemplate().update(sql, params))
                .onFailure(e -> new InfraException(e));

    }


    /**
     * Remove todos os acessos do usuário
     * @param u - Usuário que tera acesos removidos
     * @return true caso registros foram removidos
     * @throws DataAccessException
     */
    @Historico
    @Transactional(rollbackFor = {Throwable.class})
    public boolean removerAcessos(Usuario u) {

        requireNonNull(u, MsgModel.USUARIO_NOT_NULL);

        if(isNullOrEmpty(u.getNomeUsuario())){
            throw new RuntimeException(MsgModel.USUARIO_NOT_NULL);
        }

        final HashMap<String,String> params = new HashMap<String,String>(){{
            put("usuario", u.getNomeUsuario());
        }};
        final String sql = String.format("DELETE FROM %s WHERE ACES_USUA_CODG = :usuario",
                UsuarioRowMapper.USUA_REL_PERFIL);

        return Try.of(() -> npJdbcTemplate.update(sql,params))
                .map(x -> x > 0)
                .onFailure(e -> new InfraException(e))
                .get();

    }
}
