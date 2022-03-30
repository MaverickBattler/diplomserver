package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.service.PatientIdentityVerificationService;
import edu.leti.diplomserver.dto.VerificationRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientIdentityVerificationController {
    private final PatientIdentityVerificationService patientIdentityVerificationService;

    //Constructor Dependency Injection
    public PatientIdentityVerificationController(
            PatientIdentityVerificationService patientIdentityVerificationService) {
        this.patientIdentityVerificationService = patientIdentityVerificationService;
    }

    @PostMapping("/verify")
    @ResponseBody
    public String verificationRequest(@RequestBody VerificationRequestDto verificationRequestDto) {
        return patientIdentityVerificationService.verificationRequest(verificationRequestDto);
    }

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam String code) {
        patientIdentityVerificationService.verifyEmail(code);
        return "verification-page";
    }
}
