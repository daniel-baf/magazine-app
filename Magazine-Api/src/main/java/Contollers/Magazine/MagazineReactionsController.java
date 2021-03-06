package Contollers.Magazine;

import ApiMessages.SignupMessage;
import DB.Domain.Magazine.Comment;
import DB.Domain.Magazine.Like;
import DB.Domain.Magazine.TagsDAO;
import Models.CommentModel;
import Models.LikesModel;
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
@WebServlet(name = "MagazineReactionsController", urlPatterns = {"/MagazineReactionsController"})
@MultipartConfig()
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
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
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
                case "GET_TAGS":
                    ArrayList<String> tags = new TagsDAO().select();
                    response.getWriter().append(parser.toJSON(tags, tags.getClass()));
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
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Parser parser = new Parser();
        try {
            switch (request.getParameter("action")) {
                case "NEW_COMMENT":
                    Comment comment = (Comment) parser.toObject(request.getParameter("comment"), Comment.class);
                    comment.setDate(parser.toLocalDate(comment.getDateString()));
                    response.getWriter().append(new CommentModel().publishComment(comment));
                    break;
                case "LEAVE_LIKE":
                    Like like = (Like) parser.toObject(request.getParameter("like"), Like.class);
                    like.setDate(parser.toLocalDate(like.getDateString()));
                    response.getWriter().append(new LikesModel().leaveLike(like));
                    break;
                default:
                    System.out.println("UNKNOWN action at [MagazineReactionsController]");
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
