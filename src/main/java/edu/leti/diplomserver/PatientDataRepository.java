package edu.leti.diplomserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDataRepository extends JpaRepository<PatientData, Long> {
}
