package Contollers.User;

import ApiMessages.SignupMessage;
import DB.Domain.Users.User;
import Models.LoginCheckerModel;
import BackendUtilities.Parser;
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
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            SignupMessage message = new LoginCheckerModel().verifyUser(request.getReader());
            response.getWriter().append(new Parser().toJSON(message, message.getClass()));
        } catch (Exception e) {
            response.getWriter().append(new Parser().toJSON(new SignupMessage("Error al intentar verificar al usuario " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Parser parser = new Parser();
        try {
            // switch
            switch (request.getParameter("action")) {
                case "BASIC_INFO":
                    User user = (User) new LoginCheckerModel().searchUser(request.getParameter("email"));
                    response.getWriter().append(parser.toJSON(user, user.getClass()));
                    break;
                default:
                    response.getWriter().append(null);
            }

        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error buscando usuario en [UserChecker] " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
