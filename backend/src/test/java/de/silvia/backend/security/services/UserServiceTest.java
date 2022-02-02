package de.silvia.backend.security.services;

import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    IUserRepo userRepo;

    @Test
    public void loadUserByUsername()  {
        assertNotNull(userRepo);
        User mockedUser = new User("Julius", "123456");
        when(userRepo.findByUsername("Julius")).thenReturn(java.util.Optional.of(mockedUser));
        UserService t  = new UserService(userRepo);
        User check = t.loadUserByUsername("Julius");
        assertEquals(mockedUser, check);
    }
}