package DB.DAOs.Magazine.Reactions;

import DB.DBConnection;
import DB.Domain.Magazine.Comment;
import BackendUtilities.Parser;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class insert comments to DB
 *
 * @author jefemayoneso
 */
public class CommentInsert {

    private String SQL_INSERT_COMMENT = "INSERT INTO Comment (date, text, user, magazine) VALUES (?, ?, ?, ?)";

    public int insert(Comment comment) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_INSERT_COMMENT)) {
            configurePsInsert(ps, comment);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while trying to insert COmment at [CommentInsert] " + e.getMessage());
            return 0;
        }
    }

    /**
     * COnfigure a PS with a Comment object for inserts
     *
     * @param ps
     * @param comment
     * @throws SQLException
     */
    private void configurePsInsert(PreparedStatement ps, Comment comment) throws SQLException {
        Parser parser = new Parser();
        ps.setDate(1, parser.toDate(comment.getDate()));
        ps.setString(2, comment.getText());
        ps.setString(3, comment.getUser());
        ps.setString(4, comment.getMagazine());
    }

}
