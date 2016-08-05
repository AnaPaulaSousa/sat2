package gov.goias.sat2.controllers;

import gov.goias.cas.auth.CasUserExtractor;
import gov.goias.excecao.InfraException;
import gov.goias.sat2.util.MsgApp;
import javaslang.control.Try;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by thiago-rs on 4/11/16.
 */
@Controller
@Path("logout")
public class LogoutCmds {

    private static final Logger LOGGER = Logger.getLogger(LogoutCmds.class);

    @Value("#{casProperties['casServerUrlPrefix']}")
    private String casLogoutUrl;

    @Autowired
    private CasUserExtractor userExtractor;

    @Context
    private HttpServletRequest request;

    @GET
    public Response logout() {

        final String username = userExtractor.extractUser(request)
                .orElse(MsgApp.CAS_LOGIN);

        Try.of(() -> request.getSession(false))
                .onSuccess(s -> {if(s!=null) s.invalidate(); })
                .onFailure(e -> LOGGER.debug(e)).get();

        final URI redirect = Try.of(() -> new URI(casLogoutUrl + "/logout?redirect"))
                .onFailure(e -> new InfraException(e)).get();

        LOGGER.debug(username + ", teve sua sessão finalizada. Redirecionando para " + redirect.toString());

        return Response.seeOther(redirect).build();

    }

}