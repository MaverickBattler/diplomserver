package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.AuthenticationRequestDto;
import edu.leti.diplomserver.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Контроллер, обрабатывающий входящие REST API запросы, связанные с аутентификацией
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    //Constructor Dependency Injection
    public AuthenticationController(
            AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //Сохранение результат заполнения анкеты OKS
    @PostMapping("/login")
    public String authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return authenticationService.authenticate(authenticationRequestDto);
    }
}
