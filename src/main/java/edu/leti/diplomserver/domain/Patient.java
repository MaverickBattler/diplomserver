package edu.leti.diplomserver.domain;

import lombok.*;

//Имитация таблицы с данными о пациенте, хранящейся в базе данных медицинского учреждения
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private String medicalCardId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
    private String phoneNumber;
    private String operationName;
    private String doctorEmail;
}
