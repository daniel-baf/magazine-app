package ApiMessages;

import DB.Domain.Financial.Subscription;

/**
 * Element used for communication with subscription tasks
 *
 * @author jefemayoneso
 */
public class SubscriptionMessage {

    private Subscription subscription;
    private String message;

    public SubscriptionMessage() {
    }

    public SubscriptionMessage(Subscription subscription, String message) {
        this.subscription = subscription;
        this.message = message;
    }

    /**
     * @return the subscription
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * @param subscription the subscription to set
     */
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
