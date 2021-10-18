package DB.DAOs.Magazine;

import DB.DBConnection;
import DB.Domain.Magazine.MagazinePost;
import Parsers.Parser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Select posts from DATABASE
 *
 * @author jefemayoneso
 */
public class MagazinePostSelect {

    private String SQL_SELECT_POSTS = "SELECT * FROM  Post WHERE magazine=? LIMIT ? OFFSET ?";

    /**
     * Select some post to show at FE
     *
     * @param magazine
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<MagazinePost> select(String magazine, int limit, int offset) {
        ArrayList<MagazinePost> posts = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_POSTS)) {
            ps.setString(1, magazine);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                posts.add(getPostFromRS(rs));
            }
        } catch (Exception e) {
            System.out.println("Error while trying to get magazine posts at [MagazinePostSelect] " + e.getMessage());
        }
        return posts;
    }

    private MagazinePost getPostFromRS(ResultSet rs) throws SQLException {
        Parser parser = new Parser();
        return new MagazinePost(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("magazine"),
                parser.toLocalDate(rs.getDate("date")),
                rs.getString("pdf"));
    }
}
