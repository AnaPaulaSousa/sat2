package gov.goias.sat2.controllers;

import gov.goias.sat2.services.AlunoService;
import gov.goias.sat2.view.model.Aluno;
import gov.goias.validation.FormValidation;
import gov.goias.validation.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/aluno")
public class AlunoCmds {

    @Autowired
    private AlunoService service;

    @POST
    @FormValidation
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response incluir(@FormParam("id") final Long id,
                            @NotNull @NotEmpty @FormParam("nome") final String nome,
                            @NotNull @NotEmpty @FormParam("email") final String email) {
        Aluno aluno = new Aluno(id, nome, email);
        if (id != null) {
            service.salvar(aluno.toEntity());
        } else {
            aluno = aluno.from(service.salvar(aluno.toEntity()));
        }
        return Response.status(Response.Status.SEE_OTHER).header("Location", String.format("aluno/%s", aluno.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") final Long id) {
        service.remover(id);
        return Response.status(Response.Status.OK).build();
    }

}