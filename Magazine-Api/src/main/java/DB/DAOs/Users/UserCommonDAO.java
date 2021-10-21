package DB.DAOs.Users;

import DB.DAOs.Users.Admin.AdminSelect;
import DB.DAOs.Users.Editor.EditorSelect;
import DB.DAOs.Users.Reader.ReaderSelect;
import DB.DBConnection;
import DB.Domain.Users.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This is a class to add methods common for AdminDAO, EditorDAO and ReaderDAO
 *
 * @author jefemayoneso
 */
public class UserCommonDAO {

    private String SQL_GET_IMG_PATH;

    /**
     * check if email is already in use
     *
     * @param email
     * @return
     */
    public User emailRegisted(String email) {
        User user = new AdminSelect().select(email); // SEARCH admin
        if (user != null) {
            user.setType("ADMIN");
            return user;
        }
        user = new EditorSelect().select(email); // search editor
        if (user != null) {
            user.setType("EDITOR");
            return user;
        }
        user = new ReaderSelect().select(email); // search for reader
        if (user != null) {
            user.setType("READER");
            return user;
        }
        return null;
    }
}
