package Contollers.Logs;

import APIErrors.StringArrayMessage;
import DB.Domain.Magazine.CategoryDAO;
import Parsers.Gson.ObjectParser;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringArrayMessage stringArrMessage = new StringArrayMessage();

        try {
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
        } catch (Exception e) {
            System.out.println("Error at [Controllers.logs].[CategoriesSelectController]");
            stringArrMessage.setMessage("ERROR " + e.getMessage());
        }

        response.getWriter().append(new ObjectParser().getJsonFromObject(stringArrMessage, stringArrMessage.getClass()));
    }

}
