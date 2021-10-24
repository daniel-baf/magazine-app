package DB.DAOs.Magazine;

import DB.DBConnection;
import DB.GeneralPaths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * This class insert magazine tags to DB
 *
 * @author jefemayoneso
 */
public class MagazineTagDAO {

    // SQL queries
    private final String SQL_INERT_MAG_TAG = "INSERT INTO Magazine_Tag (magazine, tag) VALUES (?, ?)";
    private final String SQL_SELECT_TAGS_MAG = "SELECT * FROM Magazine_Tag WHERE magazine = ?";
    private final String SQL_DELET_MAG_TAGS = "DELETE FROM Magazine_Tag WHERE (magazine=?)";

    /**
     * Insert 1 magazine with 1 single tag
     *
     * @param magazine
     * @param tag
     * @return
     */
    public int insert(String magazine, String tag) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INERT_MAG_TAG)) {
            ps.setString(1, magazine.toLowerCase());
            ps.setString(2, tag.toLowerCase());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erryr trying to insert Magazine Tag at [MagazineTagInsert] " + e.getMessage());
            return GeneralPaths.ERROR_ON_INSERT.getCode();
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
            return GeneralPaths.ERROR_ON_INSERT.getCode();
        }
    }

    /**
     * Select the tags from any magazine
     *
     * @param magazine = all
     * @return
     */
    public ArrayList<String> select(String magazine) {
        ArrayList<String> tags = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_TAGS_MAG)) {
            ps.setString(1, magazine);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tags.add(rs.getString("tag"));
            }
        } catch (Exception e) {
            System.out.println("Error trying to get  Tagsfor magazine at [MagazineTagDAO] " + e.getMessage());
        }
        return tags;
    }

    /**
     * Delete all the tags that belongs to a magazine
     *
     * @param magazine
     * @return
     */
    public int delete(String magazine) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_DELET_MAG_TAGS)) {
            ps.setString(1, magazine);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
}
