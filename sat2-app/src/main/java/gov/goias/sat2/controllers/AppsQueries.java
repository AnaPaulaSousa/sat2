package gov.goias.sat2.controllers;

import gov.goias.sat2.conf.auth.CasUserExtractor;
import gov.goias.sat2.exceptions.AcessoNegadoException;
import gov.goias.sat2.representation.App;
import gov.goias.sat2.services.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by thiago-rs on 4/11/16.
 */
@Controller
@Path("apps")
public class AppsQueries {

    @Autowired
    private SistemaService sistemas;

    @Autowired
    private CasUserExtractor userExtractor;

    @Context
    private HttpServletRequest req;

    @GET
    @Produces("application/json")
    @Path("/home")
    public List<App> home() {

        Optional<String> teste = Optional.empty();

        teste.ifPresent(t ->


                System.out.println(t));


        final String u = userExtractor.extractUser(req)
                .orElseThrow(() -> new AcessoNegadoException());

        final Optional<List<App>> apps =
            sistemas.listarAcessosPorUsuarioAmbiente(u)
                .map(l -> l.stream().map(s -> App.from(s))
                           .collect(Collectors.toList()));

        return apps.orElse(Collections.emptyList());

  }

}
