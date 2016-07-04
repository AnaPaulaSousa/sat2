
package gov.goias.sat2.conf.mappers;

import gov.goias.view.model.ViewErrorModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * <b>Título:</b> ValidationExceptionMapper
 * <br><b>Descrição:</b> Mapeamento da exceção ValidationException<br>
 * para que responda código 422 e retorne para pagina original da requisição<br>
 * e apresente mensagens de validação para usuário
 * <br><b>Copyright:</b> Copyright(c) 2015
 * <br><b>Empresa:</b> SEGPLAN
 */
@Component
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	private static final Logger LOGGER = Logger.getLogger(AcessoNegadoExceptionMapper.class);

	@Context
	protected HttpServletRequest request;

	@Override
	public Response toResponse(final ValidationException exception) {
		LOGGER.debug(exception);
		return Response.status(422).entity(getErrorModel()).build();
	}

	private Map<String, Object> getErrorModel(){
		return new HashMap<String,Object>(){{
			put("validations", (Map<String, List<String>>) request.getAttribute("validations"));
		}};
	}



}
