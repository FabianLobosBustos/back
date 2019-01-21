package Mingeso.Proyecto.repositories;

import Mingeso.Proyecto.model.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    public Quiz findQuizById(int id);
}
