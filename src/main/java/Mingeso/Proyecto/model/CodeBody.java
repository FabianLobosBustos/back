package Mingeso.Proyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "codeBody")
public class CodeBody {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 5000)
    private String code;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "quizalumno_id", nullable = false)
    @JsonBackReference
    private QuizAlumno quizAlumno;
}
