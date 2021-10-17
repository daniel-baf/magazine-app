package DB.DAOs.Magazine;

import DB.Domain.Magazine.Magazine;
import Parsers.Parser;
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
    private final String SQL_SELECT_MAGS_FOR_USER = "SELECT * FROM Magazine AS m INNER JOIN User_Intrest_Categories AS rc "
            + "ON m.category=rc.category AND rc.reader=? AND m.approved='1' LIMIT ? OFFSET ?";

    /**
     * Select all the information about 1 magazine by name
     *
     * @param email
     * @return
     */
    public Magazine select(String email) {
        String SQL_TMP = SQL_SELECT_MULT_MAG + " WHERE `name`=?";
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return configurePSSelect(rs);
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
                magazines.add(configurePSSelect(rs));
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
        ArrayList<Magazine> magazines = new ArrayList<>();
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_SELECT_MAGS_FOR_USER)) {
            ps.setString(1, reader);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magazines.add(configurePSSelect(rs));
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
     * Configure a new User from prepared statement
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Magazine configurePSSelect(ResultSet rs) throws SQLException {
        boolean allowLikes = rs.getInt("allow_likes") != 0;
        boolean allowComment = rs.getInt("allow_comment") != 0;

        return new Magazine(rs.getString("name"),
                rs.getDouble("subscription_fee"),
                rs.getDouble("company_fee"),
                rs.getDouble("cost_per_day"),
                new Parser().toLocalDate(rs.getDate("creation_date")),
                rs.getString("description"),
                allowLikes,
                allowComment,
                rs.getString("editor"),
                rs.getString("category"),
                new MagazineTagDAO().select(rs.getString("name"))
        );
    }

}