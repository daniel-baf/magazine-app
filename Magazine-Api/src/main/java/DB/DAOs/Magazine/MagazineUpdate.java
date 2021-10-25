package DB.DAOs.Magazine;

import DB.Domain.Magazine.Magazine;
import BackendUtilities.Parser;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * COntroll all Update queries from SQL for Magazine objects
 *
 * @author jefemayoneso
 */
public class MagazineUpdate {

    private String SQL_UPDATE_MAG = "UPDATE Magazine SET subscription_fee=?, company_fee=?, "
            + "cost_per_day=?, creation_date=?, description=?, allow_comment=?, "
            + "allow_likes=?, category=?, editor=?, approved=? WHERE (name = ?)";

    /**
     * Update a magazine without changing the tags
     *
     * @param magazine
     * @return
     */
    public int update(Magazine magazine) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_UPDATE_MAG)) {
            configurePs(ps, magazine);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to update magazine at [M̀agazineUpdate] " + e.getMessage());
            return 0;
        }
    }

    /**
     * Update a magazine and also change the magazine tags
     *
     * @param magazine
     * @param tags
     * @return
     */
    public int updateWitTags(Magazine magazine) {
        int result = 0;
        try {
            DB.DBConnection.getConnection().setAutoCommit(false); // rollback if error
            // update basic of mag
            if (update(magazine) != 0) {
                // insert new tags
                new TagsInsert().insert(magazine.getTags()); // can return errro if tag already existe
                // insert mag tags
                MagazineTagDAO mtd = new MagazineTagDAO();
                if (mtd.delete(magazine.getName()) != 0) {
                    result = mtd.insert(magazine.getName(), magazine.getTags());
                }
            }
        } catch (Exception e) {
            System.out.println("Cannot update magazine at MagazineUpdate " + e.getMessage());
        } finally {
            try {
                if (result == 0) {
                    DB.DBConnection.getConnection().rollback();
                }
                DB.DBConnection.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Error while rollback at MagazineUpdateI");
            }
        }
        return result;
    }

    private void configurePs(PreparedStatement ps, Magazine magazine) throws SQLException {
        // vars to transform boolean to int, 1 = true, 0 = false
        int allowComm = magazine.isAllowComment() ? 1 : 0;
        int allowLieks = magazine.isAllowLikes() ? 1 : 0;
        int approved = magazine.isApproved() ? 1 : 0;
        if (magazine.isUnlisted()) {
            approved = 3;
        }

        // configure preparedstatement
        ps.setDouble(1, magazine.getMensuality());
        ps.setDouble(2, magazine.getCompanyFee());
        ps.setDouble(3, magazine.getCostPerDay());
        ps.setDate(4, new Parser().toDate(magazine.getDate()));
        ps.setString(5, magazine.getDescription());
        ps.setInt(6, allowComm);
        ps.setInt(7, allowLieks);
        ps.setString(8, magazine.getCategory());
        ps.setString(9, magazine.getEditor());
        ps.setInt(10, approved);
        ps.setString(11, magazine.getName());
    }
}
