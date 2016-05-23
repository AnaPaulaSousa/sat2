package gov.goias.sat2.conf.auth;

import com.google.common.base.Strings;
import gov.goias.sat2.util.MsgApp;
import org.apache.log4j.Logger;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by thiago-rs on 4/7/16.
 */
@Component
@Provider
public class CasUserExtractor {

    private static final Logger LOGGER = Logger.getLogger(CasUserExtractor.class);

    private static Optional<String> mockCasUser = Optional.empty();
    private static Properties p = new Properties(System.getProperties());

    static{

        try {

            p.load(CasUserExtractor.class.getClassLoader()
                    .getResourceAsStream("cas-config.properties"));

            confiMockUser();

        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    private static void confiMockUser() throws IOException {

        final String fakeCasUser = p.getProperty("fake-cas-user");
        final String portalCasUserTest = p.getProperty("portal.cas.user.test");

        if(!Strings.isNullOrEmpty(fakeCasUser)){
            mockCasUser = Optional.of(fakeCasUser.toUpperCase());
        }else if(!Strings.isNullOrEmpty(portalCasUserTest)){
            mockCasUser = Optional.of(portalCasUserTest.toUpperCase());
        }


    }

    protected Optional<String> extractUser(){
        return mockCasUser;
    }

    public Optional<String> extractUser(HttpServletRequest request){
        Objects.requireNonNull(request, MsgApp.CAS_LOGIN);
        return extractUser(request.getSession());
    }

    public Optional<String> extractUser(HttpSession session){
        return mockCasUser.isPresent()? mockCasUser : extractUserFromSession(session);
    }

    public Optional<String> extractUserFromSession(HttpSession session){

        if(session != null){
            final Assertion a = (Assertion)
                    session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);

            final Optional<String> u = a != null? Optional.of(a.getPrincipal().getName().toUpperCase())
                    : Optional.empty();

            LOGGER.debug("Usuário cas na sessão: "+u.orElse("Erro ao extrair usuário"));

            return u;

        }else return Optional.empty();
    }

}
