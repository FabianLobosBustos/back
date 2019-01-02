package Mingeso.Proyecto.utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class PythonRun {

    public String executeCode(String code) {
        URL url = null;
        try {
            url = new URL("https://run.glot.io/languages/python/latest");
        }
        catch (MalformedURLException e) {
            return null;
        }
        String input = "{\"files\": [{\"name\" : \"main.py\", \"content\": \"" + code +"\"}]}";
        return APITest.executeCode(url, input);
    }
}
