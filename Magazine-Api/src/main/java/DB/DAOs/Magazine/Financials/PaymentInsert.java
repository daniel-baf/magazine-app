package DB.DAOs.Magazine.Financials;

import DB.Domain.Financial.Payment;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Insert a payment on DATABASE
 *
 * @author jefemayoneso
 */
public class PaymentInsert {

    private String SQL_INSERT_PAYMENT = "INSERT INTO Payment (subscription, editor_fee, company_fee) VALUES (?, ?, ?)";

    /**
     * Insert a payment to DATABASE
     *
     * @param payment
     * @return
     */
    public int insert(Payment payment) {
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_PAYMENT)) {
            configurePSInsert(ps, payment);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error trying to insert payment at [PaymentInsert] " + e.getMessage());
            return 0;
        }
    }

    /**
     * A shared method for multiple insert
     *
     * @param ps
     * @param payment
     * @throws SQLException
     */
    private void configurePSInsert(PreparedStatement ps, Payment payment) throws SQLException {
        ps.setInt(1, payment.getSubscription());
        ps.setDouble(2, payment.getEditorFee());
        ps.setDouble(3, payment.getCompanyFee());
    }

}
