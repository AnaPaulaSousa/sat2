package gov.goias.sat2;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filtro para autorização de clientes de Aplicações Web com CAS<br />
 *
 * requestResponseCharEncoding <br />
 *            - Character para codificação da request e response. Padrão: UFT-8 <br />
 *
 * @author SCTI thiago-rs - thiago-rs@segplan.go.gov.br <br />
 */
public class EncodingFilter implements Filter {

    private String characterEncoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        try{
            characterEncoding = filterConfig.getInitParameter("requestResponseCharEncoding");
        }catch (Exception e){

        }

    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(characterEncoding);
        response.setCharacterEncoding(characterEncoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
