package edu.leti.diplomserver.repository;

import edu.leti.diplomserver.domain.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationCodeRepository extends JpaRepository<EmailVerificationCode, String> {
    EmailVerificationCode findByCode(String code);
}
