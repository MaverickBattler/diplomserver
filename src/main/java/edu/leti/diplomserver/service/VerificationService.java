package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.*;
import edu.leti.diplomserver.repository.RoleRepository;
import edu.leti.diplomserver.repository.UnverifiedUsersRepository;
import edu.leti.diplomserver.repository.MedicalInstitutionDatabaseImitation;
import edu.leti.diplomserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Сервис, в котором представлены методы, связанные с верификацией
@Service
public class VerificationService {

    private final UnverifiedUsersRepository unverifiedUsersRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    //Имитация репозитория медицинского учреждения
    private final ArrayList<Patient> patients;
    //Constructor Dependency Injection
    public VerificationService(UnverifiedUsersRepository unverifiedUsersRepository,
                               UserRepository userRepository, RoleRepository roleRepository) {
        this.unverifiedUsersRepository = unverifiedUsersRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        //Заполнение имитации репозитория медицинского учреждения
        patients = new MedicalInstitutionDatabaseImitation().getPatients();
    }

    //Подтверждение пациента и его операции по id медицинской карты
    public String verifyMedicalCardId(String medicalCardId) {
        //Для каждого пациента из базы данных медицинского учреждения
        for (Patient patient : patients) {
            //Получение  Id медицинской карты пациента
            String patientMedicalCardId = patient.getMedicalCardId();
            //Если находится пациент с таким ID медицинской карты
            if (patientMedicalCardId.equals(medicalCardId)
                    //Если у этого пациента название операции эндопротезирование коленного сустава
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

    //Подтверждение email по коду в ссылке, отправляющейся на почту пациента
    public Boolean verifyEmail(String code) {
        UnverifiedUser unverifiedUser = unverifiedUsersRepository.findByCode(code);
        //Если найдена попытка регистрации с данным кодом
        if (unverifiedUser != null) {
            //Получение Id медицинской карты попытавшегося зарегистрироваться
            String medicalCardId = unverifiedUser.getMedicalCardId();
            for (Patient patient : patients) { //находим пациента с данным id медицинской карты
                if (patient.getMedicalCardId().equals(medicalCardId)) {
                    List<Role> roles = new ArrayList<>();
                    //добавляем в роли пользователю роль ROLE_USER
                    roles.add(roleRepository.findByName("ROLE_USER"));
                    User user = User.builder()
                            .medicalCardId(patient.getMedicalCardId())
                            .email(patient.getEmail())
                            .password(unverifiedUser.getPassword())
                            .firstName(patient.getFirstName())
                            .lastName(patient.getLastName())
                            .fatherName(patient.getFatherName())
                            .phoneNumber(patient.getPhoneNumber())
                            .status(Status.ACTIVE)//Статус ставится активен
                            .roles(roles)
                            .build();
                    //Пользователь сохранятеся в userRepository
                    userRepository.save(user);
                    //Если имеются неподтвержденные пользователи с данным id медицинской карты,
                    //они удаляются
                    unverifiedUsersRepository.deleteByMedicalCardId(medicalCardId);
                }
            }//Если успешно подтвержден email
            return true;
        }//Если код не был найден
        return false;
    }
}
