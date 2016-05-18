package gov.goias.mtb.conf;

import gov.goias.historico.aspecto.HistoricoAspectoFuncional;
import gov.goias.mtb.conf.auth.CasUserExtractor;
import gov.goias.mtb.repositories.HistoricoRepository;
import gov.goias.mtb.util.MsgApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import java.io.IOException;

/**
 * Created by thiago-rs on 4/11/16.
 */
@Component
public class HistoricoRequestFilter implements ContainerRequestFilter {

    @Autowired
    protected HistoricoAspectoFuncional historico;

    @Autowired
    protected HistoricoRepository historicoRepository;

    @Context
    protected HttpServletRequest servletRequest;

    @Autowired
    protected CasUserExtractor userExtractor;


    @PostConstruct
    protected void setHistorico(){

        historico.c = (final String usuario) -> historicoRepository.setUsuarioSessao(usuario);

    }

    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {

        historico.s = () -> userExtractor.extractUser(servletRequest.getSession())
                    .orElseThrow(() -> new RuntimeException(MsgApp.CAS_LOGIN));

    }

}
