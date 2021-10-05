package Contollers.Login;

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
        User user = new LoginCheckerModel().verifyUser(request.getReader());
        response.getWriter().append(new ObjectParser().getJsonFromUser(user));
    }
}
