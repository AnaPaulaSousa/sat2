package gov.goias.mtb.conf.mappers;

import gov.goias.excecao.negocio.NaoEncontradoException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * <b>Título:</b> NaoEncontradoExceptionMapper
 * <br><b>Descrição:</b> Mapeamento da exceção NaoEncontradoException<br>
 * para que responda código 404 e mensagem de não encontrado
 * <br><b>Copyright:</b> Copyright(c) 2015
 * <br><b>Empresa:</b> SEGPLAN
 */
@Component
@Provider
public class NaoEncontradoExceptionMapper implements ExceptionMapper<NaoEncontradoException> {
	private static final Logger LOGGER = Logger.getLogger(NaoEncontradoExceptionMapper.class);

	@Override
	public Response toResponse(final NaoEncontradoException exception) {
		LOGGER.debug(exception);
		return Response.status(Response.Status.NOT_FOUND).entity(getErrorModel(exception)).build();
	}

	protected String getErrorModel(final NaoEncontradoException exception) {
		return exception.getMessage();
	}
	protected Response.Status getErrorStatus(final NaoEncontradoException exception) {
		return Response.Status.NOT_FOUND;
	}

}
