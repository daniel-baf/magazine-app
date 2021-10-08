package Parsers.Gson;

import com.google.gson.Gson;
import java.lang.reflect.Type;

/**
 * This class convert a JSON to <object> who extends from User this <objects>
 * such as Admin, Reader or Editor
 *
 * @author jefemayoneso
 */
public class JsonParser {

    private Gson gson;

    public JsonParser() {
        gson = new Gson();
    }

    public Object getObjectFromJson(String jsonString, Type type) {
        return this.gson.fromJson(jsonString, type);
    }

}
