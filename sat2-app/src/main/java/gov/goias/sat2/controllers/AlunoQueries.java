package gov.goias.sat2.controllers;

import gov.goias.excecao.GoiasResourceMessage;
import gov.goias.excecao.negocio.NaoEncontradoException;
import gov.goias.sat2.representation.DataTableResponse;
import gov.goias.sat2.services.AlunoService;
import gov.goias.sat2.view.model.Aluno;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Path("/aluno")
public class AlunoQueries {

    private static final Logger LOGGER = Logger.getLogger(AlunoQueries.class);

    @Context
    protected HttpServletRequest request;

    @Autowired
    AlunoService service;

    @GET
    @Path("/{id}")
    @RolesAllowed("Consultas")
    public Aluno obter(@PathParam("id") final Long id) {
        return Aluno.from(service.obterPorId(id).orElseThrow(() -> new NaoEncontradoException()));
    }

    @GET
    @RolesAllowed("Consultas")
    public List<Aluno> listar() {
        return null; //return service.listarPaginado();
    }

    @GET
    @Path("/paginar")
    @Produces({DataTableResponse.JSON})
    @RolesAllowed("Consultas")
    public Response list(@QueryParam("draw")   final Integer draw,
                         @QueryParam("start")  final Integer start,
                         @QueryParam("length") final Integer length,
                         @QueryParam("search[value]") final String searchValue,
                         @QueryParam("columns[0][search][value]") final Long id,
                         @QueryParam("columns[1][search][value]") final String nome,
                         @QueryParam("columns[2][search][value]") final String email) {
        final DataTableResponse dtr = new DataTableResponse();
        final List<Map<String, String>> res = new ArrayList<>();
        dtr.setDraw(draw);
        final String[] columns = new String[]{"id", "nome","email"};
        try {
            final Integer qtTotal = new Long(service.contarTodos()).intValue();
            final Map<String, String> searchParams = new HashMap<>();
            if (!searchValue.isEmpty()) {
                searchParams.put(columns[1], searchValue);
            }
            final Integer page = new Double(Math.ceil(start / length)).intValue();
            final Page<Aluno> list = service.listarPaginado(id, nome, email, new PageRequest(page, length)).map(a -> Aluno.from(a)) ;

            final Integer qtFiltrada = new Long(list.getTotalElements()).intValue();
            if (qtFiltrada > 0) {
                list.forEach(a -> res.add(a.asMapofValues(
                        (Object v) -> String.format("row_%s", v),
                        "DT_RowId",
                        "id",
                        columns
                )));
            }
            dtr.setRecordsFiltered(qtFiltrada);
            dtr.setData(res);
            dtr.setRecordsTotal(qtTotal);
        } catch (Exception e) {
            LOGGER.error(e);
            dtr.setError(GoiasResourceMessage.getMessage("msg_erro_dessconhecido:"+ e.getMessage()));
        }
       return Response.status(Response.Status.OK).entity(dtr).build();
    }

}