package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.UnverifiedUser;
import edu.leti.diplomserver.domain.Patient;
import edu.leti.diplomserver.domain.User;
import edu.leti.diplomserver.dto.IdVerificationRequestDto;
import edu.leti.diplomserver.repository.UnverifiedUsersRepository;
import edu.leti.diplomserver.repository.MedicalInstitutionDatabaseImitation;
import edu.leti.diplomserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VerificationService {

    private final UnverifiedUsersRepository unverifiedUsersRepository;
    private final UserRepository userRepository;
    private final ArrayList<Patient> patients;

    public VerificationService(UnverifiedUsersRepository unverifiedUsersRepository,
                               UserRepository userRepository) {
        this.unverifiedUsersRepository = unverifiedUsersRepository;
        this.userRepository = userRepository;
        patients = new MedicalInstitutionDatabaseImitation().getPatients();
    }

    public String idVerificationRequest(IdVerificationRequestDto idVerificationRequestDto) {

        for (Patient patient : patients) {
            String patientMedicalCardId = patient.getMedicalCardId();
            if (patientMedicalCardId.equals(idVerificationRequestDto.getMedicalCardId())
                    && patient.getOperationName().equals("Эндопротезирование коленного сустава")) {
                if (userRepository.findById(patientMedicalCardId).isPresent())
                    //если пользователь уже зарегистрирован (есть в UserRepository)
                    return "registered";
                else
                    return "to_be_registered";
            }
        }//Иначе, если пациента с таким ID медицинской карты не существует
        return null;
    }

    public void verifyEmail(String code) {
        UnverifiedUser unverifiedUser = unverifiedUsersRepository.findByCode(code);
        if (unverifiedUser != null) {
            String medicalCardId = unverifiedUser.getMedicalCardId();
            for (Patient patient : patients) {
                if (patient.getMedicalCardId().equals(medicalCardId)) {
                    User user = User.builder()
                            .medicalCardId(patient.getMedicalCardId())
                            .email(patient.getEmail())
                            .password(unverifiedUser.getPassword())
                            .firstName(patient.getFirstName())
                            .lastName(patient.getLastName())
                            .fatherName(patient.getFatherName())
                            .phoneNumber(patient.getPhoneNumber())
                            .build();
                    userRepository.save(user);
                    unverifiedUsersRepository.deleteByMedicalCardId(unverifiedUser.getMedicalCardId());
                }
            }
        }
    }
}
