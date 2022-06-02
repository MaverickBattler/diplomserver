package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.RegistrationRequestDto;
import edu.leti.diplomserver.dto.UserDataDto;
import edu.leti.diplomserver.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Контроллер, обрабатывающий входящие REST API запросы, связанные с прохождением тренировок
@RestController
public class UserController {

    private final UserService userService;
    //Constructor Dependency Injection
    public UserController(
            UserService userService) {
        this.userService = userService;
    }
    //Регистрация пользователя
    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequestDto registrationRequestDto) {
        return userService.register(registrationRequestDto);
    }
    //Получение информации о пользователе
    @PostMapping("/get-user-data")
    public UserDataDto getUserData(@RequestBody String email) {
        return userService.getUserData(email);
    }

}
