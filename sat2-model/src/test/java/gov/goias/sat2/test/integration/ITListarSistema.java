package gov.goias.sat2.test.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.DataTable;
import cucumber.api.java8.Pt;
import cucumber.api.junit.Cucumber;
import gov.goias.sat2.entities.Sistema;
import gov.goias.sat2.repositories.HistoricoRepository;
import gov.goias.sat2.services.AlunoService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.function.Function;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(Cucumber.class)
//@CucumberOptions(monochrome = true,
//        features = "classpath:features/integration/listar_acesso_sistema.feature", //feature:x:n -> x,n = numero das linhas
//        format = {"pretty",
//                "json:target/target_json/cucumber.json",
//                "junit:target/target_junit/cucumber.xml",
//                "html:target/cucumber"})
public class ITListarSistema {//implements Pt {
//
//    private String dropUser = null;
//
//    private Sistema sistema;
//    private ApplicationContext context;
//
//    private AlunoService sistemaService;
//    private SistemaRepository sistemaRepository;
//    private PerfilRepository perfilRepository;
//    private UsuarioRepository usuarioRepo;
//    private HistoricoRepository historicoRepository;
//
//    private HttpSession session;
//    private Assertion assertion;
//    private AttributePrincipal principal;
//    private Map<String,String> acessos;
//
//
//    private List<Function> drops = new ArrayList<>();
//
//
//    public ITListarSistema() {
//        starApplicationContext();
//        mockObjects();
//        usuarioIdentificadoAcessaFuncionalidade();
//
//        portalListaAcessosUsuarios();
//
//        usuarioNaoIdentificadoAcessaFuncionalidade();
//
////        Before(new String[]{"@drop"}, () -> drop());
//        After(new String[]{"@drop"}, () -> drop());
//
//    }
//
//    private void drop(){
//
//        historicoRepository.setUsuarioSessao(dropUser);
//
//        try{
//
//            acessos.forEach((k,v) -> {
//
//                final Sistema s = new Sistema();
//                s.setSigla(k);
//
//                final Usuario u = new Usuario();
//                u.setNomeUsuario(dropUser);
//
//                usuarioRepo.removerAcessos(u);
//                perfilRepository.deleteAllFrom(s);
//                sistemaRepository.delete(k);
//
//            });
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    private void mockObjects() {
//        principal = mock(AttributePrincipal.class);
//        assertion = mock(Assertion.class);
//        session = mock(HttpSession.class);
//
//        when(assertion.getPrincipal()).thenReturn(principal);
//        when(session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION)).thenReturn(assertion);
//
//        System.setProperty("gov.goias.portal.ambiente","intra");
//
//    }
//
//    private void starApplicationContext(){
//        try{
//            context = new ClassPathXmlApplicationContext("applicationContext.xml");
//            sistemaRepository = context.getBean(SistemaRepository.class);
//            usuarioRepo = context.getBean(UsuarioRepository.class);
//            perfilRepository = context.getBean(PerfilRepository.class);
//            historicoRepository = context.getBean(HistoricoRepository.class);
//            sistemaService = context.getBean(AlunoService.class);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    private void usuarioIdentificadoAcessaFuncionalidade(){
//        Dado("que o usuário (.*) está previamente autenticado", (String usuario) -> {
//            final Assertion a = (Assertion) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
//            when(principal.getName()).thenReturn(usuario);
//            assertTrue(usuario.equals(a.getPrincipal().getName()));
//        });
//
//        Entao("o usuário (.*) é identificado pelo Portal", (String usuario) -> {
//            final Optional<Usuario> us =
//                    usuarioRepo.findByNomeUsuario(usuario)
//                            .filter(u -> usuario.equalsIgnoreCase(u.getNomeUsuario()));
//
//            assertTrue(us.isPresent());
//        });
//    }
//
//    private void usuarioNaoIdentificadoAcessaFuncionalidade() {
//        Dado("que o usuário (.*) está indevidamente autenticado", (String usuario) -> {
//            final Assertion a = (Assertion) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
//            assertTrue(!usuario.equals(a.getPrincipal().getName()));
//        });
//
//        Entao("o Portal não identifica o usuário (.*)", (String usuario) -> {
//            final Optional<Usuario> us =
//                    usuarioRepo.findByNomeUsuario(usuario)
//                            .filter(u -> usuario.equalsIgnoreCase(u.getNomeUsuario()));
//
//            assertTrue(!us.isPresent());
//        });
//    }
//
//
//    private void portalListaAcessosUsuarios() {
//
//        Dado("que foi previamente concedido ao usuário (.*) os acessos dos sistemas",
//                (String usuario, DataTable data) -> {
//
//            try{
//
//                dropUser = usuario;
//                acessos = data.asMap(String.class,String.class);
//
//                final Usuario u =
//                        usuarioRepo.findByNomeUsuario(usuario)
//                                .orElseThrow(() -> new Exception("Usuario não existe"));
//
//                acessos.forEach((k,v) -> {
//
//                    final Sistema s = new Sistema();
//                    s.setSigla(k);
//                    s.setDescricao("Sistema "+k);
//                    s.setMensagem("Mensagem");
//                    s.setConexao("http://localhost");
//                    s.setStatus(StatusSistema.A);
//                    s.setEmail("email@dominio.com");
//
//                    assertTrue(sistemaRepository.save(s).isPresent());
//
//                    final Perfil p = new Perfil();
//                    p.setSistema(s);
//                    p.setDescricao(v);
//                    p.setServico(false);
//
//                    assertTrue(perfilRepository.save(p).isPresent());
//
//                    usuarioRepo.adicionarPerfil(p,u);
//
//                });
//
//            }catch (Exception e){
//                fail(e.getMessage());
//            }
//        });
//
//        Quando("usuário (.*) acessa o Portal de forma autenticada", (String usuario) -> {
//            final Optional<Usuario> us =
//                    usuarioRepo.findByNomeUsuario(usuario)
//                            .filter(u -> usuario.equalsIgnoreCase(u.getNomeUsuario()));
//
//            assertTrue(us.isPresent());
//
//        });
//
//        Entao("o Portal obtém uma lista contendo todos sistemas que o usuário (.*) possui acesso",
//                (String usuario, DataTable data) -> {
//
//            final List<String> sistemas = data.asList(String.class);
//
//            final List<Sistema> acessos = sistemaService.listarAcessosPorUsuarioAmbiente(usuario)
//                    .orElse(Collections.emptyList());
//
//            assertTrue(!acessos.isEmpty() && acessos.stream()
//                .allMatch(s -> sistemas.contains(s.getSigla())));
//
//        });
//
//        E("filtra os sistemas listados de acordo com ambiente especificado pela RN18", () -> {
//              //FIXME: Domínio deve ser alterador para suportar funcionalidade
//              assertTrue(true);
//         });
//
//    }

}