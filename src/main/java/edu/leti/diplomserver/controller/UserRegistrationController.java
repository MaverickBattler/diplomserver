package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.RegistrationRequestDto;
import edu.leti.diplomserver.service.UserRegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(
            UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequestDto registrationRequestDto) {
        return userRegistrationService.register(registrationRequestDto);
    }
}
