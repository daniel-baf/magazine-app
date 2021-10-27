package Contollers.Files;

import BackendUtilities.JasperService;
import BackendUtilities.Parser;
import BackendUtilities.VarsChecker;
import DB.DAOs.Magazine.Financials.CommentSelect;
import DB.DAOs.Magazine.Financials.SubscriptionSelect;
import DB.Domain.forJasperReports.CompanyEarning;
import DB.Domain.forJasperReports.EarningResult;
import DB.Domain.forJasperReports.EarningsByAdvertiser;
import DB.Domain.forJasperReports.MaganizeSubscriptionReport;
import DB.Domain.forJasperReports.MagazineCommentsReport;
import DB.Models.forJasperReports.AdminReportModel;
import DB.Models.forJasperReports.EditorReportModel;
import Models.TotalEarningsModel;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "HTMLReportAdmin", urlPatterns = {"/HTMLReportAdmin"})
public class HTMLReportAdmin extends HttpServlet {

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
        try {
            EditorReportModel erm = new EditorReportModel();
            VarsChecker vc = new VarsChecker();
            // varibales
            boolean validDates = vc.needReportByDates(request.getParameter("date-start"), request.getParameter("date-end"));
            Date date1 = null;
            Date date2 = null;
            if (validDates) {
                date1 = parser.toDate(request.getParameter("date-start"));
                date2 = parser.toDate(request.getParameter("date-end"));
            }

            AdminReportModel reportModel = new AdminReportModel();
            switch (request.getParameter("type")) {
                case "earns-mags":
                    // sout finding
                    ArrayList<CompanyEarning> magsEarning = reportModel.getEarningsByMags(date1, date2, validDates);
                    response.getWriter().append(parser.toJSON(magsEarning, magsEarning.getClass()));
                    break;
                case "earns-advers":
                    ArrayList<EarningsByAdvertiser> advsEarnings = reportModel.getEarningsByAdvertisers(date1, date2, validDates);
                    response.getWriter().append(parser.toJSON(advsEarnings, advsEarnings.getClass()));
                    break;
                case "most-commented":
                    ArrayList<MagazineCommentsReport> magsComments = new CommentSelect().getMagazineMostCommentedes(date1, date2, validDates);
                    response.getWriter().append(parser.toJSON(magsComments, magsComments.getClass()));
                    break;
                case "most-subscribed":
                    ArrayList<MaganizeSubscriptionReport> magsSubs = new SubscriptionSelect().selectMostSubscribedMags(validDates, date1, date2);
                    response.getWriter().append(parser.toJSON(magsSubs, magsSubs.getClass()));
                    break;
                case "total-earnings":
                    Map<String, Object> hashMap = new JasperService().getBasicKeyMapForJasper(date1, date2, "", validDates, "");
                    hashMap.put("list", hashMap);
                    ArrayList<EarningResult> earnings = new TotalEarningsModel().getTotalEarnings(date1, date2, validDates);
                    response.getWriter().append(parser.toJSON(earnings, earnings.getClass()));
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.getWriter().append("Error " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
