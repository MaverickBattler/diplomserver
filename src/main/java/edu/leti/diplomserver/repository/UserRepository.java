package edu.leti.diplomserver.repository;

import edu.leti.diplomserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Используется для операций с User и таблицей user
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //Найти пользователя по адресу его электронной почты
    User findByEmail(String email);
}
