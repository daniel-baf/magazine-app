package DB.DAOs.Magazine;

import ENUMS.DAOResults;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * This class insert magazine tags to DB
 *
 * @author jefemayoneso
 */
public class MagazineTagInsert {

    // SQL queries
    private String SQL_INERT_MAG_TAG = "INSERT INTO Magazine_Tag (magazine, tag) VALUES (?, ?)";

    /**
     * Insert 1 magazine with 1 single tag
     *
     * @param magazine
     * @param tag
     * @return
     */
    public int insert(String magazine, String tag) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INERT_MAG_TAG)) {
            ps.setString(1, magazine);
            ps.setString(2, tag);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erryr trying to insert Magazine Tag at [MagazineTagInsert] " + e.getMessage());
            return DAOResults.ERROR_ON_INSERT.getCode();
        }
    }

    /**
     * Insert a magazine with multiple tags
     *
     * @param magazine
     * @param tags
     * @return
     */
    public int insert(String magazine, ArrayList<String> tags) {
        int results = 0;
        try {
            results = tags.stream().map(tag -> insert(magazine, tag)).map(tmp -> tmp).reduce(results, Integer::sum);
            return results;
        } catch (Exception e) {
            System.out.println("Error trying to insert Magazine Tags at [MagazineTagInsert] " + e.getMessage());
            return DAOResults.ERROR_ON_INSERT.getCode();
        }
    }
}
