package gov.goias.sat2.test.integration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cucumber.api.CucumberOptions;
import cucumber.api.DataTable;
import cucumber.api.java8.Pt;
import cucumber.api.junit.Cucumber;
import gov.goias.sat2.services.AlunoService;
import gov.goias.sat2.test.MyCasUserExtractor;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.runner.RunWith;
import org.springframework.web.context.ContextLoaderListener;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by thiago-rs on 4/12/16.
 */
//@RunWith(Cucumber.class)
//@CucumberOptions(monochrome = true,
//        features = "classpath:features/integration/listar_acesso_sistemas.feature", //feature:x:n -> x,n = numero das linhas
//        format = {"pretty",
//                "json:target/target_json/cucumber.json",
//                "junit:target/target_junit/cucumber.xml",
//                "html:target/cucumber"})
public class ITAppQueries {//extends JerseyTest implements Pt {
//
//    private static AlunoService sistemas;
//
//    private static MyCasUserExtractor userExtractor = new MyCasUserExtractor();
//
//    private String responseString = "";
//    private Integer responseStatus = 500;
//
//    public ITAppQueries(){
//        super();
//
//        Before(() -> {
//            try { setUp(); } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//        After(() -> {
//            try { tearDown(); } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//        fluxoPadrao();
//        fluxoAlternativo();
//        fluxoExcecao();
//
//    }
//
//    private void fluxoPadrao() {
//        Dado("que o usuário (.*) está autenticado", (String usuario) -> {
//            assertEquals(userExtractor.extractUser().get(),usuario);
//        });
//
//        E("que foi previamente concedido ao usuário (.*) acesso aos sistemas", (String usuario, DataTable data) -> {
//            when(sistemas.listarAcessosPorUsuarioAmbiente(usuario)).thenReturn(Optional.of(data.asList(Sistema.class)));
//        });
//
//        Quando("a apresentação solicita a lista de sistemas para o usuário THIAGO",() -> {
//            final Response r = target("apps/home").request().get(Response.class);
//            responseStatus = r.getStatus();
//            responseString = r.readEntity(String.class);
//            assertTrue(!responseString.isEmpty());
//        });
//
//        Entao("o Portal responde para apresentação a representação App contendo os sistemas", (DataTable data) -> {
//            final Gson gson = new GsonBuilder().create();
//            final List<String> expected = data.asList(String.class);
//             final List<Sistema> actual = gson.fromJson(responseString, new TypeToken<List<Sistema>>(){}.getType());
//            assertTrue(actual.stream().allMatch(s -> expected.contains(s.getSigla())));
//        });
//    }
//
//    private void fluxoAlternativo() {
//        Dado("que o usuário (.*) foi previamente autenticado", (String usuario) -> {
//            assertEquals(userExtractor.extractUser().get(),usuario);
//        });
//
//        Quando("a lista de sistemas para o usuário (.*) é solicitada",(String usuario) -> {
//            when(sistemas.listarAcessosPorUsuarioAmbiente(usuario)).thenReturn(Optional.empty());
//            final Response r = target("apps/home").request().get(Response.class);
//            responseStatus = r.getStatus();
//            responseString = r.readEntity(String.class);
//            assertTrue(!responseString.isEmpty());
//        });
//
//        Entao("o Portal retorna uma lista vazia e status HTTP ([0-9]+)", (Integer status) -> {
//            final Gson gson = new GsonBuilder().create();
//            final HashMap<String,String> actual =
//                    gson.fromJson(responseString, new TypeToken<HashMap<String,String>>(){}.getType());
//            assertTrue(actual.keySet().isEmpty());
//            assertEquals(status, responseStatus);
//        });
//    }
//
//    private void fluxoExcecao() {
//
//        Dado("que um usuário não autenticado acessa a funcionalidade", () -> {
//            userExtractor.empty = true;
//            assertTrue(!userExtractor.extractUser().isPresent());
//        });
//
//        Quando("a apresentação solicita a lista de sistemas do usuário não autenticado",() -> {
//            responseStatus = target("apps/home").request().get(Response.class).getStatus();
//        });
//
//        Entao("o Portal responde o status HTTP ([0-9]+) negando o acesso do usuário", (Integer status) -> {
//            assertEquals(status, responseStatus);
//        });
//    }
//
//    @Override
//    protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
//        return new GrizzlyWebTestContainerFactory();
//    }
//
//    @Override
//    protected DeploymentContext configureDeployment() {
//        final Class[] resources =
//                new Class[]{AppsQueries.class,
//                            AcessoNegadoExceptionMapper.class};
//
//        //Random Ports
//        forceSet(TestProperties.CONTAINER_PORT, "0");
//
//        return ServletDeploymentContext
//                .forServlet(new ServletContainer(new ResourceConfig(resources)))
//                .addListener(ContextLoaderListener.class)
//                .contextParam("contextConfigLocation", "classpath:listar_acesso_sistemas.xml")
//                .build();
//    }
//
//    public static MyCasUserExtractor mockCasUserExtractor() {
//        return userExtractor;
//    }
//
//    public static AlunoService mockSistemaService() {
//        sistemas = mock(AlunoService.class);
//        return sistemas;
//    }
//
//
}
