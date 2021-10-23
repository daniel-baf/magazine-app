package Contollers.User;

import ApiMessages.SignupMessage;
import ApiMessages.StringArrayMessage;
import DB.DAOs.Users.ReaderInsert;
import Models.CategoriesModel;
import BackendUtilities.Parser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This controller helps getting all categories in DB
 *
 * @author jefemayoneso
 */
@WebServlet(name = "CategoriesSelectContoller", urlPatterns = {"/CategoriesSelectContoller"})
public class CategoriesSelectContoller extends HttpServlet {

    /**
     * Select the catgories on DB by user or all
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Parser parser = new Parser();
        try {
            StringArrayMessage stringArrMessage = new CategoriesModel().executeModel(request.getParameter("action"), request.getParameter("email"));
            response.getWriter().append(new Parser().toJSON(stringArrMessage, stringArrMessage.getClass()));
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error al intetnar buscar categorias " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update the categories from any user [Only Reader suppoerted]
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // validar que recibo
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Parser parser = new Parser();
        try {
            // response
            StringArrayMessage emailArray = (StringArrayMessage) parser.toObject(parser.getBody(request.getReader()), StringArrayMessage.class);
            // result of actions
            int result = new ReaderInsert().insertCategories(emailArray.getMessage(), emailArray.getArray());
            // message response
            String message = result == emailArray.getArray().size() ? "SUCCESS" : "CANNOT_APPL";
            // send response
            emailArray.setMessage(message);
            response.getWriter().append(parser.toJSON(emailArray, StringArrayMessage.class));
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error al intetnar buscar categorias en [CategoriesSelectController] " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

}
