package DB.Domain.forJasperReports;

import DB.Domain.Financial.Subscription;

/**
 *
 * @author jefemayoneso
 */
public class ReaderSubscription {

    private Double fee;
    private Subscription subscription;

    public ReaderSubscription() {
    }

    public ReaderSubscription(Double fee, Subscription subscription) {
        this.fee = fee;
        this.subscription = subscription;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

}
