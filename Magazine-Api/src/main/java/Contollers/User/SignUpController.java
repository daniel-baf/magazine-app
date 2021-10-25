package Contollers.User;

import ApiMessages.SignupMessage;
import BackendUtilities.Parser;
import DB.Domain.Users.User;
import Models.SignupModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100 // 100MB
)
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
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Parser parser = new Parser();
        String absolutePath = request.getServletContext().getRealPath("");

        try {
            // get absolute path of the application
            User user = (User) parser.toObject(request.getParameter("user"), User.class);
            Part filePart = request.getPart("profile-pic");
            SignupMessage supm = new SignupModel().signUp(user, filePart, request.getParameter("user"), absolutePath);
            response.getWriter().append(parser.toJSON(supm, supm.getClass()));
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error al intentar Iniciar sesion en [UserActionsAbout] " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
