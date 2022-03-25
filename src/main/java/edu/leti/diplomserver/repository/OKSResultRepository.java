package edu.leti.diplomserver.repository;

import edu.leti.diplomserver.domain.OKSResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OKSResultRepository extends JpaRepository<OKSResult, Long> {
}
