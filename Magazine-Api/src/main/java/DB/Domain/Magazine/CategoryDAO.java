package DB.Domain.Magazine;

import DB.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class work as data access object from categories at DATABASE
 *
 * @author jefemayoneso
 */
public class CategoryDAO {

    private String SQL_SELECT_CATEGORIES = "SELECT * FROM Category";
    private String SQL_SELECT_USER_CATEGORIES = "SELECT * FROM User_Intrest_Categories WHERE reader = ?";

    /**
     * This method select all tags on DB
     *
     * @param byUser true if search by user
     * @param user the user email if wants by email
     * @return
     */
    public ArrayList<String> select(boolean byUser, String user) {
        ArrayList<String> categories = new ArrayList<>();
        String SQL_TMP = byUser ? SQL_SELECT_USER_CATEGORIES : SQL_SELECT_CATEGORIES;
        // connect DB
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            ResultSet rs = ps.executeQuery();
            if (byUser) {
                ps.setString(1, user);
            }
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error trying to get tags at [DB.Global].[TagsDAO] " + e.getMessage());
        }
        return categories;
    }
}
