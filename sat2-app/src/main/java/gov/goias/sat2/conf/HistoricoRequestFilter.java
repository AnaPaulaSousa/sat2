package gov.goias.sat2.conf;

import gov.goias.historico.aspecto.HistoricoAspectoFuncional;
import gov.goias.sat2.conf.auth.CasUserExtractor;
import gov.goias.sat2.repositories.HistoricoRepository;
import gov.goias.sat2.util.MsgApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by thiago-rs on 4/11/16.
 */
@Component
@Provider
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
    public void filter(final ContainerRequestContext request) throws IOException {
        historico.s = () -> userExtractor.extractUser(servletRequest.getSession())
                    .orElseThrow(() -> new RuntimeException(MsgApp.CAS_LOGIN));
    }

}