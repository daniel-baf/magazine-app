package Contollers.Logs;

import APIMessages.MagazinePostMessage;
import Models.MagazinePostModel;
import Parsers.Parser;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * A controller for magazine posts actions
 *
 * @author jefemayoneso
 */
@WebServlet(name = "MagazinePostController", urlPatterns = {"/MagazinePostController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = "/home/jefemayoneso/"
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
        Parser parser = new Parser();
        try {
            MagazinePostMessage mpms = new MagazinePostModel().updatePost(request);
//            escribirArchivo(mpms.getPost().getPdfBase64());

            response.getWriter().append(parser.toJSON(mpms, MagazinePostMessage.class));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            response.getWriter().append(parser.toJSON(new MagazinePostMessage("Error trying to make a Magazine action at [MagazineController]" + e.getMessage(), null), MagazinePostMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
