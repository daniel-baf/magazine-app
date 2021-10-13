package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Constants
    private final String MYSQl_URL = "jdbc:mysql://localhost:3306/Magazine_Web?autoReconnect=true&characterEncoding=UTF-8";
    private final String MYSQL_USER = "ipc2";
    private final String MYSQL_PASSWORD = "ipc2+contraPjct0s";
    private static Connection connection = null;

    /**
     * this method returns a connection, and creates a new one only if did not
     * exist
     *
     * @return
     */
    public static Connection getConnection() {
        if (connection == null) {
            new DBConnection();
        }
        return connection;
    }

    /**
     * this method creates a persistent connection to MYSQL
     */
    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(MYSQl_URL, MYSQL_USER, MYSQL_PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error trying to access DB at [DB].[ConnectionDB] " + ex.getMessage());
        }
    }
}
