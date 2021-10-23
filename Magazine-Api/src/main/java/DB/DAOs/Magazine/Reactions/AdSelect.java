package DB.DAOs.Magazine.Reactions;

import BackendUtilities.Parser;
import DB.DBConnection;
import DB.Domain.Financial.Ad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jefemayoneso
 */
public class AdSelect {

    private String SQL_SELECT_RANDOM_AD_BY_TAGS = "SELECT a.*, t.tag FROM Magazine_Web.Ad AS a INNER JOIN Ad_Tag AS t ON a.id = t.ad "
            + "INNER JOIN Reader_Magazine_Tag AS rt ON rt.tag = t.tag AND rt.reader = ? AND a.type = ? ORDER BY RAND() LIMIT 1";
    private String SQL_SELECT_RANDOM_AD = "SELECT * FROM `Ad` WHERE `type` = ? ORDER BY RAND() LIMIT 1";

    /**
     * get a random ad to show it as service
     *
     * @param adType
     * @param email
     * @return
     */
    public Ad getRandomAdByUserIntrest(int adType, String email) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_RANDOM_AD_BY_TAGS)) {
            ps.setString(1, email);
            ps.setInt(2, adType);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getAdFromRs(rs);
            } else {
                return getRandomAd(adType);
            }
        } catch (Exception e) {
            System.out.println("Error finding an ad for usr " + e.getMessage());
        }
        return null;
    }

    public Ad getRandomAd(int adType) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_RANDOM_AD)) {
            ps.setInt(1, adType);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getAdFromRs(rs);
            }
        } catch (Exception e) {
            System.out.println("Error finding an ad for usr " + e.getMessage());
        }
        return null;
    }

    /**
     * Create an Ad object from a resultset
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Ad getAdFromRs(ResultSet rs) throws SQLException {
        Parser parser = new Parser();
        return new Ad(rs.getInt("id"),
                rs.getInt("shown_counter"),
                rs.getInt("type"),
                rs.getDouble("advertiser_paid"),
                parser.toLocalDate(rs.getDate("expiration_date")),
                parser.toLocalDate(rs.getDate("start_date")),
                rs.getString("advertiser"),
                rs.getString("shown_url"),
                rs.getString("img_local_path"),
                rs.getString("text"),
                rs.getString("video_url"));
    }
}
