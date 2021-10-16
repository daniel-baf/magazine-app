package Models;

import APIMessages.SubscriptionMessage;
import DB.Domain.Magazine.Subscription;
import Parsers.Parser;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 * Manage all transactions about a Subscription
 *
 * @author jefemayoneso
 */
public class SubscriptionModel {

    public SubscriptionMessage newSubscription(HttpServletRequest request) throws IOException {
        // variables
        Parser parser = new Parser();
        SubscriptionMessage subMsg = new SubscriptionMessage();
        // call method
        subMsg.setSubscription((Subscription) parser.toObject(parser.getBody(request.getReader()), Subscription.class)); // we receibe a JSON 
        return subMsg;
    }

}
