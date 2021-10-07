package Contollers.Logs;

import DB.Domain.Magazine.CategoryDAO;
import java.io.IOException;
import java.util.ArrayList;
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
        String action = request.getParameter("action"); // all or just one
        String name = request.getParameter("name"); // if send action "specific", send id

        if (action.equals("ALL")) {
            // get list and create json
            ArrayList<String> categories = new CategoryDAO().select();
//            new ObjectParser().getJsonFromStringArray(categories);
        }
    }

}
