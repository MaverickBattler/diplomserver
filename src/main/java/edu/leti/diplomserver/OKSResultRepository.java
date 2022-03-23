package edu.leti.diplomserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OKSResultRepository extends JpaRepository<OKSResult, Long> {
}
