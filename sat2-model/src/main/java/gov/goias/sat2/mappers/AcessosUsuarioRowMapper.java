package gov.goias.sat2.mappers;

import gov.goias.sat2.entities.Perfil;
import gov.goias.sat2.entities.Sistema;
import gov.goias.sat2.entities.StatusSistema;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by thiago-rs on 4/6/16.
 */
public class AcessosUsuarioRowMapper implements RowMapper<Optional<List<Perfil>>> {

    @Override
    public Optional<List<Perfil>> mapRow(ResultSet rs, int rowNum) throws SQLException {

        final List<Perfil> l = new ArrayList<>();

        while(rs.next()){

            final Sistema s = new Sistema();
            s.setId(rs.getInt("SIST_IDEN"));
            s.setSigla(rs.getString("SIST_SIGL"));
            s.setDescricao(rs.getString("SIST_DESC"));
            s.setEmail(rs.getString("SIST_EMAIL"));
            s.setConexao(rs.getString("SIST_INFO_CONEXAO"));
            s.setStatus(StatusSistema.valueOf(rs.getString("SIST_STAT")));
            s.setMensagem(rs.getString("SIST_INFO_MENSAGEM"));
            s.setLogotipo(rs.getString("SIST_INFO_LOGOTIPO"));
            s.setNomeAplicacao(rs.getString("SIST_NOME_APLICACAO"));

            final Perfil p = new Perfil();
            p.setCodigo(rs.getInt("PERF_CODG"));
            p.setDescricao(rs.getString("PERF_DESC"));
            p.setServico("S".equalsIgnoreCase(rs.getString("PERF_INDI_SERVICO")));
            p.setTipoPerfil(rs.getString("PERF_TIPO_PERFIL"));
            p.setSistema(s);

            l.add(p);

        }

        return l.isEmpty()? Optional.empty() : Optional.of(l);
    }
}

