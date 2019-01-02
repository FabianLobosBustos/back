package Mingeso.Proyecto.repositories;

import Mingeso.Proyecto.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    Question findQuestionById(int id);
    ArrayList<Question> findQuestionByPozo(int pozo);


}
