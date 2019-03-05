package Mingeso.Proyecto.controller;

import Mingeso.Proyecto.model.*;
import Mingeso.Proyecto.repositories.*;
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

import java.util.List;


@CrossOrigin()
@Controller
@RequestMapping(path="Proyecto-Sprint2/quizalumno")
public class QuizAlumnoController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private VariableRepository variableRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private PozoRepository pozoRepository;

    @Autowired
    private QuizAlumnoRepository quizAlumnoRepository;

    @Autowired
    private  RespuestaCorrectaRepository respuestaCorrectaRepository;

    @Autowired
    private RespuestaAlumnoRepository respuestaAlumnoRepository;

    @CrossOrigin
    @PostMapping(path="/reviewQuiz")
    @ResponseBody
    public QuizAlumno reviewQuiz (@RequestBody QuizAlumno resource) {

        QuizAlumno quizAlumnoRevisado = new QuizAlumno();

        //Del resource nos llega la respuesta del alumno y los codigos!
        List<RespuestaAlumno> respuestaAlumno =resource.getRespuestaAlumno();
        List<CodeBody> codeBody =resource.getCodeBody();


        quizAlumnoRevisado.setRespuestaAlumno(respuestaAlumno);



        quizAlumnoRevisado.setCodeBody(codeBody);
        quizAlumnoRevisado.runCodes();
        quizAlumnoRevisado.calcularNota();
        quizAlumnoRepository.save(quizAlumnoRevisado);

        for (RespuestaAlumno var: quizAlumnoRevisado.getRespuestaAlumno()) {
            var.setQuizAlumno(quizAlumnoRevisado);
            respuestaAlumnoRepository.save(var);
        }

        for (RespuestaCorrecta var: quizAlumnoRevisado.getRespuestaCorrecta()) {
            var.setQuizAlumno(quizAlumnoRevisado);
            respuestaCorrectaRepository.save(var);
        }

        return quizAlumnoRevisado;

    }

}
