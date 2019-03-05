package Mingeso.Proyecto.controller;

import Mingeso.Proyecto.model.Pozo;
import Mingeso.Proyecto.model.Question;
import Mingeso.Proyecto.repositories.PozoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin()
@Controller
@RequestMapping(path="Proyecto-Sprint2/pozo")
public class PozoController {

    @Autowired
    private PozoRepository pozoRepository;

    @CrossOrigin
    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<Pozo> getAllPozos() {
        return pozoRepository.findAll();
    }

    @CrossOrigin
    @PutMapping(path = "/{idPozo}")
    @ResponseBody
    public ResponseEntity<Pozo> updatePozo(@RequestBody Pozo resource, @PathVariable int idPozo) {
        Pozo pozo = pozoRepository.findPozoById(idPozo);
        pozo.setCantidadPreguntasQuiz(resource.getCantidadPreguntasQuiz());
        pozo.setDescripcion(resource.getDescripcion());
        pozoRepository.save(pozo);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping(path="/add")
    @ResponseBody
    public ResponseEntity<Pozo> addPozo (@RequestBody Pozo resource) {
        Pozo newPozo = new Pozo();
        newPozo.setDescripcion(resource.getDescripcion());
        newPozo.setCantidadPreguntasQuiz(resource.getCantidadPreguntasQuiz());
        newPozo.setCantidadPreguntasPoso(resource.getCantidadPreguntasPoso());

        Iterable<Pozo> pozos = pozoRepository.findAll();
        long idPozo = pozos.spliterator().getExactSizeIfKnown();
        newPozo.setId((int)idPozo + 1);

        System.out.println((int)idPozo + 1);
        pozoRepository.save(newPozo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


