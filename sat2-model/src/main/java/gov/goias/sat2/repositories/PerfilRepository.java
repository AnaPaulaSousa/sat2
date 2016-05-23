package gov.goias.sat2.repositories;

import com.google.common.collect.Maps;
import gov.goias.excecao.InfraException;
import gov.goias.historico.annotation.Historico;
import gov.goias.sat2.entities.Perfil;
import gov.goias.sat2.entities.Sistema;
import gov.goias.sat2.entities.Usuario;
import gov.goias.sat2.mappers.AcessosUsuarioRowMapper;
import gov.goias.sat2.mappers.PerfilRowMapper;
import gov.goias.sat2.util.MsgModel;
import javaslang.control.Try;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static gov.goias.sat2.mappers.PerfilRowMapper.buildQueryByUsuarioAndByUsuarioSistema;
import static java.util.Objects.requireNonNull;
import static javaslang.API.Case;
import static javaslang.API.Match;

/**
 * Repositorio para perfil
 * Created by thiago-rs on 3/23/16.
 */
@Repository
public class PerfilRepository extends PasRepository {

    @Override
    public String getTableName() {
        return PerfilRowMapper.tableName;
    }

    @Override
    public String[] getInsertColumnNames() {
        return PerfilRowMapper.insertColumnNames;
    }

    /**
     * Inclui um novo Perfil
     * @param p
     * @return perfil incluído
     */
    @Historico
    @Transactional(rollbackFor = {Throwable.class})
    public Optional<Perfil> save(Perfil p) {

        int lastId = this.npJdbcTemplate.queryForObject(
                "SELECT PERF_CODG FROM PERFIS_SISTEMA WHERE PERF_CODG IN (SELECT MAX(PERF_CODG) FROM PERFIS_SISTEMA)",
                Maps.newHashMap(),
                Integer.class);

        p.setCodigo(lastId+1);

        return Try.of(() -> insert.execute(PerfilRowMapper.toMap(p)))
                .map(x -> Optional.of(p))
                .onFailure(e -> new InfraException(e))
                .get();
    }


    /**
     * Exclui um Perfil
     * @param p
     * @return true se registros foram alterados
     */
    @Historico
    @Transactional(rollbackFor = {Throwable.class})
    public boolean delete(Perfil p) {
        return delete(requireNonNull(p, MsgModel.PERFIL_NOT_NULL).getCodigo());
    }


    /**
     * Exclui um Perfil por codigo
     * @param codigo - Codigo do Perfil
     * @return true se registros foram alterados
     */
    @Historico
    @Transactional(rollbackFor = {Throwable.class})
    public boolean delete(Integer codigo) {

        final HashMap<String,Integer> params = new HashMap<String,Integer>(){{
            put("codigo",requireNonNull(codigo, MsgModel.PERFIL_NOT_NULL));
        }};
        final String sql = String.format("DELETE FROM %s WHERE PERF_CODG = :codigo",getTableName());

        return Try.of(() -> npJdbcTemplate.update(sql,params))
                .map(x -> x > 0)
                .onFailure(e -> new InfraException(e))
                .get();
    }

    /**
     * Exclui todos os Perfis de um Sistema
     * @param s - Sistema que tera os perfis removidos
     * @return true se registros foram alterados
     */
    @Historico
    @Transactional(rollbackFor = {Throwable.class})
    public boolean deleteAllFrom(Sistema s) {

        final HashMap<String,String> params = new HashMap<String,String>(){{
            put("sigla", requireNonNull(s, MsgModel.SISTEMA_NOT_NULL).getSigla());
        }};
        final String sql = String.format("DELETE FROM %s WHERE PERF_SIST_SIGL = :sigla",getTableName());

        return Try.of(() -> npJdbcTemplate.update(sql,params))
               .map(x -> x > 0)
               .onFailure(e -> new InfraException(e))
               .get();

    }

    /**
     * Obtem lista de perfis por usuario
     * @param u usuario para listar perfis
     * @return
     * @throws DataAccessException
     */
    public Optional<List<Perfil>> findByUsuario(Usuario u) {

        final String sql = buildQueryByUsuarioAndByUsuarioSistema(false).toString();

        final Map<String,String> params = Collections.singletonMap(
                "usuario",
                requireNonNull(u, MsgModel.USUARIO_NOT_NULL).getNomeUsuario());

        try{

            return npJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new AcessosUsuarioRowMapper());

        }catch (EmptyResultDataAccessException empty){
            return Optional.empty();
        }catch (Exception e){
            throw new InfraException(e);
        }
    }



    /**
     * Obtem lista de perfis por usuario
     * @param u usuario para listar perfis
     * @param s sistema para filtrar perfis
     * @return
     * @throws DataAccessException
     */
    public Optional<List<Perfil>> findByUsuarioAndSistema(Usuario u, Sistema s) {

        final String sql = buildQueryByUsuarioAndByUsuarioSistema(true).toString();

        final HashMap<String,String> params = new HashMap<String,String>(){{
            put("usuario", requireNonNull(u, MsgModel.USUARIO_NOT_NULL).getNomeUsuario());
            put("sistema", requireNonNull(s, MsgModel.SISTEMA_NOT_NULL).getSigla());
        }};

        try{

            return npJdbcTemplate.queryForObject(
                    sql.toString(),
                    params,
                    new AcessosUsuarioRowMapper());

        }catch (EmptyResultDataAccessException empty){
            return Optional.empty();
        }catch (Exception e){
            throw new InfraException(e);
        }

    }

}
