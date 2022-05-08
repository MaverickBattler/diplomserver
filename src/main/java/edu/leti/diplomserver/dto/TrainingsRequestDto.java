package edu.leti.diplomserver.dto;

import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainingsRequestDto {
    private String email;
    private Long lastOks;
}
