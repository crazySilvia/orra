package de.silvia.backend.security.services;

import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private final IUserRepo userRepo = mock(IUserRepo.class);
    private final UserService userService = new UserService(userRepo);

    @Test
    public void loadUserByUsername() {
        assertNotNull(userRepo);
        User mockedUser = User.newUser("Julius", "jjj@ddd.cd", "Schmitz",
                "userX", "kskskk", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        when(userRepo.findUserByUsername("Julius")).thenReturn(mockedUser);
        UserService t = new UserService(userRepo);
        User actualUser = t.loadUserByUsername("Julius");
        assertEquals(mockedUser, actualUser);
    }

    @Test
    void shouldThrowErrorIfLoadByUsernameFails() {
        String nonExistUsername = "Oliver";
        when(userRepo.findUserByUsername(nonExistUsername))
                .thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(nonExistUsername));
    }

    @Test
    void getUserByPrincipal() {
        Principal testPrincipal = () -> "Heinz";
        User mockedUser = User.newUser("Heinz", "jjj@ddd.cd", "Schmitz",
                "userX", "kskskk", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        when(userRepo.findUserByUsername("Heinz")).thenReturn(mockedUser);

        assertEquals(mockedUser, userService.getUserByPrincipal(testPrincipal));
    }

    @Test
    void shouldThrowErrorIfGetUserByPrincipalFails() {
        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByPrincipal(null));
    }
}