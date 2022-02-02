package edu.leti.diplomserver;

import org.springframework.web.bind.annotation.*;

@RestController
public class PatientDataController {

    private final PatientDataService patientDataService;
    //Constructor Dependency Injection
    public PatientDataController(PatientDataService patientDataService) {
        this.patientDataService = patientDataService;
    }

    @PostMapping("/patientData")
    public void addPatientData(@RequestBody PatientData patientData) {
        patientDataService.addPatientData(patientData);
    }

    @DeleteMapping("/patientData/{id}")
    public void deletePatient(@PathVariable(name = "id") long id) {
        patientDataService.removePatientData(id);
    }
}
