package DB.DAOs.Magazine;

import DB.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * This class makes all DATABSE DAO from Company Fee
 *
 * @author jefemayoneso
 */
public class CompanyFeeDAO {

    private String SQL_SELECT_COMP_FEE = "SELECT * FROM Company_Fee";

    public ArrayList<Double> select() {
        try ( PreparedStatement ps = DBConnection.getConnection().prepareCall(SQL_SELECT_COMP_FEE)) {
            ArrayList<Double> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getDouble("percentaje"));
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error trying to get Company's fee at [COmpanyFeeDAO] " + e.getMessage());
            return null;
        }
    }
}
