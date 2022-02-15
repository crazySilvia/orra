package de.silvia.backend.security.services;

import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService implements UserDetailsService {
    private static final Log LOG = LogFactory.getLog(UserDetailsService.class);
    //public static final String AUTHORITY_API_READWRITE = "API_READWRITE";
    private final IUserRepo userRepo;

    public UserService(IUserRepo repository) {

        this.userRepo = repository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            LOG.warn("User mit id: " + username + " nicht gefunden");
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getUserByPrincipal(Principal principal) throws UsernameNotFoundException{
        if(principal != null){
            return loadUserByUsername(principal.getName());
        }
        LOG.warn("Principal is null");
        throw  new UsernameNotFoundException("Principal is null");
    }
}