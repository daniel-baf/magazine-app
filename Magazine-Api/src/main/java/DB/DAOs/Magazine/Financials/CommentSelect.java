package DB.DAOs.Magazine.Financials;

import DB.DBConnection;
import DB.Domain.Magazine.Comment;
import BackendUtilities.Parser;
import DB.Domain.forJasperReports.MagazineCommentsReport;
import java.sql.Date;
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
    private final String SQL_SELECT_COMMENTS_1 = "SELECT * FROM Comment WHERE magazine = ? ";
    private String SQL_SELECT_MOST_COMMENTED_MAGS = "SELECT COUNT(magazine) AS `comments`, magazine FROM `Comment` GROUP BY magazine ORDER BY `comments` DESC LIMIT 5";

    /**
     * Get comments from a magazine
     *
     * @param limit
     * @param offset
     * @param magazine
     * @return
     */
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

    public ArrayList<Comment> getComments(String magazine, Date date1, Date date2, boolean validDates) {
        String SQL_TMP = validDates ? SQL_SELECT_COMMENTS_1 + " AND `date` BETWEEN ? AND ? " : SQL_SELECT_COMMENTS_1;
        ArrayList<Comment> comments = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            ps.setString(1, magazine);
            if (validDates) {
                ps.setDate(2, date1);
                ps.setDate(3, date2);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comments.add(getCommentFromRS(rs));
            }
        } catch (Exception e) {
            System.out.println("Error getting comment " + e.getMessage());
        }
        return comments;
    }

    /**
     * Shared method for comment select
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Comment getCommentFromRS(ResultSet rs) throws SQLException {
        return new Comment(
                rs.getInt("id"),
                new Parser().toLocalDate(rs.getDate("date")),
                rs.getString("text"),
                rs.getString("user"), rs.getString("magazine"));
    }

    public ArrayList<MagazineCommentsReport> getMagazineMostCommentedes(Date date1, Date date2, boolean validDates) {
        ArrayList<MagazineCommentsReport> magsRep = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_MOST_COMMENTED_MAGS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magsRep.add(new MagazineCommentsReport(rs.getString("magazine"), getComments(rs.getString("magazine"), date1, date2, validDates)));
            }
        } catch (Exception e) {
            System.out.println("Cannot get comments report " + e.getMessage());
        }
        return magsRep;
    }
}
