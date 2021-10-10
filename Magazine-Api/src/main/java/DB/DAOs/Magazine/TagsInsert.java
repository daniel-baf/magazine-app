package DB.DAOs.Magazine;

import DB.DBConnection;
import ENUMS.DAOResults;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class TagsInsert {

    private String SQL_INSERT_TAG = "INSERT INTO Tag (name) VALUES (?)";

    public int insert(String tag) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_INSERT_TAG)) {
            ps.setString(1, tag.toLowerCase());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to insert Tag at [TagsInsert] " + e.getMessage());
            return DAOResults.ERROR_ON_INSERT.getCode();
        }
    }

    public int insert(ArrayList<String> tags) {
        int results = 0;
        try {
            for (String tag : tags) {
                int sub = insert(tag);
                results += sub;
            }
            return results;
        } catch (Exception e) {
            System.out.println("Error trying to insert Tags at [TagsInsert] " + e.getMessage());
            return DAOResults.ERROR_ON_INSERT.getCode();
        }
    }
}
