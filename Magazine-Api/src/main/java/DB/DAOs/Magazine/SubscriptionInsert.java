package DB.DAOs.Magazine;

import DB.Domain.Magazine.Payment;
import DB.Domain.Magazine.Subscription;
import Parsers.Parser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This DAO class is used to insert Subscription to DB
 *
 * @author jefemayoneso
 */
public class SubscriptionInsert {

    private String SQL_INSERT_SUB = "INSERT INTO Subscription (months, expiration_date, acquisition_date, magazine, reader) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * Insert a subscription, but, on each SUb, there's a payment associated
     *
     * @param sub
     * @return
     */
    public int insert(Subscription sub) {
        // INSERT payment
        int result = 0;
        System.out.println("se inicia el insert");
        try ( PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(SQL_INSERT_SUB, PreparedStatement.RETURN_GENERATED_KEYS)) {
            DB.DBConnection.getConnection().setAutoCommit(false);
            System.out.println("autocomit falso");
            configurePSInsert(ps, sub);
            if (ps.executeUpdate() != 0) { // insert the new sub
                System.out.println("se ha insertado la subscripcion");
                // get the sub fee and the id of subscriptions and price
                Double subFee = new CompanyFeeDAO().select().get(0);
                ResultSet rs = ps.getGeneratedKeys();
                Double price = new MagazineSelect().select(sub.getMagazine()).getMensuality();
                Payment payment;
                int tmp;
                if (rs.next()) {
                    System.out.println("se ha obtenido el id de la sub");
                    tmp = rs.getInt(1);
                    System.out.println(tmp);
                    payment = new Payment(tmp, price * (1 - subFee), price * subFee);
                    if (new PaymentInsert().insert(payment) != 0) {
                        System.out.println("se ha puesto el pago");
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
