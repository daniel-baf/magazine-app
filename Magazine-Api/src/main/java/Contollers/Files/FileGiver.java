package Contollers.Files;

import DB.DAOs.Magazine.Post.MagazinePostSelect;
import DB.Domain.Magazine.MagazinePost;
import Parsers.Parser;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "FileGiver", urlPatterns = {"/FileGiver"})
public class FileGiver extends HttpServlet {

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
        switch (request.getParameter("action")) {
            case "SHOW_PDF":
                showPDF(response, parser.toInteger(request.getParameter("id")));
            case "DOWNLOAD_PDF":
                downloadPDF(response, request.getParameter("id"));
                break;
            case "SHOW_JASPER_TMP":
                showJasper();
                // get Jasper
                break;
        }
    }

    /**
     * Return a PDF
     *
     * @param response
     * @param id
     */
    private void showPDF(HttpServletResponse response, int id) {
        MagazinePost post = new MagazinePostSelect().select(id);
        try ( BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(post.getPdfNamePath()))) {
            response.setContentType("application/pdf");
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void downloadPDF(HttpServletResponse response, String id) throws FileNotFoundException, IOException {
        Parser parser = new Parser();
        MagazinePost post = new MagazinePostSelect().select(parser.toInteger(id));
        FileInputStream inputStream = new FileInputStream(post.getPdfNamePath());
        String filename = post.getId() + "_" + post.getTitle() + "_" + post.getMagazine() + ".pdf";
        download(response, inputStream, filename);
    }

    private void download(HttpServletResponse response, InputStream inputStream, String fileName) throws IOException {
        try ( BufferedInputStream fileStream = new BufferedInputStream(inputStream)) {
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        }
    }
    
    private void showJasper() {
        try (InputStream template = JasperReportsApplication.class){
            
        } catch (Exception e) {
        }
    }
}
