package Contollers.Finance;

import ApiMessages.SignupMessage;
import DB.Domain.Financial.Ad;
import DB.Domain.Financial.Advertiser;
import Models.AdsModel;
import BackendUtilities.Parser;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "AdsController", urlPatterns = {"/AdsController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100 // 100MB
)
public class AdsController extends HttpServlet {

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
            switch (request.getParameter("sub-action")) {
                case "ADVERTISERS":
                    ArrayList<String> advertiser = new AdsModel().getAdvertiser(
                            parser.toInteger(request.getParameter("limit")),
                            parser.toInteger(request.getParameter("offset")),
                            request.getParameter("action"));
                    response.getWriter().append(parser.toJSON(advertiser, advertiser.getClass()));
                    break;
                case "get-ad":
                    response.getWriter().append(parser.toJSON(new AdsModel().getRandomAd(
                            parser.toInteger(
                                    request.getParameter("ad-type")),
                            request.getParameter("user")), Ad.class));
                    break;
            }

        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error trying to make a Magazine action at [MagazineController]" + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
            switch (request.getParameter("sub-action")) {
                case "new-advertiser": // return a message about the insert
                    response.getWriter().append(new AdsModel().registNewAdvertiser(
                            new Advertiser(
                                    request.getParameter("advertiser"))));
                    break;
                case "new-add":
                    Ad ad = (Ad) parser.toObject(request.getParameter("ad"), Ad.class);
                    ad.setExpirationDate(parser.toLocalDate(ad.getExpirationDateString()));
                    ad.setStartDate(parser.toLocalDate(ad.getStartDateString()));
                    response.getWriter().append(new AdsModel().createNewAdd(ad, request.getPart("file"), absolutePath));
                    break;
                default:
                    System.out.println("UNKNOWN action at [AddsController]");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().append("Error at [MagazineController] " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
