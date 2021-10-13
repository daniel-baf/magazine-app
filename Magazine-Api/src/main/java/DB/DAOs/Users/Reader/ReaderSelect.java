package DB.DAOs.Users.Reader;

import DB.Domain.Users.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this class get information from DB, it belongs to Reader table at DATABASE
 *
 * @author jefemayoneso
 */
public class ReaderSelect {

    private final String SQL_SELECT_READER = "SELECT * FROM Reader WHERE email = ?";

    /**
     * Select all the info, from Reader table at DATABASE
     *
     * @param email
     * @return
     */
    public Reader select(String email) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_SELECT_READER)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getReaderFromRs(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error trying to get READER from DB at [DB.DAOs.Users.Reader].[ReaderSelectDAO] " + e.getMessage());
        }
        return null;
    }

    /**
     * get all the info of a <Reader> from <ResultSet>
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Reader getReaderFromRs(ResultSet rs) throws SQLException {
        return new Reader(rs.getString("email"), rs.getString("password"), rs.getString("name"));
    }
}
