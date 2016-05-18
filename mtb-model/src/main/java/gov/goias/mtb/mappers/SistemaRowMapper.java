package gov.goias.mtb.mappers;

import gov.goias.mtb.entities.Sistema;
import gov.goias.mtb.entities.StatusSistema;
import gov.goias.mtb.util.MsgModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Objects.requireNonNull;

/**
 * Created by thiago-rs on 3/18/16.
 */
public class SistemaRowMapper implements RowMapper<Sistema> {

    private boolean isList;

    public static final String tableName = "SISTEMAS_SERVICO";

    public static final String[] insertColumnNames = new String[]{
            "SIST_SIGL", "SIST_DESC", "SIST_INFO_CONEXAO", "SIST_INFO_MENSAGEM",
            "SIST_INFO_LOGOTIPO", "SIST_STAT", "SIST_NOME_APLICACAO", "SIST_EMAIL"
    };

    @Override
    public Sistema mapRow(ResultSet rs, int rowNum) throws SQLException {
        return toSistema(rs);
    }

    private Sistema toSistema(ResultSet rs) throws SQLException {
        final Sistema s = new Sistema();
        s.setId(rs.getInt("SIST_IDEN"));
        s.setSigla(rs.getString("SIST_SIGL"));
        s.setDescricao(rs.getString("SIST_DESC"));
        s.setEmail(rs.getString("SIST_EMAIL"));
        s.setConexao(rs.getString("SIST_INFO_CONEXAO"));
        s.setLogotipo(rs.getString("SIST_INFO_LOGOTIPO"));
        s.setMensagem(rs.getString("SIST_INFO_MENSAGEM"));
        s.setStatus(StatusSistema.valueOf(rs.getString("SIST_STAT")));
        s.setNomeAplicacao(rs.getString("SIST_NOME_APLICACAO"));
        return s;
    }


    public static Map<String, Object> toMap(Sistema s) {

        requireNonNull(s, MsgModel.SISTEMA_NOT_NULL);

        return new HashMap<String, Object>() {{
            put("SIST_SIGL", s.getSigla());
            put("SIST_IDEN", s.getId());
            put("SIST_DESC", s.getDescricao());
            put("SIST_INFO_CONEXAO", s.getConexao());
            put("SIST_INFO_MENSAGEM", s.getMensagem());
            put("SIST_INFO_LOGOTIPO", s.getLogotipo());
            put("SIST_STAT", s.getStatus().name());
            put("SIST_EMAIL", s.getEmail());
        }};

    }
}