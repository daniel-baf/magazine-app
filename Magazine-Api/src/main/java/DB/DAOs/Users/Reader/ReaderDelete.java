package DB.DAOs.Users.Reader;

import java.sql.PreparedStatement;

/**
 * This class delete from DATABASE all about Reader
 *
 * @author jefemayoneso
 */
public class ReaderDelete {

    // SQL sentences
    private String SQL_DELETE_USER_CATG = "DELETE FROM User_Intrest_Categories WHERE (reader = ?) and (category = ?)";
    private String SQL_DELETE_USER_CATG_ALL = "DELETE FROM User_Intrest_Categories WHERE (reader = ?)";

    /**
     * Delete from DATABASE some/all the category interest
     *
     * @param deleteAll
     * @param email
     * @param category
     * @return
     */
    public int deleteCategoryIntrest(boolean deleteAll, String email, String category) {
        String SQL_TMP = deleteAll ? SQL_DELETE_USER_CATG_ALL : SQL_DELETE_USER_CATG;
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            ps.setString(1, email);
            if (!deleteAll) {
                ps.setString(2, category);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to DELETE CATEGORY from USER at [DB.DAOs.Users.Reader].[ReaderDelete] " + e.getMessage());
            return 0;
        }
    }
}
