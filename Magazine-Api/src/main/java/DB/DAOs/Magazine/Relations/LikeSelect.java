package DB.DAOs.Magazine.Relations;

import DB.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jefemayoneso
 */
public class LikeSelect {

    private String SQL_COUNT_LIKES_MAG = "SELECT COUNT(magazine) AS counter FROM `Like` WHERE magazine=?";

    /**
     * Return the number of likes for a magazine
     *
     * @param magazine
     * @return
     */
    public int countLikes(String magazine) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_COUNT_LIKES_MAG)) {
            ps.setString(1, magazine);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("counter");
            }
        } catch (Exception e) {
            System.out.println("Error whie trying to count likes of magazine at [LIkesSelect] " + e.getMessage());
        }
        return 0;
    }
}
