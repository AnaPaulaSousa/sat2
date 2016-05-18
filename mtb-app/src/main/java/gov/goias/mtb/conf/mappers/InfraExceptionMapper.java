
package gov.goias.mtb.conf.mappers;

import gov.goias.excecao.InfraException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * <b>Título:</b> InfraExceptionMapper
 * <br><b>Descrição:</b> Mapeamento da exceção InfraException<br>
 * para que responda código 500 e mensagem genérica
 * <br><b>Copyright:</b> Copyright(c) 2015
 * <br><b>Empresa:</b> SEGPLAN
 */
@Component
@Provider
public class InfraExceptionMapper implements ExceptionMapper<InfraException> {
	private static final Logger LOGGER = Logger.getLogger(InfraExceptionMapper.class);
	@Context
	protected HttpServletRequest request;

	@Override
	public Response toResponse(final InfraException infraException) {
		LOGGER.error(infraException);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(infraException.getMessage()).build();
	}

}
