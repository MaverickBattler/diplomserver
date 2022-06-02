package edu.leti.diplomserver.repository;

import edu.leti.diplomserver.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Используется для операций с Role и таблицей role
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);
}
