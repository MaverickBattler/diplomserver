package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.Patient;
import edu.leti.diplomserver.dto.AuthenticationRequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PatientIdentityVerificationService {

    public String authenticate(AuthenticationRequestDto authenticationRequestDto) {
        ArrayList<Patient> patients = new ArrayList<>();
        Patient patientToAdd = Patient.builder().medicalCardNumber("12345678ABCD")
                .firstName("Маргарита").lastName("Кузнецова")
                .fatherName("Кирилловна")
                .email("mkkuznetsova@yandex.ru").phoneNumber("89532439096")
                .operationName("Эндопротезирование коленного сустава").build();
        patients.add(patientToAdd);
        patientToAdd = Patient.builder().medicalCardNumber("1A2B3C4D5678")
                .firstName("Михаил").lastName("Петров")
                .fatherName("Гордеевич")
                .email("petr23@gmail.com").phoneNumber("89216342525")
                .operationName("Эндопротезирование коленного сустава").build();
        patients.add(patientToAdd);
        patientToAdd = Patient.builder().medicalCardNumber("DACB87654321")
                .firstName("Артём").lastName("Новиков")
                .fatherName("Дмитриевич")
                .email("afureta@mail.ru").phoneNumber("89605569312")
                .operationName("Аппендэктомия").build();
        patients.add(patientToAdd);

        for (Patient patient : patients) {
            if (patient.getMedicalCardNumber().equals(authenticationRequestDto.getMedicalCardNumber())
                    && patient.getOperationName().equals("Эндопротезирование коленного сустава"))
                return patient.getPhoneNumber();
        }
        return null;
    }
}
