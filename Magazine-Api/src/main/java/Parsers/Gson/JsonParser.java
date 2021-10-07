package Parsers.Gson;

import DB.Domain.Users.Admin;
import DB.Domain.Users.Editor;
import DB.Domain.Users.Reader;
import DB.Domain.Users.User;
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

//    public Admin getAdminFromJson(String jsonString) {
//        return this.gson.fromJson(jsonString, Admin.class);
//    }
//
//    public Editor getEditorFromJson(String jsonString) {
//        return this.gson.fromJson(jsonString, Editor.class);
//    }
//
//    public Reader getReaderFromJson(String jsonString) {
//        return this.gson.fromJson(jsonString, Reader.class);
//    }
//
//    public User getUserFromJson(String jsonString) {
//        return this.gson.fromJson(jsonString, User.class);
//    }
    public Object getUserFromObject(String jsonString, Type type) {
        return this.gson.fromJson(jsonString, type);
    }

}
