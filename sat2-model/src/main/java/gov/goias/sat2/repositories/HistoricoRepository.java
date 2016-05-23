package gov.goias.sat2.repositories;

import lombok.Getter;
import lombok.Setter;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 *
 * Created by thiago-rs on 4/7/16.
 */
@Repository
public class HistoricoRepository {

    @Autowired
    @Getter
    @Setter
    private DataSource dataSource;

    /**
     * Executa a procedure {call GEN.PKGGEN_INICIALIZA_USER.SPGEN_INICIALIZA_USER(:usuario)}
     * @param usuario
     */
    public void setUsuarioSessao(String usuario){

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(new JdbcTemplate(this.dataSource))
                .withoutProcedureColumnMetaDataAccess()
                .withSchemaName("GEN")
                .withCatalogName("PKGGEN_INICIALIZA_USER")
                .withProcedureName("SPGEN_INICIALIZA_USER");
        jdbcCall.addDeclaredParameter(new SqlParameter("usuario", OracleTypes.VARCHAR));
        jdbcCall.execute(usuario);

    }

}


