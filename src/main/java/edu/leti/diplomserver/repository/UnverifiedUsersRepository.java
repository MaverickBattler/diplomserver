package edu.leti.diplomserver.repository;

import edu.leti.diplomserver.domain.UnverifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnverifiedUsersRepository extends JpaRepository<UnverifiedUser, String> {
    UnverifiedUser findByCode(String code);
    void deleteByMedicalCardId(String medicalCardId);
}
