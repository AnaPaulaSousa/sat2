package gov.goias.sat2.repositories;

import gov.goias.excecao.InfraException;
import lombok.Getter;
import lombok.Setter;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

/**
 *
 * Created by thiago-rs on 4/7/16.
 */
@Repository
public class HistoricoRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Executa a procedure {call GEN.PKGGEN_INICIALIZA_USER.SPGEN_INICIALIZA_USER(:usuario)}
     * @param usuario
     */
    public void setUsuarioSessao(String usuario){
//        try{
//            final Query q = em.createNativeQuery("{call GEN.PKGGEN_INICIALIZA_USER.SPGEN_INICIALIZA_USER(:usuario)}");
//            q.setParameter("usuario", usuario);
//            q.executeUpdate();
//        }catch(Exception e){
//            throw new InfraException("Houve um erro ao tentar atribuir o usuário na sessão de auditoria",e);
//        }
    }
}