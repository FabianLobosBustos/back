package Mingeso.Proyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "respuestaAlumno")
public class RespuestaAlumno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 5000)
    private String respuesta;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "quizalumno_id", nullable = false)
    @JsonBackReference
    private QuizAlumno quizAlumno;


    public int getId() {
        return id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void setQuizAlumno(QuizAlumno quizAlumno) {
        this.quizAlumno = quizAlumno;
    }
}

