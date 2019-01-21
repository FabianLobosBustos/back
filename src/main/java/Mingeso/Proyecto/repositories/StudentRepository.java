package Mingeso.Proyecto.repositories;

import Mingeso.Proyecto.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student, Long> {

}
