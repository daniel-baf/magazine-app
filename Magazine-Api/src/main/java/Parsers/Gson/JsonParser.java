package Parsers.Gson;

import DB.Domain.Users.Admin;
import com.google.gson.Gson;

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

    /**
     * return the admin object from JSON file as string
     *
     * @param jsonString
     * @return
     */
    public Admin getAdminFromJson(String jsonString) {
        return this.gson.fromJson(jsonString, Admin.class);
    }

}
