package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.*;
import edu.leti.diplomserver.repository.RoleRepository;
import edu.leti.diplomserver.repository.UnverifiedUsersRepository;
import edu.leti.diplomserver.repository.MedicalInstitutionDatabaseImitation;
import edu.leti.diplomserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VerificationService {

    private final UnverifiedUsersRepository unverifiedUsersRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ArrayList<Patient> patients;

    public VerificationService(UnverifiedUsersRepository unverifiedUsersRepository,
                               UserRepository userRepository, RoleRepository roleRepository) {
        this.unverifiedUsersRepository = unverifiedUsersRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        patients = new MedicalInstitutionDatabaseImitation().getPatients();
    }

    public String verifyMedicalCardId(String medicalCardId) {

        for (Patient patient : patients) {
            String patientMedicalCardId = patient.getMedicalCardId();
            if (patientMedicalCardId.equals(medicalCardId)
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

    public Boolean verifyEmail(String code) {
        UnverifiedUser unverifiedUser = unverifiedUsersRepository.findByCode(code);
        if (unverifiedUser != null) {
            String medicalCardId = unverifiedUser.getMedicalCardId();
            for (Patient patient : patients) {
                if (patient.getMedicalCardId().equals(medicalCardId)) {
                    List<Role> roles = new ArrayList<>();
                    roles.add(roleRepository.findByName("ROLE_USER"));
                    User user = User.builder()
                            .medicalCardId(patient.getMedicalCardId())
                            .email(patient.getEmail())
                            .password(unverifiedUser.getPassword())
                            .firstName(patient.getFirstName())
                            .lastName(patient.getLastName())
                            .fatherName(patient.getFatherName())
                            .phoneNumber(patient.getPhoneNumber())
                            .status(Status.ACTIVE)
                            .roles(roles)
                            .build();
                    userRepository.save(user);
                    unverifiedUsersRepository.deleteByMedicalCardId(medicalCardId);
                }
            }
            return true;
        }
        return false;
    }
}
