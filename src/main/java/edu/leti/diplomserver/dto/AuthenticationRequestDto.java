package edu.leti.diplomserver.dto;

import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {
    String email;
    String password;
}
