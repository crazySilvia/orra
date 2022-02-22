package de.silvia.backend.security.services;

import de.silvia.backend.api.UserDto;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    private final IUserRepo userRepo = mock(IUserRepo.class);
    private final PasswordEncoder encoder = mock(PasswordEncoder.class);
    private final RegisterService registerService = new RegisterService(userRepo, encoder);

    @Test
    public void registerUser() {
        UserDto mockedUserDto = new UserDto("Julius", "Schmidt", "schmitti",
                "123456", "schmitt@uli.de");
        String encodedPw = encoder.encode(mockedUserDto.getPassword());
        User mockedUser = User.newUser(mockedUserDto.getFirstName(), mockedUserDto.getEmail(),
                mockedUserDto.getLastName(), mockedUserDto.getUserName(),
                encodedPw, List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        when(userRepo.insert(mockedUser)).thenReturn(mockedUser);
        registerService.registerUser(mockedUserDto);
        verify(userRepo, times(1)).insert(mockedUser);
    }

    @Test
    void shouldThrowErrorIfRegisterFails() {
        UserDto mockedUserDto = new UserDto("Frauke", "Schmitt", "FrauSchmitt",
                "fRSm1245", "fra@ke.de");
        String encodedPw = encoder.encode(mockedUserDto.getPassword());
        User mockedUser = User.newUser(mockedUserDto.getFirstName(), mockedUserDto.getEmail(),
                mockedUserDto.getLastName(), mockedUserDto.getUserName(),
                encodedPw, List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        when(userRepo.insert(mockedUser)).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> registerService.registerUser(mockedUserDto));
    }
}