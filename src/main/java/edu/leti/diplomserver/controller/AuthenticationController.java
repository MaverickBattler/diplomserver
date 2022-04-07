package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.AuthenticationRequestDto;
import edu.leti.diplomserver.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(
            AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return authenticationService.authenticate(authenticationRequestDto);
    }
}
