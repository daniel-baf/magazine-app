package DB.DAOs.Users.Editor;

import DB.Domain.Users.Editor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This method access to DB to get info from table User
 *
 * @author jefemayoneso
 */
public class EditorSelect {
    
    private final String SQL_SELECT_EDITOR = "SELECT * FROM Editor WHERE email = ?";

    /**
     * This method get all the info at DB from any Editor by email
     *
     * @param email
     * @return
     */
    public Editor select(String email) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_SELECT_EDITOR)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getEditorFromRs(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error trying to get editor from DB at [DB.DAOs.Users.Editor].[EditorSelectDAO] " + e.getMessage());
        }
        return null;
    }

    /**
     * Get the <Editor> info from <PreparedStatement>
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Editor getEditorFromRs(ResultSet rs) throws SQLException {
        return new Editor(rs.getString("email"), rs.getString("password"), rs.getString("name"), rs.getString("description"), rs.getString("imgPath"));
    }
}
