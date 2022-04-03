package edu.leti.diplomserver.repository;

import edu.leti.diplomserver.domain.Patient;
import lombok.Getter;

import java.util.ArrayList;

public class MedicalInstitutionDatabaseImitation {

    @Getter
    public ArrayList<Patient> patients;

    public MedicalInstitutionDatabaseImitation() {
        patients = new ArrayList<>();
        Patient patientToAdd = Patient.builder().medicalCardId("12345678ABCD")
                .firstName("Кирилл").lastName("Мохнаткин")
                .fatherName("Валерьевич")
                .email("crusty1234567@yandex.ru").phoneNumber("89532439096")
                .operationName("Эндопротезирование коленного сустава").build();
        patients.add(patientToAdd);
        patientToAdd = Patient.builder().medicalCardId("1A2B3C4D5678")
                .firstName("Михаил").lastName("Петров")
                .fatherName("Гордеевич")
                .email("petr23@gmail.com").phoneNumber("89216342525")
                .operationName("Эндопротезирование коленного сустава").build();
        patients.add(patientToAdd);
        patientToAdd = Patient.builder().medicalCardId("DACB87654321")
                .firstName("Артём").lastName("Новиков")
                .fatherName("Дмитриевич")
                .email("afureta@mail.ru").phoneNumber("89605569312")
                .operationName("Аппендэктомия").build();
        patients.add(patientToAdd);
    }
}
