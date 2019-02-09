package Mingeso.Proyecto.controller;

import Mingeso.Proyecto.model.Section;
import Mingeso.Proyecto.model.Student;
import Mingeso.Proyecto.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class SectionController {
    @Autowired
    SectionRepository sectionRepository;

    @GetMapping("Proyecto-Sprint2/section")
    @CrossOrigin(origins = "*")
    public List<Section> getAllSections(){
        return sectionRepository.findAll();
    }

    @GetMapping("Proyecto-Sprint2/section/student")
    @CrossOrigin(origins = "*")
    public List<Student> getAllStudentBySection(@PathVariable(value = "id") Long sectionId){
        return sectionRepository.findStudentsById(sectionId);
    }
}
