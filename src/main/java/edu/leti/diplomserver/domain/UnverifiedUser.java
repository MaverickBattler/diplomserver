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
@Table(name = "unverified_users")
public class UnverifiedUser {
    @Column(name = "medical_card_id")
    String medicalCardId;
    @Id
    @Column(name = "code", unique = true)
    String code;
    @Column(name = "password")
    String password;
}