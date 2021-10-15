package Contollers.Logs;

import APIMessages.SignupMessage;
import Models.SignupModel;
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
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {

    /**
     * Create a new profile
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
            SignupMessage supm = new SignupModel().signUp(request.getReader());
            response.getWriter().append(parser.toJSON(supm, supm.getClass()));
        } catch (IOException e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error al intentar Iniciar sesion en [UserActionsAbout] " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
