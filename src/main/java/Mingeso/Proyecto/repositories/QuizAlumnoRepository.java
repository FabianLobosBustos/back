package Mingeso.Proyecto.repositories;

import Mingeso.Proyecto.model.Question;
import Mingeso.Proyecto.model.QuizAlumno;
import org.springframework.data.repository.CrudRepository;


public interface QuizAlumnoRepository extends CrudRepository<QuizAlumno, Long> {

    QuizAlumno findQuizAlumnoById(Long id);
}
