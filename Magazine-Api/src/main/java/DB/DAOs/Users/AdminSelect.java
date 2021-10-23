package DB.DAOs.Users;

import DB.Domain.Users.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class select the Admin's id onformation from database
 *
 * @author jefemayoneso
 */
public class AdminSelect {

    // SQL queries
    private final String SQL_SELECT_ADMIN = "SELECT * FROM Magazine_Web.Admin WHERE email = ?";

    /**
     * select 1 admin from DB by email
     *
     * @param email
     * @return
     */
    public Admin select(String email) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_SELECT_ADMIN)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getAdminFromRs(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error trying to get user from DB at [DB.DAOs.Users.Admin].[AdminSelectDAO] " + e.getMessage());
        }
        return null;
    }

    /**
     * This method is a shared method, return Admin object from multiple methods
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Admin getAdminFromRs(ResultSet rs) throws SQLException {
        return new Admin(rs.getString("email"), rs.getString("password"), rs.getString("name"));
    }
}
