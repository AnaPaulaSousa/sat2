package gov.goias.sat2.conf.mappers;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ParamException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * <b>Título:</b> ParamExceptionMapper
 * <br><b>Descrição:</b> Mapeamento da exceção ParamException<br>
 * para que responda código 400 e mensagem de erro na requisição
 * <br><b>Copyright:</b> Copyright(c) 2015
 * <br><b>Empresa:</b> SEGPLAN
 */
@Component
@Provider
public class ParamExceptionMapper implements ExceptionMapper<ParamException> {
	private static final Logger LOGGER = Logger.getLogger(ParamExceptionMapper.class);

	@Override
	public Response toResponse(final ParamException exception) {
		LOGGER.error(exception);
		return Response.status(Response.Status.BAD_REQUEST).entity(getErrorModel(exception)).build();
	}

	protected String getErrorModel(final ParamException exception) {
		return exception.getMessage();
	}

}
