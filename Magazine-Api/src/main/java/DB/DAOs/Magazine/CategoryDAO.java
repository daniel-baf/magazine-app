package DB.DAOs.Magazine;

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

    // SQL SENTENCES
    private String SQL_SELECT_CATEGORIES = "SELECT * FROM Category";
    private String SQL_SELECT_USER_CATEGORIES = "SELECT * FROM User_Intrest_Categories WHERE reader = ?";

    /**
     * select all <Categories> on DATABASE
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
            if (byUser) {
                ps.setString(1, user);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (byUser) {
                    categories.add(rs.getString("category"));
                } else {
                    categories.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error trying to get tags at [DB.DAOs.Magazines..[TagsDAO] " + e.getMessage());
        }
        return categories;
    }
}
