package Mingeso.Proyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.omg.CORBA.DATA_CONVERSION;

import javax.persistence.*;

import static org.aspectj.runtime.internal.Conversions.intValue;

@Data
@Entity
@Table(name = "variable")
public class Variable {

    private enum DataType {
        INT//,
        //FLOAT,
        //COMPLEX
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private Question question;

    @Column(nullable = false)
    private DataType dataType;

    private double lowerNumberLimit;

    private double upperNumberLimit;

    public Variable() {

    }

    public Variable (Question question, String name, int lowerNumberLimit, int upperNumberLimit, DataType dataType) {
        this.question = question;
        this.name = name;
        this.lowerNumberLimit = lowerNumberLimit;
        this.upperNumberLimit = upperNumberLimit;
        switch (intValue(dataType)) {
            case 0:
                this.dataType = DataType.INT;
                break;
            /*case 1:
                this.dataType = DataType.FLOAT;
        */}
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public double getLowerNumberLimit() {
        return lowerNumberLimit;
    }

    public void setLowerNumberLimit(double lowerNumberLimit) {
        this.lowerNumberLimit = lowerNumberLimit;
    }

    public double getUpperNumberLimit() {
        return upperNumberLimit;
    }

    public void setUpperNumberLimit(double upperNumberLimit) {
        this.upperNumberLimit = upperNumberLimit;
    }
}
