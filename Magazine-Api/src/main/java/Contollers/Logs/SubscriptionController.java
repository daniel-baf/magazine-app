package Contollers.Logs;

import APIMessages.SignupMessage;
import APIMessages.SubscriptionMessage;
import DB.Domain.Magazine.Subscription;
import Models.SubscriptionModel;
import Parsers.Parser;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * keep track of all payments
 *
 * @author jefemayoneso
 */
@WebServlet(name = "SubscriptionController", urlPatterns = {"/SubscriptionController"})
public class SubscriptionController extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Parser parser = new Parser();
        try {
            SubscriptionMessage submsg = new SubscriptionModel().newSubscription(request);
            response.getWriter().append(parser.toJSON(submsg, SubscriptionMessage.class));
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error trying to insert a new subscrition at [SubscriptonController] " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Get list of Subscriptions
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Parser parser = new Parser();
        try {
            ArrayList<Subscription> subs = new SubscriptionModel().getSubscription(
                    request.getParameter("user"),
                    request.getParameter("action"),
                    parser.toInteger(request.getParameter("limit")),
                    parser.toInteger(request.getParameter("offset")));
            response.getWriter().append(parser.toJSON(subs, new ArrayList<Subscription>().getClass()));
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error trying to insert a new subscrition at [SUbscriptionController] " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

}
