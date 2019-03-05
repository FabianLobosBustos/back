package Mingeso.Proyecto.controller;

import Mingeso.Proyecto.model.Pozo;
import Mingeso.Proyecto.model.Question;
import Mingeso.Proyecto.model.QuestionFactory;
import Mingeso.Proyecto.model.Variable;
import Mingeso.Proyecto.repositories.PozoRepository;
import Mingeso.Proyecto.repositories.QuestionRepository;
import Mingeso.Proyecto.repositories.QuizRepository;
import Mingeso.Proyecto.repositories.VariableRepository;
import Mingeso.Proyecto.utilities.PythonRun;
import Mingeso.Proyecto.utilities.SingletonQuestionValidator;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;


@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path="Proyecto-Sprint2/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private VariableRepository variableRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private PozoRepository pozoRepository;


    @GetMapping(path="/all")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Iterable<Question> getAllQuestions () {

        return questionRepository.findAll();
    }

    @PostMapping(path="/add")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<Question> addQuestion (@RequestBody Question resource) {

        if (!SingletonQuestionValidator.getInstance().validator(resource)) {

            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        //USO DE FACTORY!
        Question newQuestion = QuestionFactory.getQuestion();
        newQuestion.setCodeBody(resource.getCodeBody());
        newQuestion.setPozo(resource.getPozo());

        for (Variable var: resource.getVariables()) {

            var.setQuestion(newQuestion);
            variableRepository.save(var);
        }
        //Aqui aumentamos en 1 el numero de preguntas en el pozo
        Pozo pozo = pozoRepository.findPozoById(resource.getPozo());
        pozo.setCantidadPreguntasPoso(pozo.getCantidadPreguntasPoso() + 1);
        pozoRepository.save(pozo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
