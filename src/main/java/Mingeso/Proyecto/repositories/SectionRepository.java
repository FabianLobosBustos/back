package Mingeso.Proyecto.repositories;

import Mingeso.Proyecto.model.Section;
import Mingeso.Proyecto.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Student> findStudentsById(Long id);
    void saveAll(List<Section> sections);
}
