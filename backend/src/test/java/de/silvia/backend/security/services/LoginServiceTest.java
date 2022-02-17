package de.silvia.backend.security.services;

import de.silvia.backend.security.models.LoginData;
import de.silvia.backend.services.JWTUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    private final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private final JWTUtils jwtUtils = mock(JWTUtils.class);
    private final LoginService loginService = new LoginService(jwtUtils, authenticationManager);

    @Test
    void shouldThrowErrorIfLoginFails() {
        LoginData loginData = new LoginData("Fridolin", "fRi125!");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(mock(AuthenticationException.class));
        assertThrows(ResponseStatusException.class, () -> loginService.loginUser(loginData));
    }

    @Test
    void shouldReturnNewUserToken() {
        LoginData loginData = new LoginData("Fridolin", "fRi125!");
        when(jwtUtils.createToken(any(LoginData.class))).thenReturn("123");
        assertEquals("123", loginService.loginUser(loginData));
    }
}