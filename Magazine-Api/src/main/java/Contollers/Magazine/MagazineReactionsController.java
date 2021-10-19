package Contollers.Magazine;

import APIMessages.SignupMessage;
import DB.Domain.Magazine.Relations.Comment;
import Models.CommentModel;
import Models.LikesModel;
import Parsers.Parser;
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
@WebServlet(name = "MagazineReactionsController", urlPatterns = {"/MagazineReactionsController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = "/home/jefemayoneso/"
)
public class MagazineReactionsController extends HttpServlet {

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
        Parser parser = new Parser();
        try {
            switch (request.getParameter("action")) {
                case "GET_MAG_COMMENT":
                    returnComments(request, response, parser);
                    break;
                case "GET_LIKES_COUNTER":
                    int counter = new LikesModel().getLikesCounter(request.getParameter("magazine"));
                    response.getWriter().append(String.valueOf(counter));
                    break;
                default:
                    System.out.println("UNKNOWN action at [MagazineInteractionsController]");
            }
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error trying to get reactions at [MagazineREacctionController]" + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Parser parser = new Parser();
//        CommentMessage message = (CommentMessage) parser.toObject(parser.getBody(request.getReader()), CommentMessage.class);
        try {
            switch (request.getParameter("action")) {
                case "NEW_COMMENT":
                    Comment comment = (Comment) parser.toObject(request.getParameter("comment"), Comment.class);
                    comment.setDate(parser.toLocalDate(comment.getDateString()));
                    String tmp = new CommentModel().publishComment(comment);
                    response.getWriter().append(parser.toJSON(tmp));
                    break;
                default:
                    System.out.println("Error while getting commnets");
            }
        } catch (Exception e) {
            response.getWriter().append(parser.toJSON(new SignupMessage("Error trying to get reactions at [MagazineREacctionController]" + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Return a list of magazines
     *
     * @param request
     * @param response
     * @param parser
     * @throws IOException
     */
    private void returnComments(HttpServletRequest request, HttpServletResponse response, Parser parser) throws IOException {
        ArrayList<Comment> comments = new CommentModel().getCommentsForMag(
                request.getParameter("magazine"),
                parser.toInteger(request.getParameter("limit")),
                parser.toInteger(request.getParameter("offset")));
        response.getWriter().append(parser.toJSON(comments, comments.getClass()));
    }

}
