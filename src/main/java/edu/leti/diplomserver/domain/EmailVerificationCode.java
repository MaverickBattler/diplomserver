package edu.leti.diplomserver.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "email_verification_codes")
public class EmailVerificationCode {
    @Id
    @Column(name = "medical_card_id")
    String medicalCardId;
    @Column(name = "code", unique = true)
    String code;
    @Column(name = "verified")
    Boolean verified;
}
