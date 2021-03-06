package Contollers.Files;

import BackendUtilities.Parser;
import BackendUtilities.VarsChecker;
import DB.GeneralPaths;
import BackendUtilities.JasperService;
import DB.DAOs.Magazine.Financials.SubscriptionSelect;
import DB.DAOs.Magazine.Financials.CommentSelect;
import DB.DAOs.Magazine.Financials.LikeSelect;
import DB.Domain.forJasperReports.EarningResult;
import DB.Domain.forJasperReports.MaganizeSubscriptionReport;
import DB.Domain.forJasperReports.MagazineCommentsReport;
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
        response.setHeader("Content-disposition", "attachment; filename=report.pdf");
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
                    printEditorReport(request, response, validDates, date1, date2);
                    break;
                case "ADMIN":
                    printAdminReport(request, response, validDates, date1, date2);
                    break;
                default:
                    System.out.println("UNKNOWN action at JasperReport class");
            }
        } catch (IOException | JRException ex) {
            System.out.println("Error taking jasper " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Print different jasper reports for Editors
     *
     * @param request
     * @param response
     * @param validDates
     * @param date1
     * @param date2
     * @throws IOException
     * @throws JRException
     */
    private void printEditorReport(HttpServletRequest request, HttpServletResponse response, boolean validDates, Date date1, Date date2) throws IOException, JRException {
        response.setHeader("Content-disposition", "attachment; filename=report.pdf");

        JasperService jm = new JasperService();
        String subPath = GeneralPaths.JASPER_EDITOR_SUB_PATH_RELATIVE.getMessage();
        String owner = request.getParameter("owner");

        switch (request.getParameter("action")) {
            case "comments-mag":
                String subPathMagComments = validDates ? "CommentsSubRep.jasper" : "CommentsSubRepNoParms.jasper";
                jm.printReport(response.getOutputStream(), jm.getMasterReportPathEditor("comments-mag", validDates),
                        jm.getBasicKeyMapForJasper(date1, date2, owner, validDates, subPath + subPathMagComments));
                break;
            case "subs-mag":
                String subPathMagSubs = validDates ? "SubscriptionSubRep.jasper" : "SubscriptionSubRepNoParms.jasper";
                jm.printReport(response.getOutputStream(), jm.getMasterReportPathEditor("subs-mag", validDates),
                        jm.getBasicKeyMapForJasper(date1, date2, owner, validDates, subPath + subPathMagSubs));
                break;
            case "most-liked":
                String mag = new LikeSelect().selectMostLikedByUser(owner, date1, date2, validDates);
                Map<String, Object> mp = jm.getBasicKeyMapForJasper(date1, date2, request.getParameter("owner"), validDates, "");
                mp.put("magazine", mag);
                jm.printReport(response.getOutputStream(), jm.getMasterReportPathEditor("most-liked", validDates), mp);
                break;
            case "earnings":
                jm.printReport(response.getOutputStream(), jm.getMasterReportPathEditor("earnings", validDates),
                        jm.getBasicKeyMapForJasper(date1, date2, owner, validDates, ""));
                break;

        }
    }

    /**
     * Print a report for admiun
     *
     * @param request
     * @param response
     * @param validDates
     * @param date1
     * @param date2
     * @throws IOException
     * @throws JRException
     */
    private void printAdminReport(HttpServletRequest request, HttpServletResponse response, boolean validDates, Date date1, Date date2) throws IOException, JRException {
        JasperService jm = new JasperService();
        String subPath = GeneralPaths.JASPER_ADMIN_SUB_PATH_RELATIVE.getMessage();

        switch (request.getParameter("action")) {
            case "earns-advers":
                jm.printReport(response.getOutputStream(), jm.getMasterReportPathAdmin("earns-advers", validDates),
                        jm.getBasicKeyMapForJasper(date1, date2, "", validDates, ""));
                break;
            case "most-subscribed":
                ArrayList<MaganizeSubscriptionReport> mags = new SubscriptionSelect().selectMostSubscribedMags(validDates, date1, date2);
                jm.printReportWithComplexBeans(mags, jm.getMasterReportPathAdmin("most-subscribed", validDates),
                        jm.getBasicKeyMapForJasper(null, null, "", validDates, subPath + "TopMagsDetails.jasper"), response.getOutputStream());
                break;
            case "most-commented": //
                ArrayList<MagazineCommentsReport> magsComments = new CommentSelect().getMagazineMostCommentedes(date1, date2, validDates);
                jm.printReportWithComplexBeans1(magsComments, jm.getMasterReportPathAdmin("most-commented", validDates),
                        jm.getBasicKeyMapForJasper(null, null, "", validDates, subPath + "MasCommentDetails.jasper"), response.getOutputStream());
                break;
            case "earns-mags":
                String subRepTmp = validDates ? "MagEarningsSubDetail.jasper" : "MagEarningsSubDetailNoParms.jasper";
                jm.printReport(response.getOutputStream(), jm.getMasterReportPathAdmin("earns-mags", validDates),
                        jm.getBasicKeyMapForJasper(date1, date2, "", validDates, subPath + subRepTmp));
                break;
            case "total-earnings":
                // get map and add object
                Map<String, Object> hashMap = jm.getBasicKeyMapForJasper(date1, date2, "", validDates, "");
                hashMap.put("list", hashMap);
                // call tmp
                ArrayList<EarningResult> earnings = new TotalEarningsModel().getTotalEarnings(date1, date2, validDates);
                jm.printReportWithComplexBeans2(earnings, jm.getMasterReportPathAdmin("total-earnings", validDates), hashMap, response.getOutputStream());
                break;
            default:
                break;
        }
    }

}
