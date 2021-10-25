package DB.DAOs.Users;

import BackendUtilities.FileWriterCP;
import DB.DBConnection;
import DB.Domain.Users.Reader;
import DB.GeneralPaths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.Part;

/**
 * This class insert Reader objects to DATABASE
 *
 * @author jefemayoneso
 */
public class ReaderInsert {

    private String SQL_INSERT_READER = "INSERT INTO `Reader` (`email`, `name`, `password`, `imgPath`) VALUES (?, ?, ?, ?)";
    private String SQL_INSERT_USER_CATEG = "INSERT INTO User_Intrest_Categories (reader, category) VALUES (?, ?)";

    /**
     * Insert a single Reader on DB
     *
     * @param reader
     * @return
     */
    public int insert(Reader reader, Part filePart, String absolutePath) {
        int result = 0;
        if (new UserCommonDAO().emailRegisted(reader.getEmail()) == null) { // verify if exist at any table
            try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_READER)) {
                reader.setImgPath(GeneralPaths.FILES_IMG_PATH_PROF_READER.getMessage() + reader.getEmail());
                configurePS(reader, ps);
                DBConnection.getConnection().setAutoCommit(false);
                if (ps.executeUpdate() != 0) {
                    // inserted, now write file
                    FileWriterCP fw = new FileWriterCP();
                    result = fw.write(filePart, reader.getImgPath(), absolutePath) ? 1 : 0;
                }
            } catch (SQLException e) {
                System.out.println("Error trying to insert READER at [DB.DAOs.Users.Editor].[EditorInsertDAO] " + e.getMessage());
            } finally {
                try {
                    if (result == 0) {
                        DBConnection.getConnection().rollback();
                    }
                    DBConnection.getConnection().setAutoCommit(true);
                } catch (Exception e) {
                    System.out.println("Error while rollback at REadInsert " + e.getMessage());
                }
            }
        } else {
            return GeneralPaths.EMAIL_IN_USE.getCode();
        }
        return result;
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
            DBConnection.getConnection().setAutoCommit(false); // transaction
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
        ps.setString(4, reader.getImgPath());
    }
}
