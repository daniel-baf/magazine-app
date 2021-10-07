package DB.DAOs.Users.Editor;

import DB.DAOs.Users.UserCommonDAO;
import DB.Domain.Users.Editor;
import ENUMS.DAOResults;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class contorl all inserts for Editor
 *
 * @author jefemayoneso
 */
public class EditorInsert {

    private String SQL_INSERT_EDITOR = "INSERT INTO Editor (email, name, password, description) VALUES (?, ?, ?, ?)";

    /**
     * insert an Editor on DB
     *
     * @param editor
     * @return
     */
    public int insert(Editor editor) {
        if (new UserCommonDAO().emailRegisted(editor.getEmail()) == null) { // verify if exist at any table
            try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_EDITOR)) {
                configurePS(editor, ps);
                return ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error trying to insert EDITOR at [DB.DAOs.Users.Reader].[ReaderInsert] " + e.getMessage());
                return 0;
            }
        }
        return DAOResults.EMAIL_IN_USE.getCode(); // -1 means the email is registed
    }

    /**
     * creates a object Editor from PS with ps.getAttibute
     *
     * @param editor
     * @param ps
     * @throws SQLException
     */
    private void configurePS(Editor editor, PreparedStatement ps) throws SQLException {
        ps.setString(1, editor.getEmail());
        ps.setString(2, editor.getName());
        ps.setString(3, editor.getPassword());
        ps.setString(4, editor.getDescription());
    }
}
