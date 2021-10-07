package Parsers.Gson;

import com.google.gson.Gson;
import java.lang.reflect.Type;

/**
 * THis class transform an object to JSON
 *
 * @author jefemayoneso
 */
public class ObjectParser {

    Gson gson;

    public ObjectParser() {
        this.gson = new Gson();
    }

    // recibir un Objeto, el tipo del objeto
    public String getJsonFromObject(Object src, Type typeOfSrc) {
        return this.gson.toJson(src, typeOfSrc);
    }

}
