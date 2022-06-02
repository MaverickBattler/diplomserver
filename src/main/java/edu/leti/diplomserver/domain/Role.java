package edu.leti.diplomserver.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//Данному классу соответствует таблица role в БД
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    //Первичный ключ, генерируется автоматически по порядку по возрастанию
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Short id;
    @Column(name = "name", nullable = false)
    //Название роли
    String name;
    //Отношение многие ко многим, связь по полю roles класса User
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
