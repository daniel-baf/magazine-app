package DB.DAOs.Users;

import DB.Domain.Users.Admin;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jefemayoneso
 */
public class AdminUpdate {

    // SQL queries
    private String SQL_UPDATE_ADMIN = "UPDATE Admin SET password=?, name =? WHERE (email=?)";

    /**
     * This method update any admin info at DATABASE
     *
     * @param admin OBject with info
     * @return int, with the number of rows affected
     */
    public int update(Admin admin) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_UPDATE_ADMIN)) {
            configurePSAdminUpdate(admin, ps);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to update ADMIN at [AdminUpdate] " + e.getMessage());
            return 0;
        }
    }

    /**
     * This method is a shared prepared statement configuration
     *
     * @param admin
     * @param ps
     * @throws SQLException
     */
    private void configurePSAdminUpdate(Admin admin, PreparedStatement ps) throws SQLException {
        ps.setString(1, admin.getPassword());
        ps.setString(2, admin.getName());
        ps.setString(3, admin.getEmail());
    }

}
