package de.silvia.backend.security.services;

import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    IUserRepo userRepo;

    @Test
    public void loadUserByUsername()  {
        assertNotNull(userRepo);
        User mockedUser = User.newUser("Julius", "jjj@ddd.cd", "Schmitz",
                "userX", "kskskk", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        when(userRepo.findUserByUsername("Julius")).thenReturn(mockedUser);
        UserService t  = new UserService(userRepo);
        User actualUser = t.loadUserByUsername("Julius");
        assertEquals(mockedUser, actualUser);
    }
}