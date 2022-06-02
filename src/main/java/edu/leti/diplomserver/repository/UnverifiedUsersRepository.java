package edu.leti.diplomserver.repository;

import edu.leti.diplomserver.domain.UnverifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//Используется для операций с UnverifiedUser и таблицей unverified_user
@Repository
public interface UnverifiedUsersRepository extends JpaRepository<UnverifiedUser, String> {
    UnverifiedUser findByCode(String code);
    @Transactional
    void deleteByMedicalCardId(String medicalCardId);
}
