package Contollers.Logs;

import APIErrors.SignupMessage;
import APIErrors.StringArrayMessage;
import DB.DAOs.Users.Reader.ReaderInsert;
import DB.Domain.Magazine.CategoryDAO;
import Parsers.Parser;
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
        Parser parser = new Parser();
        try {
            StringArrayMessage stringArrMessage = new StringArrayMessage();

            switch (request.getParameter("action")) {
                case "BY_USER":
                    stringArrMessage.setStrings(new CategoryDAO().select(true, request.getParameter("email")));
                    stringArrMessage.setMessage("FOUND");
                    break;
                case "ALL":
                    stringArrMessage.setStrings(new CategoryDAO().select(false, null));
                    stringArrMessage.setMessage("FOUND");
                    break;
                default:
                    stringArrMessage.setMessage("NO_ACTION");
                    break;
            }
            response.getWriter().append(new Parser().getJsonFromObject(stringArrMessage, stringArrMessage.getClass()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.getWriter().append(parser.getJsonFromObject(new SignupMessage("Error al intetnar buscar categorias " + e.getMessage(), null), SignupMessage.class));
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
        Parser parser = new Parser();
        try {
            StringArrayMessage emailArray = (StringArrayMessage) parser.getObjectFromJson(parser.getBody(request.getReader()), StringArrayMessage.class);
            int result = new ReaderInsert().insertCategories(emailArray.getMessage(), emailArray.getArray());
            if (result == emailArray.getArray().size()) {
                emailArray.setMessage("SUCCESS");
            } else {
                emailArray.setMessage("CANNNOT_APPL " + emailArray.getArray().size());
            }
            response.getWriter().append(parser.getJsonFromObject(emailArray, StringArrayMessage.class));
        } catch (Exception e) {
            response.getWriter().append(parser.getJsonFromObject(new SignupMessage("Error al intetnar buscar categorias en [CategoriesSelectController] " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

}
