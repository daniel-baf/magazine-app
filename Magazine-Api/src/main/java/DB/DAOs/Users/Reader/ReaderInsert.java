package DB.DAOs.Users.Reader;

import DB.DAOs.Users.UserCommonDAO;
import DB.DBConnection;
import DB.Domain.Users.Reader;
import ENUMS.DAOResults;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class insert Reader objects to DATABASE
 *
 * @author jefemayoneso
 */
public class ReaderInsert {

    private String SQL_INSERT_READER = "INSERT INTO Reader (email, name, password) VALUES (?, ?, ?);";
    private String SQL_INSERT_USER_CATEG = "INSERT INTO User_Intrest_Categories (reader, category) VALUES (?, ?)";

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
                System.out.println("Error trying to insert READER at [DB.DAOs.Users.Editor].[EditorInsertDAO] " + e.getMessage());
                return 0;
            }
        }
        return DAOResults.EMAIL_IN_USE.getCode(); // -1 means the email is registed
    }

    /**
     * Insert multiple categories to Db into User_Categories_Interest table
     *
     * @param email
     * @param category
     * @return
     */
    public int insertCategory(String email, String category) {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_INSERT_USER_CATEG)) {
            ps.setString(1, email);
            ps.setString(2, category);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to insert READER CATEGORIES INTREST at [DB.DAOS.Users.Reader].[ReaderInsert] " + e.getMessage());
            return 0;
        }
    }

    public int insertCategories(String reader, ArrayList<String> categories) {
        int results = 0;
        int subResult;
        // clear actual categories
        // delete
        new ReaderDelete().deleteCategoryIntrest(true, reader, "");
        // insert
        for (String category : categories) {
            subResult = insertCategory(reader, category);
            results += subResult;
        }
        return results;
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
