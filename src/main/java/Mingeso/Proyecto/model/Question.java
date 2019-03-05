package Mingeso.Proyecto.model;


import Mingeso.Proyecto.utilities.ToPythonCodeContext;
import Mingeso.Proyecto.utilities.VariableEnteraToPythonCode;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question implements QuestionInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    @Column(length = 5000)
    private String codeBody;

    private int pozo;

    @Getter
    @OneToMany(mappedBy = "question",
            orphanRemoval = true,
            fetch = FetchType.LAZY)

    @JsonManagedReference
    private List<Variable> variables;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeBody() {
        return codeBody;
    }

    public void setCodeBody(String codeBody) {
        this.codeBody = codeBody;
    }

    public int getPozo() {
        return pozo;
    }

    public void setPozo(int pozo) {
        this.pozo = pozo;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    //Entra una pregunta y sale el codigo con variables aleatoreas
    @Override
    public String questionToCode(Question question){
        String code = new String();

        //USO DE STRATEGY
        ToPythonCodeContext context = new ToPythonCodeContext();
        context.setStrategy(new VariableEnteraToPythonCode());

        for (Variable var:
                question.getVariables()) {
            code += context.toPythonCode(var);
        }
        code += question.getCodeBody();


        return code;
    }
}
