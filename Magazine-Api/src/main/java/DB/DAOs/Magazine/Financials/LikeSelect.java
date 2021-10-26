package DB.DAOs.Magazine.Financials;

import DB.DBConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jefemayoneso
 */
public class LikeSelect {

    private String SQL_COUNT_LIKES_MAG = "SELECT COUNT(magazine) AS counter FROM `Like` WHERE magazine=?";
    private final String SQL_SELECT_MOST_LIKED_BTWN = "SELECT COUNT(l.magazine) AS `likes`, l.magazine, m.editor FROM `Like` AS l  "
            + "INNER JOIN Magazine as m ON m.name = l.magazine AND m.editor = ? WHERE `date` BETWEEN ? AND ? GROUP BY l.magazine "
            + "ORDER BY `likes` DESC LIMIT 1";
    private String SQL_SELECT_MOST_LIKED = "SELECT COUNT(l.magazine) AS `likes`, l.magazine, m.editor FROM `Like` AS l INNER JOIN Magazine as m "
            + "ON m.name = l.magazine AND m.editor = ? GROUP BY l.magazine  ORDER BY `likes` DESC LIMIT 1";

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

    /**
     * Return the name of the magazine with most likes wich belong to a user
     *
     * @param user
     * @param startDate
     * @param endDate
     * @param validDates
     * @return
     */
    public String selectMostLikedByUser(String user, Date startDate, Date endDate, boolean validDates) {
        String SQL_TMP = validDates ? SQL_SELECT_MOST_LIKED_BTWN : SQL_SELECT_MOST_LIKED;
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            ps.setString(1, user);
            if (validDates) {
                ps.setDate(2, startDate);
                ps.setDate(3, endDate);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("magazine");
            }
        } catch (Exception e) {
            System.out.println("Cannot get most liked mag at [MagazineInsert] " + e.getMessage());
        }
        return null;
    }

}
