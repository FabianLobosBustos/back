package Mingeso.Proyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Getter
    @OneToMany(mappedBy = "id",   // o question_id
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
