package DB.DAOs.Magazine;

import DB.Domain.Magazine.MagazinePost;
import Parsers.Parser;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Update a post
 *
 * @author jefemayoneso
 */
public class MagazinePostUpdate {

    private String SQL_UPDATE_POST = "UPDATE Post SET name=?, date=?, pdf=?, magazine=? WHERE (id=?)";

    public int update(MagazinePost post) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_UPDATE_POST)) {
            configurePsUpdate(ps, post);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while trying to update a post at [MagazinePOstUpdate] " + e.getMessage());
            return 0;
        }
    }

    private void configurePsUpdate(PreparedStatement ps, MagazinePost post) throws SQLException {
        Parser parser = new Parser();
        ps.setString(1, post.getTitle());
        ps.setDate(2, parser.toDate(post.getDate()));
        ps.setString(3, post.getPdfNamePath());
        ps.setString(4, post.getMagazine());
        ps.setInt(5, post.getId());
    }

}
