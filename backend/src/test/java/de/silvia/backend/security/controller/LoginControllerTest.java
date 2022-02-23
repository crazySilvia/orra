package de.silvia.backend.security.controller;

import de.silvia.backend.security.models.LoginData;
import de.silvia.backend.security.services.LoginService;
import de.silvia.backend.services.JWTUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    private final JWTUtils testJwtUtils = mock(JWTUtils.class);
    private final AuthenticationManager testAuthenticationManager = mock(AuthenticationManager.class);
    private final LoginService testLoginService = new LoginService(testJwtUtils, testAuthenticationManager);
    private final LoginController testLoginController = new LoginController(testLoginService);

    @Test
    void login() {
        //Given
        LoginData testLoginData = new LoginData("Jule", "ju123");
        //When
        when(testLoginService.loginUser(testLoginData)).thenReturn("testToken");
        //Then
        assertEquals("testToken", testLoginController.login(testLoginData));
    }

    @Test
    void loginWithInvalidCredentials() {
        //Given
        LoginData invalidCredentials = new LoginData("Jule", "ju123");
        //When
        when(testLoginService.loginUser(invalidCredentials))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials"));
        //Then
        assertThrows(ResponseStatusException.class, () -> testLoginController.login(invalidCredentials));
    }
}