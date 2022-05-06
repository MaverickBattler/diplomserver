package edu.leti.diplomserver.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @CreatedDate
    @Column(name = "started")
    private Date started;
    @LastModifiedDate
    @Column(name = "finished")
    private Date finished;
    @Column(name = "exercise_1")
    private Boolean exercise1;
    @Column(name = "exercise_2")
    private Boolean exercise2;
    @Column(name = "exercise_3")
    private Boolean exercise3;
    @Column(name = "exercise_4")
    private Boolean exercise4;
    @Column(name = "exercise_5")
    private Boolean exercise5;
    @Column(name = "exercise_6")
    private Boolean exercise6;
    @Column(name = "exercise_7")
    private Boolean exercise7;
    @Column(name = "exercise_8")
    private Boolean exercise8;
    @Column(name = "exercise_9")
    private Boolean exercise9;
    @Column(name = "exercise_10")
    private Boolean exercise10;
    @Column(name = "exercise_11")
    private Boolean exercise11;
    @Column(name = "exercise_12")
    private Boolean exercise12;
    @Column(name = "exercise_13")
    private Boolean exercise13;
}
