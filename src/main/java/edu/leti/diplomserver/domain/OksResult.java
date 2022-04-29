package edu.leti.diplomserver.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "oks_result")
public class OksResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @CreatedDate
    @Column(name = "completed")
    private Date completed;
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
