package DB.DAOs.Magazine;

import DB.DAOs.Magazine.Tags.TagsInsert;
import DB.Domain.Magazine.Magazine;
import Parsers.Parser;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jefemayoneso
 */
public class MagazineInsert {

    private String SQL_INSERT_MAG = "INSERT INTO Magazine (name, subscription_fee, company_fee, cost_per_day, creation_date, description, allow_comment, allow_likes, category, editor, approved) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public int insert(Magazine magazine) {
        int result = 0;

        try {
            try {
                // AUTOCOMIT FALSE
                DB.DBConnection.getConnection().setAutoCommit(false);
                PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_MAG);
                configurePSMagInsert(magazine, ps);
                if (ps.executeUpdate() != 0) { // magazine insertee
                    // insert tags
                    new TagsInsert().insert(magazine.getTags());
                    if (new MagazineTagDAO().insert(magazine.getName(), magazine.getTags()) != 0) {
                        result = 1;
                    }

                }
            } catch (SQLException e) {
                System.out.println("Error trying to insert magazine at [DB.DAOs.Magazine].[MagazineInsert] " + e.getMessage());
            } finally {
                if (result == 0) {
                    DB.DBConnection.getConnection().rollback();
                }
                DB.DBConnection.getConnection().setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Error trying to insert magazine at [DB.DAOs.Magazine].[MagazineInsert] " + e.getMessage());
        }
        return result;
    }

    private void configurePSMagInsert(Magazine magazine, PreparedStatement ps) throws SQLException {
        // vars to transform boolean to int, 1 = true, 0 = false
        int allowComm = magazine.isAllowComment() ? 1 : 0;
        int allowLieks = magazine.isAllowLikes() ? 1 : 0;
        int approved = magazine.isApproved() ? 1 : 0;
        // configure preparedstatement
        ps.setString(1, magazine.getName().toLowerCase());
        ps.setDouble(2, magazine.getMensuality());
        ps.setDouble(3, magazine.getCompanyFee());
        ps.setDouble(4, magazine.getCostPerDay());
        ps.setDate(5, new Parser().toDate(magazine.getDate()));
        ps.setString(6, magazine.getDescription());
        ps.setInt(7, allowComm);
        ps.setInt(8, allowLieks);
        ps.setString(9, magazine.getCategory());
        ps.setString(10, magazine.getEditor());
        ps.setInt(11, approved);
    }
}
