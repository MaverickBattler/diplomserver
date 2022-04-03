package edu.leti.diplomserver.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}