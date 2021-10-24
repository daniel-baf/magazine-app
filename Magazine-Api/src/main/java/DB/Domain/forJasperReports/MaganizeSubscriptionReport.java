package DB.Domain.forJasperReports;

import DB.Domain.Financial.Subscription;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jefemayoneso
 */
public class MaganizeSubscriptionReport {

    private String magazine;
    private List<Subscription> subscriptions;

    public MaganizeSubscriptionReport() {
    }

    public MaganizeSubscriptionReport(String magazine, ArrayList<Subscription> subscriptions) {
        this.magazine = magazine;
        this.subscriptions = subscriptions;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

}
