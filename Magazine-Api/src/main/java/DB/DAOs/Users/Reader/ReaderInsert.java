package DB.DAOs.Users.Reader;

import DB.DAOs.Users.UserCommonDAO;
import DB.Domain.Users.Reader;
import ENUMS.DAOResults;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class insert Reader objects to DATABASE
 *
 * @author jefemayoneso
 */
public class ReaderInsert {

    private String SQL_INSERT_READER = "INSERT INTO Reader (email, name, password) VALUES (?, ?, ?);";

    /**
     * Insert a single Reader on DB
     *
     * @param reader
     * @return
     */
    public int insert(Reader reader) {
        if (new UserCommonDAO().emailRegisted(reader.getEmail()) == null) { // verify if exist at any table
            try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_READER)) {
                configurePS(reader, ps);
                return ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error trying to insert EDITOR at [DB.DAOs.Users.Editor].[EditorInsertDAO] " + e.getMessage());
                return 0;
            }
        }
        return DAOResults.EMAIL_IN_USE.getCode(); // -1 means the email is registed

    }

    /**
     * Common task, configure a prepared statement for insert on DB
     *
     * @param reader
     * @param ps
     * @throws SQLException
     */
    private void configurePS(Reader reader, PreparedStatement ps) throws SQLException {
        ps.setString(1, reader.getEmail());
        ps.setString(2, reader.getName());
        ps.setString(3, reader.getPassword());
    }
}
