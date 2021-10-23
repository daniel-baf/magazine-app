package DB.DAOs.Magazine.Financials;

import DB.Domain.Financial.Advertiser;
import java.sql.PreparedStatement;

/**
 * Manage al INSERT queries for MYSQL about Advertiser
 *
 * @author jefemayoneso
 */
public class AdvertiserInsert {

    private String SQL_INSERT_ADVERTISER = "INSERT INTO `Advertiser` (`name`) VALUES (?)";

    public int insert(Advertiser advertiser) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_ADVERTISER)) {
            ps.setString(1, advertiser.getName().toLowerCase());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Cannot insert advertiser at [AdvertiserInsert] " + e.getMessage());
            return 0;
        }
    }
}
