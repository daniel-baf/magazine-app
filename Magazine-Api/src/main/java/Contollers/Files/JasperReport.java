package Contollers.Files;

import BackendUtilities.Parser;
import BackendUtilities.VarsChecker;
import DB.GeneralPaths;
import BackendUtilities.JasperService;
import DB.DAOs.Magazine.MagazineSelect;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "JasperReport", urlPatterns = {"/JasperReport"})
public class JasperReport extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        VarsChecker vc = new VarsChecker();

        boolean validDates = vc.needReportByDates(request.getParameter("date-start"), request.getParameter("date-end"));
        Date date1 = null;
        Date date2 = null;
        if (validDates) {
            Parser parser = new Parser();
            date1 = parser.toDate(request.getParameter("date-start"));
            date2 = parser.toDate(request.getParameter("date-end"));
        }
        try {
            switch (request.getParameter("type")) {
                case "EDITOR":
                    respondWithEditorRep(request, response, validDates, date1, date2);
                    break;
                default:
                    System.out.println("UNKNOWN action at JasperReport class");
            }
        } catch (Exception ex) {
            System.out.println("Error taking jasper " + ex.getMessage());
        }
    }

    private void respondWithEditorRep(HttpServletRequest request, HttpServletResponse response, boolean validDates, Date date1, Date date2) throws IOException, JRException {
        JasperService jm = new JasperService();
        String subPath = GeneralPaths.JASPER_EDITOR_SUB_PATH.getMessage();
        try {
            switch (request.getParameter("action")) {
                case "comments-mag":
                    String subPathMagComments = validDates ? "CommentsSubRep.jasper": "CommentsSubRepNoParms.jasper";
                    jm.printReport(response.getOutputStream(), jm.getRespectiveEditorJasperPath("comments-mag", validDates),
                            jm.getOwnerDatesMap(date1, date2, request.getParameter("owner"), validDates, subPath + subPathMagComments));
                    break;
                case "subs-mag":
                    String subPathMagSubs = validDates ? "SubscriptionSubRep.jasper": "SubscriptionSubRepNoParms.jasper";
                    jm.printReport(response.getOutputStream(), jm.getRespectiveEditorJasperPath("subs-mag", validDates),
                            jm.getOwnerDatesMap(date1, date2, request.getParameter("owner"), validDates, subPath + subPathMagSubs));
                    break;
                case "most-liked":
                    String mag = new MagazineSelect().selectMostLikedByUser(request.getParameter("owner"), date1, date2, validDates);
                    Map<String, Object> mp = jm.getOwnerDatesMap(date1, date2, request.getParameter("owner"), validDates, "");
                    mp.put("magazine", mag);
                    jm.printReport(response.getOutputStream(), jm.getRespectiveEditorJasperPath("most-liked", validDates), mp);
                    break;
                case "earnings":
                    jm.printReport(response.getOutputStream(), jm.getRespectiveEditorJasperPath("earnings", validDates),
                            jm.getOwnerDatesMap(date1, date2, request.getParameter("owner"), validDates, ""));
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
