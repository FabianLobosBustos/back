package Mingeso.Proyecto.utilities;

public class ConexionApiPythonFacade {

    PythonRun pythonRun;

    public String correrCodigoPython(String codigo){
        pythonRun = new PythonRun();
        return pythonRun.executeCode(codigo);
    }





}
