package DB.DAOs.Magazine;

import ENUMS.DAOResults;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class MagazineTagInsert {

    private String SQL_INERT_MAG_TAG = "INSERT INTO Magazine_Tag (magazine, tag) VALUES (?, ?)";

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

    public int insert(String magazine, ArrayList<String> tags) {
        int results = 0;
        int tmp = 0;
        try {
            for (String tag : tags) {
                tmp = insert(magazine, tag);
                results += tmp;
            }
            return results;
        } catch (Exception e) {
            System.out.println("Erryr trying to insert Magazine Tags at [MagazineTagInsert] " + e.getMessage());
            return DAOResults.ERROR_ON_INSERT.getCode();
        }
    }
}
