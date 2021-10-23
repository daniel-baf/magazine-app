package Contollers.Magazine;

import ApiMessages.MagazineMessage;
import ApiMessages.SignupMessage;
import DB.Domain.Magazine.Magazine;
import Models.MagazineModel;
import BackendUtilities.Parser;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "MagazineContoller", urlPatterns = {"/MagazineContoller"})
public class MagazineContoller extends HttpServlet {

    /**
     * Manage the actions of Magazine
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
        try {
            MagazineMessage message = new MagazineModel().executeModel(request.getReader());
            response.getWriter().append(parser.toJSON(message, MagazineMessage.class));
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error trying to make a Magazine action at [MagazineController]" + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Parser parser = new Parser();
        try {
            ArrayList<Magazine> magazines = new MagazineModel().selectMagazines(request);
            response.getWriter().append(parser.toJSON(magazines, new ArrayList<Magazine>().getClass()));
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error trying to make a Magazine action at [MagazineController]" + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
