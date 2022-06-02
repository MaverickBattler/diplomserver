package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.service.VerificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//Контроллер, обрабатывающий входящие REST API запросы, связанные с верификацией
@Controller
public class VerificationController {
    private final VerificationService verificationService;
    //Constructor Dependency Injection
    public VerificationController(
            VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    //Нахождение пациента с данным id медицинской карты
    @PostMapping("/verify-patient-id")
    @ResponseBody
    public String verifyMedicalCardId(@RequestBody String medicalCardId) {
        return verificationService.verifyMedicalCardId(medicalCardId);
    }
    //Подтверждение адреса электронной почты
    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam String code) {
        if (verificationService.verifyEmail(code))
            //html-страница с сообщением об успешном подтверждении email
            return "verification-page";
        else
            //html-страница с сообщением об ошибке подтверждения email
            return "email-verification-failure-page";
    }
}
