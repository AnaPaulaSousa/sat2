package gov.goias.mtb.mappers;

import gov.goias.mtb.entities.PessoaFisica;
import gov.goias.mtb.entities.Sexo;
import gov.goias.mtb.entities.StatusUsuario;
import gov.goias.mtb.entities.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by thiago-rs on 3/18/16.
 */
public class UsuarioRowMapper implements RowMapper<Usuario> {

    public static final String tableName = "USUARIOS_SISTEMA";

    public static final String[] insertColumnNames = new String[]{
            "USUA_CODG","USUA_PESS_ID","USUA_STAT","USUA_INFO_SENHA",
            "USUA_INDI_SUPER_USUARIO","USUA_EMAIL"
    };

    public static final String USUA_REL_PERFIL = "ACESSOS_USUARIO";

    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Usuario u = new Usuario();
        final PessoaFisica pf = new PessoaFisica();

        pf.setId(rs.getInt("PFIS_IDEN"));
        pf.setNome(rs.getString("PFIS_NOME"));
        pf.setNomeMae(rs.getString("PFIS_NOME_MAE"));
        pf.setNomePai(rs.getString("PFIS_NOME_PAI"));
        pf.setNascimento(rs.getDate("PFIS_DATA_NASC"));
        pf.setSexo(Sexo.valueOf(rs.getString("PFIS_SIGL_SEXO")));
        u.setPessoa(pf);

        u.setNomeUsuario(rs.getString("USUA_CODG"));
        u.setStatus(StatusUsuario.valueOf(rs.getString("USUA_STAT")));
        u.setSenha(rs.getString("USUA_INFO_SENHA"));
        u.setAtivacao(rs.getDate("USUA_DATA_ATIVACAO"));
        u.setSuperUsuario("S".equalsIgnoreCase(rs.getString("USUA_INDI_SUPER_USUARIO")));
        u.setEmail(rs.getString("USUA_EMAIL"));
        return u;
    }
}
