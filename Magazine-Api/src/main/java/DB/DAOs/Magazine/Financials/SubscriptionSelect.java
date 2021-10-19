package DB.DAOs.Magazine.Financials;

import DB.DBConnection;
import DB.Domain.Financial.Subscription;
import Parsers.Parser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Execute the SELECT for Subscription
 *
 * @author jefemayoneso
 */
public class SubscriptionSelect {

    private final String SQL_SELECT_ACTIVE_SUB = "SELECT * FROM Subscription WHERE reader = ? AND expiration_date >= NOW() LIMIT ? OFFSET ?";

    /**
     * This method gets all active subscription for a reader from DB
     *
     * @param reader
     * @return
     */
    public ArrayList<Subscription> select(String reader, int limit, int offset) {
        ArrayList<Subscription> subs = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_ACTIVE_SUB)) {
            ps.setString(1, reader);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subs.add(getSubFromRs(rs));
            }
        } catch (Exception e) {
            System.out.println("Error while trying to get Sub at [SUbscriptionSelect] " + e.getMessage());
        }
        return subs;
    }

    private Subscription getSubFromRs(ResultSet rs) throws SQLException {
        Parser parser = new Parser();
        return new Subscription(rs.getInt("id"), rs.getInt("months"),
                parser.toLocalDate(rs.getDate("expiration_date")),
                parser.toLocalDate(rs.getDate("acquisition_date")),
                rs.getString("magazine"),
                rs.getString("reader"));
    }

}
