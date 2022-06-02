package edu.leti.diplomserver.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "[user]")
//Данному классу соответствует таблица user в БД
public class User {
    @Id
    @Column(name = "medical_card_id")
    private String medicalCardId; //Id медицинской карты пациента
    @Column(name = "email", unique = true, nullable = false)
    private String email; //Электронная почта
    @Column(name = "password", nullable = false)
    private String password; //Пароль
    @Column(name = "first_name")
    private String firstName; //Имя
    @Column(name = "last_name")
    private String lastName; //Фамилия
    @Column(name = "father_name")
    private String fatherName; //Отчество
    @Column(name = "phone_number")
    private String phoneNumber; //Номер телефона
    @CreatedDate
    @Column(name = "created")
    private Date created; //Дата и время создания пользователя
    @LastModifiedDate
    @Column(name = "updated")
    //Дата и время последнего изменения пользователя
    private Date updated;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    //Статус пользователя
    private Status status;
    //Отношение многие ко многим
    @ManyToMany(fetch = FetchType.EAGER)
    //Промежуточная таблица user_role
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "medical_card_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)})
    private List<Role> roles;
    @OneToMany(mappedBy = "user")
    //Ссылка на таблицу с результатами анкеты OKS
    private List<OksResult> oksResults;
}