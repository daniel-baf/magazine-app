package DB.DAOs.Magazine.Financials;

import DB.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Manage al select queries fro MYSQL
 *
 * @author jefemayoneso
 */
public class AdvertiserSelect {

    private String SQL_SELECT_ADVERTISER = "SELECT * FROM Advertiser LIMIT ? OFFSET ?";
    private final String SQL_SELECT_ALL_ADVERTISER = "SELECT * FROM Advertiser ORDER BY `name` ASC";

    /**
     * Return a list with the name of all advertisers
     *
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<String> getAdvertisers(int limit, int offset) {
        ArrayList<String> advertisers = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_ADVERTISER)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                advertisers.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Error while trying to get advertisers at [AdvertiserSelect] " + e.getMessage());
        }
        return advertisers;
    }

    public ArrayList<String> getAdvertisers() {
        ArrayList<String> advertisers = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_ALL_ADVERTISER)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                advertisers.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Error while trying to get advertisers at [AdvertiserSelect] " + e.getMessage());
        }
        return advertisers;
    }
}
