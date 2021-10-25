package DB.DAOs.Users;

import BackendUtilities.FileWriterCP;
import DB.DBConnection;
import DB.Domain.Users.Editor;
import DB.GeneralPaths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Part;

/**
 * This class contorl all inserts for Editor
 *
 * @author jefemayoneso
 */
public class EditorInsert {

    private String SQL_INSERT_EDITOR = "INSERT INTO Editor (email, name, password, description, imgPath) VALUES (?, ?, ?, ?, ?)";

    /**
     * insert an Editor on DB
     *
     * @param editor
     * @param part
     * @return
     */
    public int insert(Editor editor, Part part, String absolutePath) {
        int result = 0;
        if (new UserCommonDAO().emailRegisted(editor.getEmail()) == null) { // verify if exist at any table
            try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_EDITOR)) {
                DBConnection.getConnection().setAutoCommit(false);
                FileWriterCP fwcp = new FileWriterCP();
                editor.setImgPath(GeneralPaths.FILES_IMG_PATH_PROF_EDITOR.getMessage() + editor.getEmail());
                configurePS(editor, ps);
                if (ps.executeUpdate() != 0) {
                    // save file to disk
                    result = fwcp.write(part, editor.getImgPath(), absolutePath) ? 1 : 0;
                }
            } catch (SQLException e) {
                System.out.println("Error trying to insert EDITOR at [DB.DAOs.Users.Reader].[ReaderInsert] " + e.getMessage());
                return 0;
            } finally {
                try {
                    if (result == 0) {
                        DBConnection.getConnection().rollback();
                    }
                    DBConnection.getConnection().setAutoCommit(true);
                } catch (SQLException ex) {
                    System.out.println("Error while rollback at [EditorInsert] " + ex.getMessage());
                }
            }
        } else {
            result = GeneralPaths.EMAIL_IN_USE.getCode(); // -1 means the email is registed
        }
        return result;
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
        ps.setString(5, editor.getImgPath());
    }
}
