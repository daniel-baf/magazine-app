package DB.Models.forJasperReports;

import DB.DAOs.Magazine.Financials.AdSelect;
import DB.DAOs.Magazine.Financials.AdvertiserSelect;
import DB.DAOs.Magazine.Financials.SubscriptionSelect;
import DB.DAOs.Magazine.MagazineSelect;
import DB.Domain.Magazine.Magazine;
import DB.Domain.forJasperReports.CompanyEarning;
import DB.Domain.forJasperReports.EarningsByAdvertiser;
import DB.Domain.forJasperReports.ReaderSubscription;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class AdminReportModel {

    public ArrayList<CompanyEarning> getEarningsByMags(Date date1, Date date2, boolean validDates) {
        ArrayList<CompanyEarning> earnings = new ArrayList<>();
        // get magazines
        ArrayList<Magazine> mags = new MagazineSelect().select();
        SubscriptionSelect subsSelect = new SubscriptionSelect();
        mags.forEach(mag -> {
            ArrayList<ReaderSubscription> subscription = subsSelect.getSubsWithFee(validDates, date1, date2, mag.getName(), true);
            earnings.add(new CompanyEarning(mag, subscription));
        });
        return earnings;
    }

    public ArrayList<EarningsByAdvertiser> getEarningsByAdvertisers(Date date1, Date date2, boolean validDates) {
        ArrayList<EarningsByAdvertiser> earns = new ArrayList<>();
        ArrayList<String> advertsers = new AdvertiserSelect().getAdvertisers();
        advertsers.forEach(advertser -> {
            earns.add(new EarningsByAdvertiser(advertser, new AdSelect().getAdsBelongAdvertiser(advertser, validDates, date1, date2)));
        });
        return earns;
    }

}
