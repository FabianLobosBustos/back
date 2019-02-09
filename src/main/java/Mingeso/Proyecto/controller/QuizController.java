package Mingeso.Proyecto.controller;


import Mingeso.Proyecto.model.Pozo;
import Mingeso.Proyecto.model.Question;
import Mingeso.Proyecto.model.Quiz;
import Mingeso.Proyecto.model.Section;
import Mingeso.Proyecto.repositories.PozoRepository;
import Mingeso.Proyecto.repositories.QuestionRepository;
import Mingeso.Proyecto.repositories.QuizRepository;
import Mingeso.Proyecto.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin()
@Controller
@RequestMapping(path="Proyecto-Sprint2/quiz")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PozoRepository pozoRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @CrossOrigin
    @GetMapping(path="/all")
    @ResponseBody
    public Iterable<Quiz> getAllQuizzes () {
        return quizRepository.findAll();
    }

    @CrossOrigin
    @PostMapping(path="/add")
    @ResponseBody
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz newQuiz){
        Quiz quiz = new Quiz();
        return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.OK);
    }

    //Retorna una id asociada a un pozo especifico
    @CrossOrigin
    @RequestMapping(value="/{id_pozo}",method = RequestMethod.GET)
    @ResponseBody
    public Quiz getQuiz(@PathVariable("id_pozo") Integer id_pozo) {


        //OBTENER NUMERO DE PREGUNTAS A OBTENER (DEL POZO)
        ArrayList<Question> preguntas;
        Quiz quiz = new Quiz();

        preguntas = questionRepository.findQuestionByPozo(id_pozo);


        int i;

        //Esto se obtendra de la clase pozo!!!
        Pozo pozo = pozoRepository.findPozoById(id_pozo);


        int cantidadPreguntasQuiz = pozo.getCantidadPreguntasQuiz();

        System.out.println(cantidadPreguntasQuiz);
        Random aleatoreo = new Random(System.currentTimeMillis());

        ArrayList<Question> preguntasQuiz = new ArrayList();

        for (i = 0; i<cantidadPreguntasQuiz; i++){
            int repetido = 0;
            int PosicionPreguntaAleatorea = aleatoreo.nextInt(preguntas.size());
            Question seleccionada = preguntas.get(PosicionPreguntaAleatorea);

            //se transforma la pregunta a codigo y se instancia variables
            String codigoPregunta = seleccionada.questionToCode(seleccionada);
            //se cambia el \n por </br>
            codigoPregunta = codigoPregunta.replaceAll("\n", "\n");

            //Se verifica que la pregunta no se halla sacado con anterioridad:
            for(Iterator<Question> iterator =  preguntasQuiz.iterator(); iterator.hasNext();){
                Question elemento = iterator.next();
                if (elemento.getId() == seleccionada.getId()) {
                    repetido = 1;
                    break;
                }
            }
            if(repetido == 1){
                i = i -1;
            }
            else {
                seleccionada.setCodeBody(codigoPregunta);
                preguntasQuiz.add(seleccionada);
                System.out.println(seleccionada.getId());
            }
        }
        quiz.setQuestions(preguntasQuiz);
        return quiz;

        //picolinosdad
    }


    @CrossOrigin
    @GetMapping(path="/section/{id}")
    public List<Quiz> getQuizzesOfSectionTime (@PathVariable(value = "id") Long SectionId) {
        Section section = sectionRepository.getOne(SectionId);
        Date date = new Date();
        DateFormat hourDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        System.out.println("Hora y fecha: "+hourDateFormat.format(date));
        return null;
    }

}
