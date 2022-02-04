package de.silvia.backend.security.services;

import de.silvia.backend.BackendApplication;
import de.silvia.backend.api.UserDto;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
public class RegisterService {

    private static final Log LOG = LogFactory.getLog(BackendApplication.class);
    private final PasswordEncoder encoder;
    private final IUserRepo userRepo;

    public RegisterService(IUserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public void registerUser(@Validated UserDto data) {
        final String encodedPassword = encoder.encode(data.getPassword());
        final User user = User.newUser(data.getFirstName(), data.getEmail(), data.getLastName(), data.getUserName(),
                encodedPassword, List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        try {
            userRepo.insert(user);
        } catch (Exception e) {
            LOG.info("User " + user.getUsername() + " already exists.");
        }
    }
}
