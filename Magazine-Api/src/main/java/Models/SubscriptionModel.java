package Models;

import APIMessages.SubscriptionMessage;
import DB.DAOs.Magazine.Financials.SubscriptionInsert;
import DB.DAOs.Magazine.Financials.SubscriptionSelect;
import DB.Domain.Financial.Subscription;
import BackendUtilities.Parser;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * Manage all transactions about a Subscription
 *
 * @author jefemayoneso
 */
public class SubscriptionModel {

    /**
     * Take a list a subscription on database to guaranty access to magazines
     *
     * @param request
     * @return
     * @throws IOException
     */
    public SubscriptionMessage newSubscription(HttpServletRequest request) throws IOException {
        // variables
        Parser parser = new Parser();
        SubscriptionMessage subMsg = new SubscriptionMessage();
        // call method
        subMsg.setSubscription((Subscription) parser.toObject(parser.getBody(request.getReader()), Subscription.class)); // we receibe a JSON 
        subMsg.getSubscription().setAcquisitionDate(parser.toLocalDate(subMsg.getSubscription().getAcquisitionDateString()));
        subMsg.getSubscription().setExpirationDate(parser.toLocalDate(subMsg.getSubscription().getExpirationDateString()));
        int result = new SubscriptionInsert().insert(subMsg.getSubscription());
        String message = result != 0 ? "NO_ERROR" : "ERROR_INSERT";
        subMsg.setMessage(message);
        return subMsg;
    }

    /**
     * Return a list of subscription
     *
     * @param user
     * @param action
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<Subscription> getSubscription(String user, String action, int limit, int offset) {
        switch (action) {
            case "ACTIVE_USER":
                return new SubscriptionSelect().select(user, limit, offset);
            default:
                System.out.println("UNKNOWN action at [SubscriptionModel]");
                return null;
        }
    }
}
