package gov.goias.sat2.controllers;

import org.apache.log4j.Logger;
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

    @Context
    private HttpServletRequest req;

    @GET
    public Response logout() {

        try {

            req.getSession().invalidate();

            final URI redirect = new URI(casLogoutUrl+"/logout");
            return Response.seeOther(redirect).build();

        } catch (URISyntaxException e) {
            LOGGER.error(e);
            return Response.ok().build();
        }catch (Exception e){
            LOGGER.error(e);
            return Response.ok().build();
        }

    }

}
