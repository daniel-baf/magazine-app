package Parsers;

import APIErrors.StringArrayMessage;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * This class convert a JSON to <object> who extends from User this <objects>
 * such as Admin, Reader or Editor
 *
 * @author jefemayoneso
 */
public class Parser {

    private Gson gson;

    public Parser() {
        gson = new Gson();
    }

    public Object getObjectFromJson(String jsonString, Type type) {
        return this.gson.fromJson(jsonString, type);
    }

    // recibir un Objeto, el tipo del objeto
    public String getJsonFromObject(Object src, Type typeOfSrc) {
        return this.gson.toJson(src, typeOfSrc);
    }

    /**
     * this method reads the BR from the FRONTEND and returns the string readed
     *
     * @param br
     * @return
     */
    public String getBody(BufferedReader br) {
        try {
            String body = "";
            String line = br.readLine();
            while (line != null) {
                body += line;
                line = br.readLine();
            }
            return body;
        } catch (IOException e) {
            System.out.println("Error parsing request string from buffered reader at [Parsers].[ReaderBR]\n" + e.getMessage());
            return null;
        }
    }
}
