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
@Table(name = "oks_results")
public class OKSResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "answer_1")
    private Short answer1;
    @Column(name = "answer_2")
    private Short answer2;
    @Column(name = "answer_3")
    private Short answer3;
    @Column(name = "answer_4")
    private Short answer4;
    @Column(name = "answer_5")
    private Short answer5;
    @Column(name = "answer_6")
    private Short answer6;
    @Column(name = "answer_7")
    private Short answer7;
    @Column(name = "answer_8")
    private Short answer8;
    @Column(name = "answer_9")
    private Short answer9;
    @Column(name = "answer_10")
    private Short answer10;
    @Column(name = "answer_11")
    private Short answer11;
    @Column(name = "answer_12")
    private Short answer12;
}
