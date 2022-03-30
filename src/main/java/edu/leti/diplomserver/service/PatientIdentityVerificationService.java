package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.EmailVerificationCode;
import edu.leti.diplomserver.domain.Patient;
import edu.leti.diplomserver.dto.VerificationRequestDto;
import edu.leti.diplomserver.repository.EmailVerificationCodeRepository;
import edu.leti.diplomserver.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

@Service
public class PatientIdentityVerificationService {

    private final EmailService emailService;
    private final EmailVerificationCodeRepository emailVerificationCodeRepository;
    private final UserRepository userRepository;

    ArrayList<Patient> patients;

    public PatientIdentityVerificationService(EmailService emailService,
                                              EmailVerificationCodeRepository emailVerificationCodeRepository,
                                              UserRepository userRepository) {
        this.emailService = emailService;
        this.emailVerificationCodeRepository = emailVerificationCodeRepository;
        this.userRepository = userRepository;

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

    public String verificationRequest(VerificationRequestDto verificationRequestDto) {

        for (Patient patient : patients) {
            String medicalCardId = patient.getMedicalCardId();
            if (medicalCardId.equals(verificationRequestDto.getMedicalCardId())
                    && patient.getOperationName().equals("Эндопротезирование коленного сустава")) {
                if (userRepository.findById(medicalCardId).isPresent()) {
                    //если пользователь уже зарегистрирован (есть в UserRepository)
                    return "registered";
                } else if (emailVerificationCodeRepository.findById(medicalCardId).isPresent()) {
                    if (emailVerificationCodeRepository.getById(medicalCardId).getVerified()) {
                        //если пользователь уже подтвердил свою электронную почту,
                        // и ему необходимо зарегистрироваться
                        return "verified";
                    }
                }
                //если пользователь не подтвердил свою электронную почту
                byte[] array = new byte[10];
                new Random().nextBytes(array);//generating a random string
                String generatedString = new String(array, StandardCharsets.UTF_8);
                String stringHash = DigestUtils.sha256Hex(patient.getMedicalCardId() + generatedString);
                String patientEmail = patient.getEmail();
                final String baseUrl =
                        ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                String fullUrl = baseUrl + "/verify-email?code=" + stringHash;
                String fullMessage = "        Здравствуйте, " + patient.getFirstName() + " " +
                        patient.getFatherName() + "!\n\n" +
                        "        Это письмо отправлено вам для подтверждения того, что вы пациент, желающий " +
                        "зарегистрироваться в приложении KneeApp. Для подтверждения нажмите " +
                        "на ссылку ниже:\n" + fullUrl + "\n\n        После нажатия на ссылку, " +
                        "вернитесь в приложение и введите ID медицинской карты еще раз." +
                        "\n\n        Если вы не вводили ID своей медицинской карты в приложение, " +
                        "то проигнорируйте данное сообщение.";
                emailService.sendSimpleMessage(patientEmail,
                        "Подтверждение адреса электронной почты пациента", fullMessage);
                emailVerificationCodeRepository.save(EmailVerificationCode.builder()
                        .medicalCardId(patient.getMedicalCardId()).code(stringHash).verified(false).build());
                return patientEmail;
            }
        }
        return null;
    }

    public void verifyEmail(String code) {
        EmailVerificationCode emailVerificationCode = emailVerificationCodeRepository.findByCode(code);
        if (emailVerificationCode != null) {
            String medicalCardId = emailVerificationCode.getMedicalCardId();
            for (Patient patient : patients) {
                if (patient.getMedicalCardId().equals(medicalCardId)) {
                    emailVerificationCode.setVerified(true);
                    emailVerificationCodeRepository.save(emailVerificationCode);
                }
            }
        }
    }
}
