package gov.goias.mtb.repositories;

import com.google.common.collect.Maps;
import gov.goias.excecao.InfraException;
import gov.goias.historico.annotation.Historico;
import gov.goias.mtb.entities.Sistema;
import gov.goias.mtb.mappers.SistemaRowMapper;
import gov.goias.mtb.util.MsgModel;
import javaslang.control.Try;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * Repositorios para Sistema
 * Created by thiago-rs on 3/17/16.
 */

@Repository
public class SistemaRepository extends PasRepository {

    @Override
    public String getTableName() {
        return SistemaRowMapper.tableName;
    }

    @Override
    public String[] getInsertColumnNames() {
        return SistemaRowMapper.insertColumnNames;
    }

    /**
     * Obtém Sistema pela Sigla
     * @param siglaSistema
     * @return Sistema
     * @throws DataAccessException
     */
    public Optional<Sistema> findBySigla(String siglaSistema) {

        final String sql= new StringBuilder()
                .append(" SELECT SIST_IDEN, SIST_SIGL, SIST_DESC, SIST_INFO_CONEXAO, SIST_NOME_APLICACAO")
                .append("  SIST_INFO_MENSAGEM, SIST_INFO_LOGOTIPO, SIST_STAT, SIST_EMAIL ")
                .append("  FROM SISTEMAS_SERVICO ")
                .append("  WHERE SIST_SIGL = :sist_sigl ").toString();

        try{

            final List<Sistema> s = npJdbcTemplate.query(
                    sql, new MapSqlParameterSource().addValue("sist_sigl", siglaSistema),
                    new SistemaRowMapper());

            return s.stream().findFirst();

        }catch (EmptyResultDataAccessException empty){
            LOGGER.debug(empty);
            return Optional.empty();
        }catch (Exception e){
            LOGGER.debug(e);
            throw new InfraException(e);
        }

    }

    /**
     * Inclui um novo Sistema
     * @param s
     * @return Sistema incluído
     * @throws DataAccessException
     */
    @Historico
    @Transactional(rollbackFor = {Throwable.class})
    public Optional<Sistema> save(Sistema s) {

        int lastId = this.npJdbcTemplate.queryForObject(
                "SELECT SIST_IDEN FROM SISTEMAS_SERVICO WHERE SIST_IDEN IN (SELECT MAX(SIST_IDEN) FROM SISTEMAS_SERVICO)",
                Maps.newHashMap(),
                Integer.class);

        s.setId(lastId+1);

        return Try.of(() -> insert.execute(SistemaRowMapper.toMap(s)))
                .map(x -> Optional.of(s))
                .onFailure(e -> new InfraException(e))
                .get();
    }

    /**
     * Exclui um Sistema
     * @param s
     * @return true se registros foram alterados
     * @throws DataAccessException
     */
    @Historico
    @Transactional(rollbackFor = {Throwable.class})
    public boolean delete(Sistema s) {
        return delete(requireNonNull(s, MsgModel.SISTEMA_NOT_NULL).getSigla());
    }


    /**
     * Exclui um Sistema por Id
     * @param s - Sigla do Sistema
     * @return true se registros foram alterados
     * @throws DataAccessException
     */
    @Historico
    @Transactional(rollbackFor = {Throwable.class})
    public boolean delete(String s) {

        final HashMap<String,String> params = new HashMap<String,String>(){{
            put("sigla",requireNonNull(s, MsgModel.SISTEMA_NOT_NULL));
        }};
        final String sql = String.format("DELETE FROM %s WHERE SIST_SIGL = :sigla",getTableName());

        return Try.of(() -> npJdbcTemplate.update(sql,params))
                .map(x -> x > 0)
                .onFailure(e -> new InfraException(e))
                .get();
    }

    /**
     * Obtém todos os sistemas cadastrados no Portal
     * @return
     * @throws DataAccessException
     */
    public Optional<List<Sistema>> findByUsuario(String usuario) {

        final String sql = new StringBuilder()
        .append(" SELECT DISTINCT SIST_IDEN, SIST_SIGL, SIST_STAT, SIST_DESC, SIST_EMAIL, ")
        .append("  SIST_INFO_CONEXAO, SIST_INFO_LOGOTIPO, SIST_INFO_MENSAGEM, SIST_NOME_APLICACAO ")
        .append("    FROM PERFIS_SISTEMA ")
        .append("    LEFT JOIN ACESSOS_USUARIO")
        .append("      ON ACESSOS_USUARIO.ACES_PERF_CODG = PERFIS_SISTEMA.PERF_CODG ")
        .append("    LEFT JOIN SISTEMAS_SERVICO")
        .append("      ON PERFIS_SISTEMA.PERF_SIST_SIGL = SISTEMAS_SERVICO.SIST_SIGL ")
        .append("    WHERE ACESSOS_USUARIO.ACES_USUA_CODG = :usuario ").toString();

        try{

            final List<Sistema> s = npJdbcTemplate.query(
                    sql, new MapSqlParameterSource().addValue("usuario", usuario),
                    new SistemaRowMapper());

            return s.isEmpty()?Optional.empty():Optional.of(s);

        }catch (EmptyResultDataAccessException empty){
            LOGGER.debug(empty);
            return Optional.empty();
        }catch (Exception e){
            LOGGER.debug(e);
            throw new InfraException(e);
        }

    }


    /**
     * Obtém todos os sistemas cadastrados no Portal
     * @return
     * @throws DataAccessException
     */
    public Optional<List<Sistema>> findAll() {

        final String sql = "SELECT * FROM SISTEMAS_SERVICOS WHERE SIST_STAT = :status";

        try{

            final List<Sistema> s = npJdbcTemplate.query(
                    sql, new SistemaRowMapper());

            return s.isEmpty()?Optional.empty():Optional.of(s);

        }catch (EmptyResultDataAccessException empty){
            LOGGER.debug(empty);
            return Optional.empty();
        }catch (Exception e){
            LOGGER.debug(e);
            throw new InfraException(e);
        }

    }

}
