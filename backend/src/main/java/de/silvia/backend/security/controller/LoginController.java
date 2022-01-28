package de.silvia.backend.security.controller;

import de.silvia.backend.security.models.LoginData;
import de.silvia.backend.services.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class LoginController {

    final JWTUtils jwtService;
    final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager, JWTUtils jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginData data) {
        try {
            //darf der User sich anmelden?
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getName(), data.getPassword())
            );
            return jwtService.createToken(new HashMap<>(), data.getName());
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials");
        }
    }
}