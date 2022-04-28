package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.service.VerificationService;
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
    public String verifyMedicalCardId(@RequestBody String medicalCardId) {
        return verificationService.verifyMedicalCardId(medicalCardId);
    }

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam String code) {
        if (verificationService.verifyEmail(code))
            return "verification-page";
        else
            return "email-verification-failure-page";
    }
}
