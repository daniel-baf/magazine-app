package DB.DAOs.Magazine;

import DB.Domain.Magazine.Magazine;
import BackendUtilities.Parser;
import DB.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Return a list of magazines or just one magazine
 *
 * @author jefemayoneso
 */
public class MagazineSelect {

    // SQL queries
    private final String SQL_SELECT_MULT_MAG = "SELECT * FROM Magazine";
    private final String SQL_SELECT_ONE_MAG = "SELECT * FROM Magazine WHERE name=?";
    private final String SQL_SELECT_MAGS_FOR_USER = "SELECT * FROM Magazine AS m INNER JOIN User_Intrest_Categories AS rc "
            + "ON m.category=rc.category AND rc.reader=? AND m.approved='1' LIMIT ? OFFSET ?";
    private final String SQL_SELECT_COST_PER_DAY_TOTAL = "SELECT COUNT(cost_per_day) AS 'maintaince' FROM Magazine_Web.Magazine";

    /**
     * Select all the information about 1 magazine by name
     *
     * @param email
     * @return
     */
    public Magazine select(String email) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_SELECT_ONE_MAG)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getMagazineFromRS(rs);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     *
     * @param arraySize
     * @param type the type of magazine, 1 = only published, 2 = no published, 0
     * = all
     * @return
     */
    public ArrayList<Magazine> select(int arraySize, int type) {
        ArrayList<Magazine> magazines = new ArrayList<>();
        String SQL_TMP = configureByType(type);
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            ps.setInt(1, arraySize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magazines.add(getMagazineFromRS(rs));
            }
        } catch (Exception e) {
            System.out.println("Error trying to get magazines at [MagazineSelect] " + e.getMessage());
        }
        return magazines;
    }

    /**
     * this method return <limit> magazines, starting at <offset> for infinite
     * scroll
     *
     * @param limit
     * @param offset
     * @param reader
     * @return
     */
    public ArrayList<Magazine> select(int limit, int offset, String reader) {
        return selectList(reader, limit, offset, SQL_SELECT_MAGS_FOR_USER);
    }

    /**
     * Return a list of magazines that own an editor
     *
     * @param editor
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<Magazine> select(String editor, int limit, int offset) {
        String SQL_TMP = SQL_SELECT_MULT_MAG + " WHERE editor = ? AND approved = 1 LIMIT ? OFFSET ?";
        return selectList(editor, limit, offset, SQL_TMP);
    }

    /**
     * Shared method for magazines when looking for user, limit and offset
     *
     * @param user
     * @param limit
     * @param offset
     * @param SQL_TMP
     * @return
     */
    private ArrayList<Magazine> selectList(String user, int limit, int offset, String SQL_TMP) {
        ArrayList<Magazine> magazines = new ArrayList<>();
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            configForListMag(user, offset, limit, ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magazines.add(getMagazineFromRS(rs));
            }
        } catch (Exception e) {
            System.out.println("Error trying to get magazines at [MagazineSelect] " + e.getMessage());
        }
        return magazines;
    }

    /**
     * This method create a SQL query
     *
     * @return
     */
    private String configureByType(int type) {
        int attribute = type == 1 ? 1 : 0; // 0 = not approved on DB, 1 = approved on DB
        if (type == 1 || type == 2) {
            return SQL_SELECT_MULT_MAG + " WHERE approved = " + attribute + " LIMIT ? ";
        } else {
            return SQL_SELECT_MULT_MAG;
        }
    }

    /**
     * This method is a shared action for select magazines by user or by editor
     *
     * @param user
     * @param offset
     * @param limit
     * @param ps
     * @throws SQLException
     */
    private void configForListMag(String user, int offset, int limit, PreparedStatement ps) throws SQLException {
        ps.setString(1, user);
        ps.setInt(2, limit);
        ps.setInt(3, offset);
    }

    /**
     * Configure a new User from prepared statement
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public Magazine getMagazineFromRS(ResultSet rs) throws SQLException {
        boolean allowLikes = rs.getInt("allow_likes") == 1;
        boolean allowComment = rs.getInt("allow_comment") == 1;
        boolean unlisted = rs.getInt("approved") == 3;
        boolean approved = rs.getInt("approved") == 1;

        return new Magazine(
                rs.getString("name"),
                rs.getDouble("subscription_fee"),
                rs.getDouble("company_fee"),
                rs.getDouble("cost_per_day"),
                new Parser().toLocalDate(rs.getDate("creation_date")),
                rs.getString("description"),
                allowLikes,
                allowComment,
                rs.getString("editor"),
                rs.getString("category"),
                new MagazineTagDAO().select(rs.getString("name")), approved, unlisted
        );
    }

    public int getMaintanceTotal() {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_COST_PER_DAY_TOTAL)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("maintaince");
            }
        } catch (Exception e) {
            System.out.println("Cannot get the maintance ammount");
        }
        return 0;
    }
}
