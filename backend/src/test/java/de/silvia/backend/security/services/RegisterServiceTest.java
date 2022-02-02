package de.silvia.backend.security.services;

import de.silvia.backend.api.UserDto;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Mock
    IUserRepo userRepo;

    @Mock
    PasswordEncoder encoder;

    @Test
    public void registerUser(){
        assertNotNull(userRepo);
        UserDto mockedUserDto = new UserDto("Julius", "Schmidt", "schmitti",
                "123456", "schmitt@uli.de");
        String encodedPw = encoder.encode(mockedUserDto.getPassword());
        User mockedUser = User.newUser(mockedUserDto.getFirstName(), mockedUserDto.getEmail(),
                mockedUserDto.getLastName(), mockedUserDto.getUserName(),
                encodedPw, List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        when(userRepo.insert(mockedUser)).thenReturn(mockedUser);
        RegisterService r = new RegisterService(userRepo, encoder);
        assertTrue(r.registerUser(mockedUserDto));
        verify(userRepo,times(1)).insert(mockedUser);
    }
}