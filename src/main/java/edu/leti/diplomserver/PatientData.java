package edu.leti.diplomserver;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient_data")
public class PatientData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long patientId;
    @Column(name = "q1")
    private Integer answer1;
    @Column(name = "q2")
    private Integer answer2;
    @Column(name = "q3")
    private Integer answer3;
    @Column(name = "q4")
    private Integer answer4;
    @Column(name = "q5")
    private Integer answer5;
    @Column(name = "q6")
    private Integer answer6;
    @Column(name = "q7")
    private Integer answer7;
    @Column(name = "q8")
    private Integer answer8;
    @Column(name = "q9")
    private Integer answer9;
    @Column(name = "q10")
    private Integer answer10;
    @Column(name = "q11")
    private Integer answer11;
    @Column(name = "q12")
    private Integer answer12;
}
