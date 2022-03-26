package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.service.PatientIdentityVerificationService;
import edu.leti.diplomserver.dto.AuthenticationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientIdentityVerificationController {
    private final PatientIdentityVerificationService patientIdentityVerificationService;
    //Constructor Dependency Injection
    public PatientIdentityVerificationController (
            PatientIdentityVerificationService patientIdentityVerificationService) {
        this.patientIdentityVerificationService = patientIdentityVerificationService;
    }

    @PostMapping("/auth")
    public String authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return patientIdentityVerificationService.authenticate(authenticationRequestDto);
    }
}
