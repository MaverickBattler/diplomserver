package edu.leti.diplomserver.domain;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
//Имитация данных о пациенте, хранящихся в базе данных медицинского учреждения
public class Patient {
    private String medicalCardId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
    private String phoneNumber;
    private String operationName;
}
