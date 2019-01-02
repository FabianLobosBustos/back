package Mingeso.Proyecto.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class APITest {
    private static APIData apiData = new APIData("application/json","Token 9304210f-1e0b-4704-a479-36b5cab8bb75");
    public static String executeCode(URL url, String input)
    {
        try {

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", apiData.getContentTypeData());
            connection.setRequestProperty("authorization", apiData.getApiTokenData());


            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Input error when sending code to glot.io API : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output = bufferedReader.readLine();
            connection.disconnect();
            return output;

        }
        catch (IOException  e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}