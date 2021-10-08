package Contollers.Logs;

import APIErrors.SignupMessage;
import DB.Domain.Users.User;
import Models.LoginCheckerModel;
import Parsers.Gson.ObjectParser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserChecker", urlPatterns = {"/UserChecker"})
public class UserChecker extends HttpServlet {

    /**
     * This method respond with a JSON with the info of the user to create a
     * session
     *
     * @param request httpRequest
     * @param response httpResponse
     * @throws ServletException not found
     * @throws IOException internal error
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SignupMessage message = new LoginCheckerModel().verifyUser(request.getReader());
            response.getWriter().append(new ObjectParser().getJsonFromObject(message, message.getClass()));
        } catch (Exception e) {
            System.out.println("Error at [Contoller.Logs].[UserChecker] " + e.getMessage());
        }
    }

    /**
     * This method return User info as get method
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // switch
            switch (request.getParameter("action")) {
                case "BASIC_INFO":
                    User user = (User) new LoginCheckerModel().searchUser(request.getParameter("email"));
                    response.getWriter().append(new ObjectParser().getJsonFromObject(user, user.getClass()));
                    break;
                default:
                    response.getWriter().append(null);
            }

        } catch (Exception e) {
            System.out.println("Error at [Contoller.Logs].[UserChecker] " + e.getMessage());
        }
    }
}
