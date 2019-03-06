package Mingeso.Proyecto.controller;

import Mingeso.Proyecto.model.Section;
import Mingeso.Proyecto.model.Student;
import Mingeso.Proyecto.repositories.SectionRepository;
import Mingeso.Proyecto.utilities.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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

    @PostMapping(value = "/upload", consumes = "text/csv")
    @CrossOrigin(origins = "*")
    public void uploadSimple(@RequestBody InputStream body) {
        sectionRepository.saveAll(CsvUtils.read(Section.class, body);
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @CrossOrigin(origins = "*")
    public void uploadMultipart(@RequestParam("file") MultipartFile file) {
        sectionRepository.saveAll(CsvUtils.read(Section.class, file.getInputStream());
    }
}
