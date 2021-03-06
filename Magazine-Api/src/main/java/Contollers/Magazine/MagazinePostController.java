package Contollers.Magazine;

import ApiMessages.MagazinePostMessage;
import DB.Domain.Magazine.MagazinePost;
import Models.MagazinePostModel;
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
 * A controller for magazine posts actions
 *
 * @author jefemayoneso
 */
@WebServlet(name = "MagazinePostController", urlPatterns = {"/MagazinePostController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100 // 100MB
)
public class MagazinePostController extends HttpServlet {

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
            MagazinePostModel model = new MagazinePostModel();
            switch (request.getParameter("action")) {
                case "UPDATE_NEW_POST":
                    MagazinePost post = (MagazinePost) parser.toObject(request.getParameter("mag-post"), MagazinePost.class);
                    post.setPdfPart(request.getPart("filepart"));
                    post.setDate(parser.toLocalDate(post.getDateString()));
                    response.getWriter().append(model.pulishAPost(post, absolutePath));
                    break;
                default:
                    System.out.println("UNKNOWN action at [MagazinePostController]");
            }
        } catch (Exception e) {
            System.out.println("Error while making an action at [MagazinePostController] " + e.getMessage());
            response.getWriter().append(parser.toJSON(new MagazinePostMessage("Error trying to make a Magazine action at [MagazineController]" + e.getMessage(), null), MagazinePostMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Return list of Posts
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
            ArrayList<MagazinePost> posts = new MagazinePostModel().getPosts(request);
            response.getWriter().append(parser.toJSON(posts, posts.getClass()));
        } catch (Exception e) {
            System.out.println("Error while getting Posts at [MagazinePostController] " + e.getMessage());
            response.getWriter().append(parser.toJSON(new MagazinePostMessage("Error trying to make a Magazine action at [MagazineController]" + e.getMessage(), null), MagazinePostMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
