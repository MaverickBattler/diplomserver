package edu.leti.diplomserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

//Сервис, в котором представлены методы, связанные отправкой электронной почты
@Component
public class EmailService {

    private final JavaMailSender emailSender;

    String emailFrom;
    //Constructor Dependency Injection
    public EmailService(JavaMailSender emailSender,
                        @Value("${spring.mail.username}") String emailFrom) {
        this.emailSender = emailSender;
        this.emailFrom = emailFrom;
    }
    //Отправка сообщения от emailFrom получателю to с темой subject и текстом text
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }
}
