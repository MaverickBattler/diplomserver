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
    private Byte answer1;
    @Column(name = "answer_2")
    private Byte answer2;
    @Column(name = "answer_3")
    private Byte answer3;
    @Column(name = "answer_4")
    private Byte answer4;
    @Column(name = "answer_5")
    private Byte answer5;
    @Column(name = "answer_6")
    private Byte answer6;
    @Column(name = "answer_7")
    private Byte answer7;
    @Column(name = "answer_8")
    private Byte answer8;
    @Column(name = "answer_9")
    private Byte answer9;
    @Column(name = "answer_10")
    private Byte answer10;
    @Column(name = "answer_11")
    private Byte answer11;
    @Column(name = "answer_12")
    private Byte answer12;
}
