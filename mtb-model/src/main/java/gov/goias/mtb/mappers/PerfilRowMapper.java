package gov.goias.mtb.mappers;

import gov.goias.mtb.entities.Perfil;
import gov.goias.mtb.util.MsgModel;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Created by thiago-rs on 4/7/16.
 */
public class PerfilRowMapper {

    public static final String tableName = "PERFIS_SISTEMA";

    public static final String[] insertColumnNames = new String[]{
            "PERF_CODG","PERF_SIST_SIGL","PERF_DESC",
            "PERF_TIPO_PERFIL","PERF_INDI_SERVICO"
    };

    public static StringBuilder buildQueryByUsuarioAndByUsuarioSistema(boolean usuarioSistema){

        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT PERF_CODG, PERF_DESC, PERF_TIPO_PERFIL, PERF_INDI_SERVICO, ");
        sql.append("        SIST_IDEN, SIST_SIGL, SIST_STAT, SIST_DESC, SIST_EMAIL, SIST_INFO_CONEXAO, ");
        sql.append("        SIST_INFO_LOGOTIPO, SIST_INFO_MENSAGEM, SIST_NOME_APLICACAO ");
        sql.append("  FROM PERFIS_SISTEMA ");
        sql.append("  LEFT JOIN ACESSOS_USUARIO");
        sql.append("    ON ACESSOS_USUARIO.ACES_PERF_CODG = PERFIS_SISTEMA.PERF_CODG ");
        sql.append("  LEFT JOIN SISTEMAS_SERVICO");
        sql.append("    ON PERFIS_SISTEMA.PERF_SIST_SIGL = SISTEMAS_SERVICO.SIST_SIGL ");
        sql.append("  WHERE ACESSOS_USUARIO.ACES_USUA_CODG = :usuario ");

        if(usuarioSistema) sql.append("  AND PERFIS_SISTEMA.PERF_SIST_SIGL = :sistema");

        return sql;
    }

    public static Map<String, Object> toMap(Perfil p){

        requireNonNull(p, MsgModel.PERFIL_NOT_NULL);

        return new HashMap<String, Object>(){{
            put("PERF_CODG",p.getCodigo());
            put("PERF_SIST_SIGL", p.getSistema().getSigla());
            put("PERF_DESC", p.getDescricao());
            put("PERF_TIPO_PERFIL", p.getTipoPerfil());
            put("PERF_INDI_SERVICO", p.isServico());
        }};

    }

}
