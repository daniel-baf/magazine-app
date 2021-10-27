package DB.Domain.forJasperReports;

import DB.Domain.Magazine.Magazine;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class CompanyEarning {

    private Magazine magazine;
    private ArrayList<ReaderSubscription> subscriptions;

    public CompanyEarning() {
    }

    public CompanyEarning(Magazine magazine, ArrayList<ReaderSubscription> subscriptions) {
        this.magazine = magazine;
        this.subscriptions = subscriptions;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public ArrayList<ReaderSubscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<ReaderSubscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

}
