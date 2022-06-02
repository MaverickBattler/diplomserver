package edu.leti.diplomserver.repository;

import edu.leti.diplomserver.domain.OksResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Используется для операций с OksResult и таблицей oks_result
@Repository
public interface OksResultRepository extends JpaRepository<OksResult, Long> {
}
