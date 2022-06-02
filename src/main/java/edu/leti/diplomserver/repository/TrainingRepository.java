package edu.leti.diplomserver.repository;

import edu.leti.diplomserver.domain.Training;
import edu.leti.diplomserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Используется для операций с Training и таблицей training
@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findAllByUser(User user);
}