package Mingeso.Proyecto.model;

import lombok.Data;


import javax.persistence.*;



@Data
@Entity
@Table(name = "pozo")
public class Pozo {
    @Id
    private int id;

    @Column(nullable = false)
    private int cantidadPreguntasQuiz;

    @Column(nullable = false)
    private int cantidadPreguntasPoso;

    private String descripcion;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidadPreguntasQuiz() {
        return cantidadPreguntasQuiz;
    }

    public void setCantidadPreguntasQuiz(int cantidadPreguntasQuiz) {
        this.cantidadPreguntasQuiz = cantidadPreguntasQuiz;
    }

    public int getCantidadPreguntasPoso() {
        return cantidadPreguntasPoso;
    }

    public void setCantidadPreguntasPoso(int cantidadPreguntasPoso) {
        this.cantidadPreguntasPoso = cantidadPreguntasPoso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
