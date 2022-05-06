package edu.leti.diplomserver.dto;

import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {
    private String email;
    private Short exerciseNumber;
    private Long lastOks;
}
