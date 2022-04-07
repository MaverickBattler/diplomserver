package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.service.VerificationService;
import edu.leti.diplomserver.dto.IdVerificationRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VerificationController {
    private final VerificationService verificationService;

    public VerificationController(
            VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("/verify-patient-id")
    @ResponseBody
    public String verifyMedicalCardId(@RequestBody IdVerificationRequestDto idVerificationRequestDto) {
        return verificationService.verifyMedicalCardId(idVerificationRequestDto);
    }

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam String code) {
        if (verificationService.verifyEmail(code))
            return "verification-page";
        else
            return "email-verification-failure-page";
    }
}
