package DB.DAOs.Magazine.Relations;

import DB.DBConnection;
import DB.Domain.Magazine.Relations.Like;
import BackendUtilities.Parser;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add a like in database
 *
 * @author jefemayoneso
 */
public class LikeInsert {

    private String SQL_INSERT_LIKE = "INSERT INTO `Like` (date, magazine, user) VALUES (?, ?, ?)";
    private Parser parser = new Parser();

    public int insert(Like like) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_INSERT_LIKE)) {
            configurePSInsert(ps, like);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while insert Like at [LikeINsert] " + e.getMessage());
            return 0;
        }
    }

    /**
     * Configure preparedstatement for inserts
     *
     * @param ps
     * @param like
     */
    private void configurePSInsert(PreparedStatement ps, Like like) throws SQLException {
        ps.setDate(1, this.parser.toDate(like.getDate()));
        ps.setString(2, like.getMagazine());
        ps.setString(3, like.getUser());
    }

}
