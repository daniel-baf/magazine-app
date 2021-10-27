package DB.DAOs.Magazine.Financials;

import BackendUtilities.Parser;
import DB.DBConnection;
import DB.Domain.Financial.Ad;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class AdSelect {

    private final String SQL_SELECT_RANDOM_AD_BY_TAGS = "SELECT a.*, t.tag FROM Magazine_Web.Ad AS a INNER JOIN Ad_Tag AS t ON a.id = t.ad "
            + "INNER JOIN Reader_Magazine_Tag AS rt ON rt.tag = t.tag AND rt.reader = ? AND a.type = ? ORDER BY RAND() LIMIT 1";
    private final String SQL_SELECT_RANDOM_AD = "SELECT * FROM `Ad` WHERE `type` = ? ORDER BY RAND() LIMIT 1";
    private final String SQL_SELECT_EARNING_AD_REP = "SELECT SUM(advertiser_paid) AS `entry` FROM Ad WHERE start_date = ?";
    private final String SQL_SELECT_OWNED_ADS = "SELECT * FROM Magazine_Web.Ad WHERE advertiser = ?";

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

    /**
     * Return a list of Earnings from ads
     *
     * @param validDates
     * @param date1
     * @param date2
     * @return
     */
    public Double getEarningsAds(Date date) {
        // get datyea from dratabase
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_EARNING_AD_REP)) {
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("entry");
            }
        } catch (Exception e) {
            System.out.println("Cannot get ad earnings report " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Ad> getAdsBelongAdvertiser(String advertiser, boolean validDates, Date date1, Date date2) {
        String SQL_TMP = SQL_SELECT_OWNED_ADS;
        SQL_TMP += validDates ? " AND start_date BETWEEN ? AND ? " : "";
        ArrayList<Ad> ads = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            ps.setString(1, advertiser);
            if (validDates) {
                ps.setDate(2, date1);
                ps.setDate(3, date2);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ads.add(getAdFromRs(rs));
            }
        } catch (Exception e) {
            System.out.println("Cannoot find ads " + e.getMessage());
        }
        return ads;
    }
}
