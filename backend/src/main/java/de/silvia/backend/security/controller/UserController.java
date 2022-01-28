package de.silvia.backend.security.controller;

import de.silvia.backend.security.models.LoginData;
import de.silvia.backend.security.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService uServ;

    public UserController(UserService uServ){
        this.uServ = uServ;
    }

    //*********** Methoden *********

    @PostMapping("register")
    public void register(String username, String password){
        LoginData loginData = new LoginData(username, password);
        uServ.registerUser(loginData);
    }

    @GetMapping("me")
    public UserDetails getLoggedInUser(Principal principal) {
        String username = principal.getName();
        return uServ
                .loadUserByUsername(username);
    }
}
