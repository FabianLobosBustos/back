package Mingeso.Proyecto.utilities;

import Mingeso.Proyecto.model.Question;
import Mingeso.Proyecto.model.Variable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class SingletonQuestionValidator {
    private static final SingletonQuestionValidator INSTANCE = new SingletonQuestionValidator();

    private SingletonQuestionValidator() {}

    public static SingletonQuestionValidator getInstance() {
        return INSTANCE;
    }

    //Evaluación lunes 3 de diciembre
    public boolean validator(Question question) {  //añadir validacion de si corre
        if (validarBuild(question) && validateCodeBody(question.getCodeBody()) &&
            validatePozo(question.getPozo())) {
            for (Variable var:
                 question.getVariables()) {
                if (!validateVariable(var)) {
                    System.out.println("false 1");

                    return false;
                }
            }
            System.out.println("estamos tate");

            return true;
        }
        System.out.println(question.getCodeBody().length());
        System.out.println("false 2");

        return false;
    }

    private boolean validateShortString(String string) {

        return (string.length() > 0 && string.length() <= 255);
    }

    private boolean validateCodeBody(String codeBody) {
        if(codeBody.length() > 0 && codeBody.length() <= 5000){
            System.out.println("codeBody error");

        }
        return (codeBody.length() > 0 && codeBody.length() <= 5000);
    }

    private boolean validatePozo(int pozo) {

        if (pozo >= 1){
            System.out.println("pozo error");

        }
        return pozo >= 1;
    }

    private boolean validateVariable(Variable variable) {

        return validateShortString(variable.getName()) &&
                validateRange(variable.getLowerNumberLimit(), variable.getUpperNumberLimit());
    }

    private boolean validateRange(double lowerBound, double upperBound) {
        return lowerBound <= upperBound;
    }

    //por el momento los build validan solo variables enteras
    private boolean validarBuild(Question input) {
        String code = new String();
        ToPythonCodeContext context = new ToPythonCodeContext();
        context.setStrategy(new VariableEnteraToPythonCode());

        for (Variable var:
                input.getVariables()) {
            code += context.toPythonCode(var);
        }
        code += input.getCodeBody();
        System.out.println(code);

        ConexionApiPythonFacade cont = new ConexionApiPythonFacade();
        String result = cont.correrCodigoPython(code);
        JSONObject json = null;
        try {
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(result);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(json.get("error"));
        if(json.get("error").equals("")){
            System.out.println(json.get("error"));
            return true;
        }
        else{
            System.out.println(json.get("error"));
            return false;
        }
    }
}
