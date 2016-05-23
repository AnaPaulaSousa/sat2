
package gov.goias.sat2.conf.mappers;

import gov.goias.view.model.ViewErrorModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Map;

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

	@Context
	protected HttpServletRequest request;

	@Override
	public Response toResponse(final ValidationException exception) {
		return Response.status(422).entity(getErrorModel()).build();
	}

	protected ViewErrorModel getErrorModel(){
		final ViewErrorModel vem = new ViewErrorModel();
		vem.setFormParams((Map<String, Object>) request.getAttribute("formParams"));
		vem.setValidations((Map<String, List<String>>) request.getAttribute("validations"));
		return vem;
	}

}
