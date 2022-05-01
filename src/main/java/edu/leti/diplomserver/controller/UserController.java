package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.RegistrationRequestDto;
import edu.leti.diplomserver.dto.UserDataDto;
import edu.leti.diplomserver.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequestDto registrationRequestDto) {
        return userService.register(registrationRequestDto);
    }

    @PostMapping("/get-user-data")
    public UserDataDto getUserData(@RequestBody String email) {
        return userService.getUserData(email);
    }

}
