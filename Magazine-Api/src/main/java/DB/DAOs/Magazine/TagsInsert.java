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

    // SQL queries
    private String SQL_INSERT_TAG = "INSERT INTO Tag (name) VALUES (?)";

    /**
     * Insert a single tag to DATABASE
     *
     * @param tag
     * @return
     */
    public int insert(String tag) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_INSERT_TAG)) {
            ps.setString(1, tag.toLowerCase());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to insert Tag at [TagsInsert] " + e.getMessage());
            return DAOResults.ERROR_ON_INSERT.getCode();
        }
    }

    /**
     * Insert multiple tags to DATABASE
     *
     * @param tags
     * @return
     */
    public int insert(ArrayList<String> tags) {
        int results = 0;
        try {
            // Iterate tags array and insert them, then add the value to results
            results = tags.stream().map(tag -> insert(tag)).map(sub -> sub).reduce(results, Integer::sum);
            return results;
        } catch (Exception e) {
            System.out.println("Error trying to insert Tags at [TagsInsert] " + e.getMessage());
            return DAOResults.ERROR_ON_INSERT.getCode();
        }
    }
}
