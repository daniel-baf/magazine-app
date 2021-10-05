package Parsers.Gson;

import DB.Domain.Users.User;
import com.google.gson.Gson;

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

    public String getJsonFromUser(User user) {
        return this.gson.toJson(user);
    }
}
