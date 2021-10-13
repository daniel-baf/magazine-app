package DB.DAOs.Users.Editor;

import DB.DBConnection;
import DB.Domain.Users.Editor;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This method contains all UPDATE actions from EDITOR on DATABASE
 *
 * @author jefemayoneso
 */
public class EditorUpdate {

    // SQL queries
    private String SQL_UPDATE_EDITOR = "UPDATE Editor SET name=?, password=?, description=? WHERE (email = ?);";

    /**
     * Update a <Editor> information
     *
     * @param editor
     * @return
     */
    public int update(Editor editor) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_UPDATE_EDITOR)) {
            configurePSUpdate(editor, ps);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to update EDITOR at [EditorUpdate] " + e.getMessage());
            return 0;
        }
    }

    /**
     * Configure the <Editor> information for <PreparedStatement>
     *
     * @param editor
     * @param ps
     * @throws SQLException
     */
    private void configurePSUpdate(Editor editor, PreparedStatement ps) throws SQLException {
        ps.setString(1, editor.getName());
        ps.setString(2, editor.getPassword());
        ps.setString(3, editor.getDescription());
        ps.setString(4, editor.getEmail());
    }
}
