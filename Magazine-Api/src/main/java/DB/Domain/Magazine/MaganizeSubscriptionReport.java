package DB.Domain.Magazine;

import DB.Domain.Financial.Subscription;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class MaganizeSubscriptionReport {

    private Magazine magazine;
    private ArrayList<Subscription> subscriptions;

    public MaganizeSubscriptionReport() {
    }

    public MaganizeSubscriptionReport(Magazine magazine, ArrayList<Subscription> subscriptions) {
        this.magazine = magazine;
        this.subscriptions = subscriptions;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

}
