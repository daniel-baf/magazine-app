package Contollers.Files;

import BackendUtilities.Parser;
import BackendUtilities.VarsChecker;
import DB.Domain.forJasperReports.EditorEarnings;
import DB.Domain.forJasperReports.MaganizeSubscriptionReport;
import DB.Domain.forJasperReports.MagazineCommentsReport;
import DB.Domain.forJasperReports.MagazineLikeReport;
import DB.Models.forJasperReports.EditorReportModel;
import java.io.IOException;
import java.sql.Date;
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
@WebServlet(name = "HTMLReportEditor", urlPatterns = {"/HTMLReportEditor"})
public class HTMLReportEditor extends HttpServlet {

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
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Parser parser = new Parser();
        EditorReportModel erm = new EditorReportModel();
        VarsChecker vc = new VarsChecker();
        // varibales
        boolean validDates = vc.needReportByDates(request.getParameter("date-start"), request.getParameter("date-end"));
        Date date1 = null;
        Date date2 = null;
        String editor = request.getParameter("editor");
        if (validDates) {
            date1 = parser.toDate(request.getParameter("date-start"));
            date2 = parser.toDate(request.getParameter("date-end"));
        }

        switch (request.getParameter("type")) {
            case "comments-mag":
                ArrayList<MagazineCommentsReport> tmp = erm.getMagsMostCommented(editor, date1, date2, validDates);
                response.getWriter().append(parser.toJSON(tmp, tmp.getClass()));
                break;
            case "subs-mag":
                ArrayList<MaganizeSubscriptionReport> magSubs = erm.getMagsMostSUbscribed(editor, date1, date2, validDates);
                response.getWriter().append(parser.toJSON(magSubs, magSubs.getClass()));
                break;
            case "most-liked":
                ArrayList<MagazineLikeReport> magLikes = erm.getMagazineLikesReport(editor, date1, date2, validDates);
                response.getWriter().append(parser.toJSON(magLikes, magLikes.getClass()));
                break;
            case "earnings":
                ArrayList<EditorEarnings> editorEarns = erm.getEditorEarnings(editor, date1, date2, validDates);
                response.getWriter().append(parser.toJSON(editorEarns, editorEarns.getClass()));
                break;
        }
    }
}
