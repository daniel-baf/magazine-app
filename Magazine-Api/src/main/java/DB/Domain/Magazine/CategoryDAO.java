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

    /**
     * This method select all tags on DB
     *
     * @return
     */
    public ArrayList<String> select() {
        ArrayList<String> categories = new ArrayList<>();
        // connect DB
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_CATEGORIES)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error trying to get tags at [DB.Global].[TagsDAO] " + e.getMessage());
        }
        return categories;
    }
}
