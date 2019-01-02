package Mingeso.Proyecto.model;

public class QuestionFactory {

    public static Question getQuestion()
    {
        //aca se puede arbitrar la creacion de una pregunta!
        return new Question();
    }
}
