package de.silvia.backend.security.controller;


import de.silvia.backend.api.UserDto;
import de.silvia.backend.security.services.RegisterService;
import de.silvia.backend.security.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService uServ;
    private final RegisterService rServ;

    public UserController(UserService uServ, RegisterService rServ){
        this.uServ = uServ;
        this.rServ = rServ;
    }

    //*********** Methoden *********

    @PostMapping("register")
    public void register(@RequestBody UserDto userDto){
        rServ.registerUser(userDto);
    }

    @GetMapping("me")
    public UserDetails getLoggedInUser(Principal principal) {
        String username = principal.getName();
        return uServ
                .loadUserByUsername(username);
    }
}
