package gov.goias.mtb.conf.mappers;

import gov.goias.mtb.exceptions.AcessoNegadoException;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by thiago-rs on 4/12/16.
 */
@Provider
public class AcessoNegadoExceptionMapper implements ExceptionMapper<AcessoNegadoException> {
    private static final Logger LOGGER = Logger.getLogger(AcessoNegadoExceptionMapper.class);

    @Override
    public Response toResponse(final AcessoNegadoException exception) {
        LOGGER.debug(exception);
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
