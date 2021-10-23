package DB.DAOs.Users;

import DB.DBConnection;
import DB.Domain.Users.Reader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jefemayoneso
 */
public class ReaderUpdate {

    private final String SQL_UPDATE_READER = "UPDATE `Reader` SET `name`=?,`password`=?,`imgPath`=? WHERE (`email`=?)";

    /**
     * THis method update all the Reader info (except the email) of the user
     *
     * @param reader
     * @return
     */
    public int update(Reader reader) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_UPDATE_READER)) {
            configurePS(reader, ps);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to insert READER at [DB.DAOs.Users.Reader].[ReaderUpdate] " + e.getMessage());
            return 0;
        }
    }

    /**
     * Configure the <PreparedStatement> for Updates
     *
     * @param reader
     * @param ps
     * @throws SQLException
     */
    private void configurePS(Reader reader, PreparedStatement ps) throws SQLException {
        ps.setString(1, reader.getName());
        ps.setString(2, reader.getPassword());
        ps.setString(3, reader.getImgPath());
        ps.setString(4, reader.getEmail());
    }
}
