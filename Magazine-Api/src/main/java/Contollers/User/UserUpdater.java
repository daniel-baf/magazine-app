package Contollers.User;

import APIMessages.SignupMessage;
import Models.UserModel;
import Parsers.Parser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "UserUpdater", urlPatterns = {"/UserUpdater"})
public class UserUpdater extends HttpServlet {

    /**
     * Controller, update the info of User/s
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        Parser parser = new Parser();
        try {
            SignupMessage message = new UserModel().updateUser(request.getReader());
            response.getWriter().append(parser.toJSON(message, SignupMessage.class));
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error al intentar Iniciar sesion en [UserUpdater] " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
