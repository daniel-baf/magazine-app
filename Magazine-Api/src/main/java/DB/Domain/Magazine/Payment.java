package DB.Domain.Magazine;

/**
 *
 * @author jefemayoneso
 */
public class Payment {

    private int subscription;
    private Double editorFee;
    private Double companyFee;

    public Payment() {
    }

    public Payment(int subscription, Double editorFee, Double companyFee) {
        this.subscription = subscription;
        this.editorFee = editorFee;
        this.companyFee = companyFee;
    }

    /**
     * @return the subscription
     */
    public int getSubscription() {
        return subscription;
    }

    /**
     * @param subscription the subscription to set
     */
    public void setSubscription(int subscription) {
        this.subscription = subscription;
    }

    /**
     * @return the editorFee
     */
    public Double getEditorFee() {
        return editorFee;
    }

    /**
     * @param editorFee the editorFee to set
     */
    public void setEditorFee(Double editorFee) {
        this.editorFee = editorFee;
    }

    /**
     * @return the companyFee
     */
    public Double getCompanyFee() {
        return companyFee;
    }

    /**
     * @param companyFee the companyFee to set
     */
    public void setCompanyFee(Double companyFee) {
        this.companyFee = companyFee;
    }

}
