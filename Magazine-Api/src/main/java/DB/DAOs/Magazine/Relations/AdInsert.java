package DB.DAOs.Magazine.Relations;

import DB.DBConnection;
import DB.Domain.Ad.Ad;
import ENUMS.DAOResults;
import Parsers.FileWriterCP;
import Parsers.Parser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.Part;

/**
 *
 * @author jefemayoneso
 */
public class AdInsert {

    private Parser parser = new Parser();
    private final String SQL_INSERT_AD = "INSERT INTO `Ad` (advertiser_paid,expiration_date,start_date,shown_counter,type,advertiser,shown_url,video_url,img_local_path,text) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private String SQL_INSERT_AD_TAG = "INSERT INTO `Ad_Tag` (`tag`, `ad`) VALUES (?, ?);";

    public int insert(Ad ad, Part part) {
        int result = 0;
        System.out.println("Empieza insert");
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_INSERT_AD, PreparedStatement.RETURN_GENERATED_KEYS)) {
            configurePsInsertAd(ps, ad);
            if (ad.getType() == 2) {
                System.out.println("es img");
                DBConnection.getConnection().setAutoCommit(false);
                // try insert
                if (ps.executeUpdate() != 0) { // insert done, trying to save img
                    ResultSet rs = ps.getGeneratedKeys();
                    System.out.println("insert ok");
                    // get generated key
                    if (rs.next()) {
                        ad.setId(rs.getInt(1));
                        System.out.println("set id " + ad.getId());
                        FileWriterCP fwcp = new FileWriterCP();
                        // Create a tmp date to save as name
                        if (fwcp.write(part, DAOResults.FILES_IMG_PATH_AD.getMessage(), "" + ad.getId())) { // save to directory
                            // update the ad
                            System.out.println("fiel wrote");
                            ad.setImgLocalPath(DAOResults.FILES_IMG_PATH_AD.getMessage() + ad.getId() + fwcp.getFileExtension(part.getSubmittedFileName()));
                            // now insert AdTags
                            System.out.println("updating");
                            if (new AdUpdate().update(ad) != 0) {
                                System.out.println("updated");
                                result = insertAdTags(ad);
                            }
                        }
                    }

                }
            } else {
                result = ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error while insert ad at [AdInsert] " + e.getMessage());
        } finally {
            try {
                if (result == 0) {
                    DBConnection.getConnection().rollback();
                    // try to delete file
                }
                DBConnection.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Error while rollback at [AdInsert]");
            }
        }
        return result;
    }

    private int insertAdTags(Ad ad) {
        int result = 0;
        for (String tag : ad.getTags()) {
            System.out.println("Insertando tag: " + ad.getId() + ", " + tag);
            result += insertAdTag(ad, tag);
        }
        return result == ad.getTags().size() ? result : 0;
    }

    private int insertAdTag(Ad ad, String tag) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_INSERT_AD_TAG)) {
            ps.setString(1, tag);
            ps.setInt(2, ad.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Cannot insert ad tag at [AdInsert] " + e.getMessage());
            return 0;
        }
    }

    /**
     * Shared method to configure a PS
     *
     * @param ps
     * @param ad
     * @throws SQLException
     */
    public void configurePsInsertAd(PreparedStatement ps, Ad ad) throws SQLException {
        ps.setDouble(1, ad.getAdvertiserPaid());
        ps.setDate(2, parser.toDate(ad.getExpirationDate()));
        ps.setDate(3, parser.toDate(ad.getStartDate()));
        ps.setInt(4, ad.getShownCounter());
        ps.setInt(5, ad.getType());
        ps.setString(6, ad.getAdvertiser());
        ps.setString(7, ad.getShownUrl());
        ps.setString(8, ad.getVideoUrl());
        ps.setString(9, ad.getImgLocalPath());
        ps.setString(10, ad.getText());
    }

}
