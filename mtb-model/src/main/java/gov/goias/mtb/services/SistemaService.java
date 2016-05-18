package gov.goias.mtb.services;

import com.google.common.base.Strings;
import gov.goias.mtb.entities.Sistema;
import gov.goias.mtb.entities.StatusSistema;
import gov.goias.mtb.entities.Usuario;
import gov.goias.mtb.repositories.SistemaRepository;
import gov.goias.mtb.util.MsgModel;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by thiago-rs on 4/11/16.
 */
@Service
public class SistemaService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private SistemaRepository sistemas;

    public Optional<List<Sistema>> listarAcessosPorUsuarioAmbiente(String u){
        final Usuario usuario = new Usuario();
        usuario.setNomeUsuario(u);
        return listarAcessosPorUsuarioAmbiente(usuario);
    }

    public Optional<List<Sistema>> listarAcessosPorUsuarioAmbiente(Usuario u){

        Objects.requireNonNull(u, MsgModel.USUARIO_NOT_NULL);

        if(Strings.isNullOrEmpty(u.getNomeUsuario())){
            throw new NullPointerException(MsgModel.USUARIO_NOT_NULL);
        }

        final Boolean isWeb =
                "web".equalsIgnoreCase(System.getProperty("gov.goias.portal.ambiente"));

        final Boolean isIntra =
                "intra".equalsIgnoreCase(System.getProperty("gov.goias.portal.ambiente"));

        if(isWeb == false && isIntra == false)
            LOGGER.error(MsgModel.AMBIENTE_JVM_PARAM);

        return sistemas.findByUsuario(u.getNomeUsuario()).map(l -> l.stream()
//                       .filter(s -> isWeb == s.isInternet() || isIntra == s.isIntranet())
                       .filter(s -> StatusSistema.A == s.getStatus())
                       .collect(Collectors.toList()));

    }

}
