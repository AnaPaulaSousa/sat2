package gov.goias.sat2.test.unit;

//import cucumber.api.CucumberOptions;
//import cucumber.api.java8.Pt;
//import cucumber.api.junit.Cucumber;
//import gov.goias.historico.aspecto.HistoricoAspectoFuncional;
//import gov.goias.sat2.conf.HistoricoRequestFilter;
//import gov.goias.sat2.conf.auth.CasUserExtractor;
//import gov.goias.sat2.repositories.HistoricoRepository;
//import org.jasig.cas.client.authentication.AttributePrincipal;
//import org.jasig.cas.client.util.AbstractCasFilter;
//import org.jasig.cas.client.validation.Assertion;
//import org.junit.runner.RunWith;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.ws.rs.container.ContainerRequestContext;
//
//import java.io.IOException;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

/**
 * Created by thiago-rs on 4/7/16.
 */
//@RunWith(Cucumber.class)
//@CucumberOptions(monochrome = true,
//        features = "classpath:features/unit/historico_funcional_cas.feature", //feature:x:n -> x,n = numero das linhas
//        format = {"pretty",
//                "json:target/target_json/cucumber.json",
//                "junit:target/target_junit/cucumber.xml",
//                "html:target/cucumber"})
public class HistoricoFuncionalCasTest {//implements Pt {
//
//    private MockFilter filter = new MockFilter();
//
//    public HistoricoFuncionalCasTest() {
//
//        Dado("que o usuário (.*) foi autenticado pelo CAS", (String usuario) -> {
//
//            final HttpServletRequest req = mock(HttpServletRequest.class);
//            final HttpSession session = mock(HttpSession.class);
//            final Assertion assertion = mock(Assertion.class);
//            final AttributePrincipal principal = mock(AttributePrincipal.class);
//
//            when(req.getSession()).thenReturn(session);
//            when(session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION)).thenReturn(assertion);
//            when(assertion.getPrincipal()).thenReturn(principal);
//            when(principal.getName()).thenReturn(usuario);
//
//            filter.setRequest(req);
//
//        });
//
//        Quando("o usuário (.*) altera o domínio da aplicação", (String usuario) -> {
//
//            try {
//                filter.filter(mock(ContainerRequestContext.class));
//                assertTrue(true);
//            } catch (IOException e) {
//                fail();
//            }
//
//        });
//
//        Entao("o historico de banco é gerado para usuario (.*)", (String usuario) -> {
//            assertEquals(filter.getHistorico().s.get(), usuario);
//        });
//
//    }
//
//
//    class MockFilter extends HistoricoRequestFilter {
//
//        public MockFilter(){
//           this.historicoRepository = mock(HistoricoRepository.class);
//           this.historico = mock(HistoricoAspectoFuncional.class);
//           this.userExtractor = new CasUserExtractor();
//        }
//
//        public void setRequest(HttpServletRequest servletRequest){
//            this.servletRequest = servletRequest;
//        }
//
//        public HistoricoAspectoFuncional getHistorico(){
//            return this.historico;
//        }
//
//    }
}
