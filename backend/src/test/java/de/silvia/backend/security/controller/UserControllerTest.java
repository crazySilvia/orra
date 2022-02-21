package de.silvia.backend.security.controller;

import de.silvia.backend.api.UserDto;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private IUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final WebClient webTestClient = WebClient.create();

    @Test
    void register() {
        //Given
        UserDto mockedUserDto = new UserDto("Julius", "Schmidt", "schmitti",
                "123456", "schmitt@uli.de");
        String encodedPw = passwordEncoder.encode(mockedUserDto.getPassword());
        User mockedUser = User.newUser(mockedUserDto.getFirstName(), mockedUserDto.getEmail(),
                mockedUserDto.getLastName(), mockedUserDto.getUserName(),
                encodedPw, List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        //When
        when(userRepo.insert(mockedUser)).thenReturn(mockedUser);

        ResponseEntity<Void> registerUser = webTestClient.post()
                .uri("http://localhost:" + port + "/api/user/register")
                .bodyValue(mockedUserDto)
                .retrieve()
                .toEntity(void.class)
                .block();
        //Then
        assertNotNull(registerUser);
        assertEquals(HttpStatus.OK, registerUser.getStatusCode());
    }
}