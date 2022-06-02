package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.UnverifiedUser;
import edu.leti.diplomserver.domain.Patient;
import edu.leti.diplomserver.domain.User;
import edu.leti.diplomserver.dto.RegistrationRequestDto;
import edu.leti.diplomserver.dto.UserDataDto;
import edu.leti.diplomserver.repository.MedicalInstitutionDatabaseImitation;
import edu.leti.diplomserver.repository.UnverifiedUsersRepository;
import edu.leti.diplomserver.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

//Сервис, в котором представлены методы, связанные с пользователем
@Service
public class UserService {

    private final EmailService emailService;
    private final UnverifiedUsersRepository unverifiedUsersRepository;
    private final UserRepository userRepository;
    ArrayList<Patient> patients;
    private final BCryptPasswordEncoder passwordEncoder;
    //Constructor Dependency Injection
    public UserService(EmailService emailService, UnverifiedUsersRepository unverifiedUsersRepository,
                       BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.emailService = emailService;
        this.unverifiedUsersRepository = unverifiedUsersRepository;
        this.userRepository = userRepository;
        patients = new MedicalInstitutionDatabaseImitation().getPatients();
        this.passwordEncoder = passwordEncoder;
    }

    //Регистрация пользователя
    public String register(RegistrationRequestDto registrationRequestDto) {
        //Id медицинской карты
        String medicalCardId = registrationRequestDto.getMedicalCardId();
        //Адрес электронной почты пациента
        String patientEmail = null;
        for (Patient patient : patients) { //Находится пациент с данным id медицинской карты
            if (patient.getMedicalCardId().equals(medicalCardId)) {
                //отправление письма с подтверждением на почту
                //массив типа byte
                byte[] array = new byte[10];
                new Random().nextBytes(array); //генерация случайной строки
                String generatedString = new String(array, StandardCharsets.UTF_8);
                //Создание хеша
                String stringHash = DigestUtils.sha256Hex(patient.getMedicalCardId() + generatedString);
                patientEmail = patient.getEmail();
                //Начало электронного адреса (URL) - адрес сервера
                final String baseUrl =
                        ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                //Экземпляр DateTimeFormatter
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                //Текущее время и дата
                LocalDateTime now = LocalDateTime.now();//находим текущее время
                //Полный электронный адрес (URL)
                String fullUrl = baseUrl + "/verify-email?code=" + stringHash;
                String fullMessage = "        Здравствуйте, " + patient.getFirstName() + " " +
                        patient.getFatherName() + "!\n\n" +
                        "        Это письмо отправлено Вам с целью подтвердить, что Вы пациент, желающий " +
                        "зарегистрироваться в приложении KneeApp (попытка регистрации произведена в " + dtf.format(now) + "). Для подтверждения нажмите " +
                        "на ссылку ниже:\n" + fullUrl + "\n\n        После нажатия на ссылку " +
                        "Ваш аккаунт будет подтвержден, и Вы сможете войти в приложение, используя адрес " +
                        "электронной почты, на который Вам пришло это сообщение, а также придуманный Вами при " +
                        "регистрации пароль." + "\n\n        Если вы не пытались зарегистрироваться в " +
                        "приложении KneeApp, то проигнорируйте данное сообщение.";
                emailService.sendSimpleMessage(patientEmail,
                        "Подтверждение адреса электронной почты пациента", fullMessage);
                //Сохранение попытки регистрации пользователя, для завершения которой необходимо подтвердить email
                unverifiedUsersRepository.save(UnverifiedUser.builder()
                        .medicalCardId(patient.getMedicalCardId())
                        .code(stringHash)
                        .password(passwordEncoder.encode(registrationRequestDto.getPassword()))
                        .build());
            }
        }
        return patientEmail;
    }

    //Получение информации о пользователя в UserDataDto
    public UserDataDto getUserData(String email) {
        //Пользователь с данным email
        User user = userRepository.findByEmail(email);
        //Адрес электронной почты лечащего врача
        String doctorEmail = "";
        for (Patient patient : patients) {
            if (patient.getMedicalCardId().equals(user.getMedicalCardId())) {
                doctorEmail = patient.getDoctorEmail(); //Нахождение email лечащего врача
            }
        }
        return UserDataDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fatherName(user.getFatherName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .medicalCardId(user.getMedicalCardId())
                .doctorEmail(doctorEmail)
                .build();
    }
}
