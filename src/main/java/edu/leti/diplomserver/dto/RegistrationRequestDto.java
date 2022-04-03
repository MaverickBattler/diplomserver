package edu.leti.diplomserver.dto;

import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {
    String medicalCardId;
    String password;
}
