package DB.DAOs.Magazine.Financials;

import DB.DBConnection;
import DB.Domain.Financial.Subscription;
import BackendUtilities.Parser;
import DB.DAOs.Magazine.MagazineSelect;
import DB.Domain.Magazine.Magazine;
import DB.Domain.forJasperReports.MaganizeSubscriptionReport;
import DB.Domain.forJasperReports.ReaderSubscription;
import java.sql.Date;
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
    private final String SQL_SELECT_SUBS_FOR_MAG = "SELECT * FROM Subscription WHERE magazine = ? ";
    private final String SQL_SELECT_MOST_SUBS_MAGS = "SELECT COUNT(l.magazine) AS `subs`, m.*, m.editor FROM `Subscription` AS l INNER JOIN Magazine as m "
            + "ON m.name = l.magazine GROUP BY l.magazine  ORDER BY `subs` DESC LIMIT 5;";
    private final String SQL_SELECT_TOTAL_EARN_REPORT = "SELECT SUM(p.company_fee) AS `entry` FROM Payment AS p INNER JOIN Subscription as s "
            + "ON s.id = p.subscription INNER JOIN Magazine as m ON m.name = s.magazine WHERE s.acquisition_date = ? ";
    private final String SQL_SELECT_EARNGS_EDITOR = "SELECT p.editor_fee, p.company_fee, s.* FROM Payment AS p INNER JOIN Subscription AS s ON p.subscription = s.id WHERE s.magazine = ?";

    /**
     * This method gets all active subscription for a reader from DB
     *
     * @param reader
     * @param limit
     * @param offset
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

    /**
     * Sharesd method to get a Sub from a resultset
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Subscription getSubFromRs(ResultSet rs) throws SQLException {
        Parser parser = new Parser();
        return new Subscription(rs.getInt("id"), rs.getInt("months"),
                parser.toLocalDate(rs.getDate("expiration_date")),
                parser.toLocalDate(rs.getDate("acquisition_date")),
                rs.getString("magazine"),
                rs.getString("reader"));
    }

    /**
     * Return a list of subscriptions that belongs to a magazine
     *
     * @param betweenDates
     * @param date1
     * @param date2
     * @param magazine
     * @return
     */
    public ArrayList<Subscription> select(boolean betweenDates, Date date1, Date date2, String magazine) {
        ArrayList<Subscription> subs = new ArrayList<>();
        String SQL_TMP = betweenDates ? SQL_SELECT_SUBS_FOR_MAG + " AND acquisition_date BETWEEN ? AND ? " : SQL_SELECT_SUBS_FOR_MAG;
        SQL_TMP += " ORDER BY acquisition_date DESC";
        // SQL
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            ps.setString(1, magazine);
            if (betweenDates) {
                ps.setDate(2, date1);
                ps.setDate(3, date2);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subs.add(getSubFromRs(rs));
            }
        } catch (Exception e) {
            System.out.println("Cannot get magazine sub at SubsSelect " + e.getMessage());
        }
        return subs;
    }

    /**
     * Return a list of magazines with each subscription
     *
     * @param betweenDates
     * @param date2
     * @param date1
     * @return
     */
    public ArrayList<MaganizeSubscriptionReport> selectMostSubscribedMags(boolean betweenDates, Date date1, Date date2) {
        ArrayList<MaganizeSubscriptionReport> mags = new ArrayList<>();
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_MOST_SUBS_MAGS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // get subs
                Magazine mag = new MagazineSelect().getMagazineFromRS(rs);
                ArrayList<Subscription> subs = select(betweenDates, date1, date2, mag.getName());
                mags.add(new MaganizeSubscriptionReport(mag.getName(), subs));
            }
        } catch (Exception e) {
            System.out.println("Error looking for 5 most liked mags " + e.getMessage());
        }
        return mags;
    }

    public ArrayList<ReaderSubscription> getSubsWithFee(boolean betweenDates, Date date1, Date date2, String mag, boolean adminFee) {
        ArrayList<ReaderSubscription> subs = new ArrayList<>();
        String SQL_TMP = SQL_SELECT_EARNGS_EDITOR;
        SQL_TMP += betweenDates ? " AND s.acquisition_date BETWEEN ? AND ? " : "";
        // GET DATA
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_TMP)) {
            ps.setString(1, mag);
            if (betweenDates) {
                ps.setDate(2, date1);
                ps.setDate(3, date2);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // get subs
                Double tmp = adminFee ? rs.getDouble("company_fee") : rs.getDouble("editor_fee");

                subs.add(new ReaderSubscription(tmp, getSubFromRs(rs)));
            }
        } catch (Exception e) {
            System.out.println("Error looking for subs and earnings " + e.getMessage());
        }
        return subs;
    }

    /**
     * Return a list of EarningsReult to get a final financial balance
     *
     * @param date1 the start date for report
     * @param date2 the end date for report
     * @param validDates no one date is null?
     * @return
     */
    public Double getSubsEntries(Date date) {
        // execute query
        try ( PreparedStatement ps = DBConnection.getConnection().prepareStatement(SQL_SELECT_TOTAL_EARN_REPORT)) {
            ps.setDate(1, date);
            // now call the data
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("entry");
            }
        } catch (Exception e) {
            System.out.println("Error getting subscription " + e.getMessage());
        }
        return null;
    }

}
