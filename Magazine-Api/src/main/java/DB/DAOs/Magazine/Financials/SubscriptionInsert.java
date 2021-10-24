package DB.DAOs.Magazine.Financials;

import DB.DAOs.Magazine.MagazineSelect;
import DB.Domain.Financial.Payment;
import DB.Domain.Financial.Subscription;
import BackendUtilities.Parser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This DAO class is used to insert Subscription to DB
 *
 * @author jefemayoneso
 */
public class SubscriptionInsert {

    private String SQL_INSERT_SUB = "INSERT INTO Subscription (months, expiration_date, acquisition_date, magazine, reader) VALUES (?, ?, ?, ?, ?)";

    /**
     * Insert a subscription, but, on each SUb, there's a payment associated
     *
     * @param sub
     * @return
     */
    public int insert(Subscription sub) {
        // INSERT payment
        int result = 0;
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_SUB, PreparedStatement.RETURN_GENERATED_KEYS)) {
            DB.DBConnection.getConnection().setAutoCommit(false);
            configurePSInsert(ps, sub);
            if (ps.executeUpdate() != 0) { // insert the new sub
                // get the sub fee and the id of subscriptions and price
                Double subFee = new CompanyFeeDAO().select().get(0);
                ResultSet rs = ps.getGeneratedKeys();
                Double price = new MagazineSelect().select(sub.getMagazine()).getMensuality();
                Payment payment;
                int tmp;
                if (rs.next()) {
                    tmp = rs.getInt(1);
                    sub.setId(tmp);
                    payment = new Payment(tmp, price * (1 - (subFee / 100)), price * (subFee / 100));
                    if (new PaymentInsert().insert(payment) != 0) {
                        result = 1;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error tying to INSERT a SUBSCRITION at [SubscriptionInsert] " + e.getMessage());
        } finally {
            try {
                if (result == 0) {
                    DB.DBConnection.getConnection().rollback();
                }
                // error while transaction
                DB.DBConnection.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Error while rollback at [SUbscriptionInsert] " + ex.getMessage());
            }
        }
        return result;
    }

    /**
     * This method is a shared method to configure a prepared statement for Subs
     *
     * @param ps
     * @param sub
     */
    private void configurePSInsert(PreparedStatement ps, Subscription sub) throws SQLException {
        Parser parser = new Parser();
        ps.setInt(1, sub.getMonths());
        ps.setDate(2, parser.toDate(sub.getExpirationDate()));
        ps.setDate(3, parser.toDate(sub.getAcquisitionDate()));
        ps.setString(4, sub.getMagazine());
        ps.setString(5, sub.getReader());
    }
}
