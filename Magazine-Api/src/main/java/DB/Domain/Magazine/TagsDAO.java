package DB.Domain.Magazine;

import DB.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class TagsDAO {

    private String SQL_SELECT_TAGS = "SELECT * FROM Tag";

    public ArrayList<String> select() {
        ArrayList<String> tags = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_TAGS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tags.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Error at TagsDAO " + e.getMessage());
        }
        return tags;
    }
}
