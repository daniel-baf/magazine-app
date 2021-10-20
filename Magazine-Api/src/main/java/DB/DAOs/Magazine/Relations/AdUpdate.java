package DB.DAOs.Magazine.Relations;

import DB.DBConnection;
import DB.Domain.Ad.Ad;
import Parsers.Parser;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jefemayoneso
 */
public class AdUpdate {

    Parser parser = new Parser();

    private final String SQL_UPDATE_AD = "UPDATE `Ad` SET advertiser_paid=?,expiration_date=?,start_date=?,shown_counter=?,type=?,advertiser=?,shown_url=?,"
            + "video_url=?,img_local_path=?,text=? WHERE (id=?)";

    public int update(Ad ad) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_UPDATE_AD)) {
            configurePsUpdate(ps, ad);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Cannot update Ad at [AdUpdate] " + e.getMessage());
            return 0;
        }
    }

    private void configurePsUpdate(PreparedStatement ps, Ad ad) throws SQLException {
        new AdInsert().configurePsInsertAd(ps, ad);
        ps.setInt(11, ad.getId());
    }

}
