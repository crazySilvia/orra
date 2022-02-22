package de.silvia.backend.security.controller;

import de.silvia.backend.api.UserDto;
import de.silvia.backend.security.services.RegisterService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {
    private final RegisterService registerService;

    public UserController(RegisterService registerService) {
        this.registerService = registerService;
    }

    //*********** Methoden *********

    @PostMapping("register")
    public void register(@RequestBody UserDto userDto) {

        registerService.registerUser(userDto);
    }
}