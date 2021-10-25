package Models;

import BackendUtilities.Parser;
import DB.DAOs.Magazine.Financials.SubscriptionSelect;
import DB.DAOs.Magazine.MagazineSelect;
import DB.DAOs.Magazine.Financials.AdSelect;
import DB.Domain.forJasperReports.EarningResult;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author jefemayoneso
 */
public class TotalEarningsModel {

    public ArrayList<EarningResult> getTotalEarnings(Date date1, Date date2, boolean validDates) {
        Double reportAmmountParcialEntry;
        ArrayList<EarningResult> earnings = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Parser parser = new Parser();
        MagazineSelect magSelect = new MagazineSelect();
        SubscriptionSelect subSelect = new SubscriptionSelect();
        AdSelect adSelect = new AdSelect();
        // dates
        Date dateTmp;
        if (validDates) {
            dateTmp = date1;
        } else {
            dateTmp = magSelect.getFirstMagDate();
            date2 = new Date(System.currentTimeMillis());
        }

        while (dateTmp.before(date2)) {
            try {
                calendar.setTime(dateTmp);
            } catch (Exception e) {
                System.out.println("Cannot sum date " + e.getMessage());
            }
            // get maintance costs
            earnings.add(new EarningResult("Costo mantenimiento", 0, magSelect.getMaintanceTotal(), dateTmp));
            // get earns by subscriptions
            reportAmmountParcialEntry = subSelect.getSubsEntries(dateTmp);
            if (reportAmmountParcialEntry != null && reportAmmountParcialEntry != 0) {
                earnings.add(new EarningResult("Subscripciones", reportAmmountParcialEntry, 0, dateTmp));
            }
            // get entries by ads
            reportAmmountParcialEntry = adSelect.getEarningsAds(dateTmp);
            if (reportAmmountParcialEntry != null && reportAmmountParcialEntry != 0) {
                earnings.add(new EarningResult("Pago de anuncios", reportAmmountParcialEntry, 0, dateTmp));
            }
            // next day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            dateTmp = parser.toDate(sdf.format(calendar.getTime()));
        }
        return earnings;
    }

}
