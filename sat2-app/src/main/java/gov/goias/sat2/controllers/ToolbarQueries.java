package gov.goias.sat2.controllers;

import gov.goias.AlmPublisher;
import gov.goias.cas.auth.CasUserExtractor;
import gov.goias.exceptions.AcessoNegadoException;
import gov.goias.sat2.representation.ToolbarRep;
import gov.goias.sat2.representation.UsuarioRep;
import javaslang.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;


@Controller
@Path("toolbar")
public class ToolbarQueries {


    @Autowired
    private CasUserExtractor userExtractor;

    @Context
    private HttpServletRequest req;

    @GET
//  @RolesAllowed({"PORTAL.MNTUSUARIO"})
    public ToolbarRep toolbar() throws IOException {

        final String username = userExtractor.extractUser(req)
                .orElseThrow(() -> new AcessoNegadoException());

        final UsuarioRep u = new UsuarioRep();
        u.setNomeUsuario(username);


        final AlmPublisher.AlmRep almRep = Try.of(() -> AlmPublisher.publish(req))
                .map(map -> AlmPublisher.toRep(map))
                .recover(e -> new AlmPublisher.AlmRep())
                .get();

        return new ToolbarRep(u,almRep);
    }

}
