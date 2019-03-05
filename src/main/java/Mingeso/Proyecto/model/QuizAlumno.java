package Mingeso.Proyecto.model;


import Mingeso.Proyecto.utilities.ConexionApiPythonFacade;
import Mingeso.Proyecto.utilities.PythonRun;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "quizalumno")
public class QuizAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //por ahora no se utiliza!!!
    private int idAlumno;

    private int pozo;
    //private List<String> codeBody = new ArrayList<>();
    @Getter
    @OneToMany(mappedBy = "quizAlumno",
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CodeBody> codeBody = new ArrayList<>();

    @Getter
    @OneToMany(mappedBy = "quizAlumno",
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RespuestaAlumno> respuestaAlumno = new ArrayList<>();

    @Getter
    @OneToMany(mappedBy = "quizAlumno",
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RespuestaCorrecta> respuestaCorrecta = new ArrayList<>();
    private double nota;

    //corre los codeBodies y los guarda en ese orden
    public void runCodes(){
        for(int i = 0; i<codeBody.size();i++){

            CodeBody code = this.codeBody.get(i);

            String codeGet = code.getCode().replaceAll("\n", "\n");
            //USO DEL FACADE!!!!
            ConexionApiPythonFacade cont = new ConexionApiPythonFacade();
            String result = cont.correrCodigoPython(codeGet);


            System.out.println("lo impreso es... \n\n\n");
            System.out.println(codeGet);


            JSONObject json = null;
            try {
                JSONParser parser = new JSONParser();
                json = (JSONObject) parser.parse(result);


            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(json);
            String respuestaCodeBody = (String) json.get("stdout");
            System.out.println("------");
            System.out.println(json.get("stdout"));
            RespuestaCorrecta resp = new RespuestaCorrecta();
            resp.setRespuesta(respuestaCodeBody);
            respuestaCorrecta.add(resp);
        }
    }

    public void calcularNota(){
        int buenas = 0;
        int malas = 0;
        for(int i = 0; i<codeBody.size();i++){
            System.out.println(respuestaCorrecta.get(i).getRespuesta());
            System.out.println("***************");
            System.out.println(respuestaAlumno.get(i).getRespuesta());
            String respCorrecta = respuestaCorrecta.get(i).getRespuesta();
            String respAlumno = respuestaAlumno.get(i).getRespuesta() + "\n";
            if(respCorrecta.equals(respAlumno)){
              buenas++;
          }else {
              malas ++;
          }
        }


        this.nota = ((buenas*7)+(malas*1)) /codeBody.size();
    }

    public Long getId() {
        return id;
    }
    
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getPozo() {
        return pozo;
    }

    public void setPozo(int pozo) {
        this.pozo = pozo;
    }

    public List<CodeBody> getCodeBody() {
        return codeBody;
    }

    public void setCodeBody(List<CodeBody> codeBody) {
        this.codeBody = codeBody;
    }

    public List<RespuestaAlumno> getRespuestaAlumno() {
        return respuestaAlumno;
    }

    public void setRespuestaAlumno(List<RespuestaAlumno> respuestaAlumno) {
        this.respuestaAlumno = respuestaAlumno;
    }

    public List<RespuestaCorrecta> getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(List<RespuestaCorrecta> respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
