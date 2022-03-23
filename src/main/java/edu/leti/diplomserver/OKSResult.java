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
    @Column(name = "oks_result_id")
    private Long oksResultId;
    @Column(name = "answer1")
    private Integer answer1;
    @Column(name = "answer2")
    private Integer answer2;
    @Column(name = "answer3")
    private Integer answer3;
    @Column(name = "answer4")
    private Integer answer4;
    @Column(name = "answer5")
    private Integer answer5;
    @Column(name = "answer6")
    private Integer answer6;
    @Column(name = "answer7")
    private Integer answer7;
    @Column(name = "answer8")
    private Integer answer8;
    @Column(name = "answer9")
    private Integer answer9;
    @Column(name = "answer10")
    private Integer answer10;
    @Column(name = "answer11")
    private Integer answer11;
    @Column(name = "answer12")
    private Integer answer12;
}
