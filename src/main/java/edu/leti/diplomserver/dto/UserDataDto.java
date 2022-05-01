package edu.leti.diplomserver.dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {
    private String medicalCardId;
    private String email;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private String doctorEmail;
}