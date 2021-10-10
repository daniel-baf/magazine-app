package DB.DAOs.Magazine;

import DB.Domain.Magazine.Magazine;
import ENUMS.DAOResults;
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
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_MAG)) {
            configurePSMagInsert(magazine, ps);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to insert Magazine at [MagazineInsert] " + e.getMessage());
            return DAOResults.ERROR_ON_INSERT.getCode();
        }
    }

    private void configurePSMagInsert(Magazine magazine, PreparedStatement ps) throws SQLException {
        int allowComm = magazine.isAllowComment() ? 1 : 0;
        int allowLieks = magazine.isAllowLikes() ? 1 : 0;
        int approved = magazine.isApproved() ? 1 : 0;

        ps.setString(1, magazine.getName());
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
