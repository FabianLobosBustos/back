package Mingeso.Proyecto.repositories;

import Mingeso.Proyecto.model.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    public Quiz findQuizById(int id);
}
