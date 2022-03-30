package edu.leti.diplomserver.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VerificationRequestDto {
    private String medicalCardId;
}
