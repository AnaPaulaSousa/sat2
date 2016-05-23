package gov.goias.sat2.conf.serverfilters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * <b>Título:</b> gov.goias.sistema.conf.serverfilters.SecurityHeaderServerFilter
 * <br><b>Descrição:</b> Coleção de filtros de Segurança indicados pela OWASP<br/>
 *
 * @see https://www.owasp.org/index.php/Security_Headers
 * <br><b>Copyright:</b> Copyright(c) 2015
 * <br><b>Empresa:</b> SEGPLAN
 */
@Provider
public class SecurityHeaderServerFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        responseContext.getHeaders().add("X-Frame-Options","SAMEORIGIN");
        responseContext.getHeaders().add("X-XSS-Protection","1; mode=block");

    }
}
