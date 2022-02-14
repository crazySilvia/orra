package de.silvia.backend.security.services;

import de.silvia.backend.security.models.LoginData;
import de.silvia.backend.services.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {
    private static final Log LOG = LogFactory.getLog(LoginService.class);
    private final JWTUtils jwtService;
    private final AuthenticationManager authenticationManager;

    public String loginUser(LoginData data) {
        try {
            //darf der User sich anmelden?
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getName(), data.getPassword())
            );
            LOG.info("Erfolgreich eingeloggt");
            return jwtService.createToken(data);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials");
        }
    }
}