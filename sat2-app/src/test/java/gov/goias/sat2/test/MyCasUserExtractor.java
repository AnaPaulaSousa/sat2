package gov.goias.sat2.test;

import gov.goias.sat2.conf.auth.CasUserExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by thiago-rs on 4/18/16.
 */
public class MyCasUserExtractor extends CasUserExtractor {

    public boolean empty;

    @Override
    public Optional<String> extractUser() {
        return empty? Optional.empty() : super.extractUser();
    }

    @Override
    public Optional<String> extractUser(HttpServletRequest request) {
        return empty? Optional.empty() : super.extractUser(request);
    }

    @Override
    public Optional<String> extractUser(HttpSession session) {
        return empty? Optional.empty() : super.extractUser(session);
    }

    @Override
    public Optional<String> extractUserFromSession(HttpSession session) {
        return empty? Optional.empty() : super.extractUserFromSession(session);
    }
}

