package DB.DAOs.Magazine.Relations;

import DB.DBConnection;
import DB.Domain.Magazine.Relations.Comment;
import Parsers.Parser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A class to select comments
 *
 * @author jefemayoneso
 */
public class CommentSelect {

    private String SQL_SELECT_COMMENTS = "SELECT * FROM Comment WHERE magazine = ? LIMIT ? OFFSET ?";

    public ArrayList<Comment> getComments(int limit, int offset, String magazine) {
        ArrayList<Comment> comments = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_COMMENTS)) {
            // set it up
            ps.setString(1, magazine);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comments.add(getCommentFromRS(rs));
            }
        } catch (Exception e) {
            System.out.println("Error while getting Comments at [CommentSelect] " + e.getMessage());
        }
        return comments;
    }

    private Comment getCommentFromRS(ResultSet rs) throws SQLException {
        return new Comment(
                rs.getInt("id"),
                new Parser().toLocalDate(rs.getDate("date")),
                rs.getString("text"),
                rs.getString("user"), rs.getString("magazine"));
    }
}
