package edu.leti.diplomserver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientDataService {

    private final PatientDataRepository patientDataRepository;
    //Constructor Dependency Injection
    public PatientDataService (PatientDataRepository patientDataRepository) {
        this.patientDataRepository = patientDataRepository;
    }

    public PatientData addPatientData(PatientData patientData) {
        return patientDataRepository.save(patientData);
    }

    public PatientData getPatientData(Long patientId) {
        return patientDataRepository.getById(patientId);
    }

    public List<PatientData> getAllPatientsData() {
        return patientDataRepository.findAll();
    }

    public void removePatientData(Long patientId) {
        patientDataRepository.deleteById(patientId);
    }
}
