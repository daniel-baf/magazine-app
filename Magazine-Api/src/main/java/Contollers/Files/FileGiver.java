package Contollers.Files;

import DB.DAOs.Magazine.Post.MagazinePostSelect;
import DB.Domain.Magazine.MagazinePost;
import BackendUtilities.Parser;
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
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
// JASPER
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.engine.JasperFillManager;

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
        request.setCharacterEncoding("UTF-8");
        try {
            switch (request.getParameter("action")) {
                case "SHOW_PDF":
                    MagazinePost post = new MagazinePostSelect().select(parser.toInteger(request.getParameter("id")));
                    showPDF(response, post.getPdfNamePath());
                case "DOWNLOAD_PDF":
                    downloadPDF(response, request.getParameter("id"));
                    break;
//            case "SHOW_JASPER_TMP":
//                showJasperAsHTML(response);
//                // get Jasper
//                break;
            }
        } catch (Exception e) {
        }
    }

    /**
     * Return a PDF
     *
     * @param response
     * @param id
     */
    private void showPDF(HttpServletResponse response, String path) {
        try ( BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(path))) {
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

    /**
     * This method generate a PDF file for download
     *
     * @param response
     * @param id
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void downloadPDF(HttpServletResponse response, String id) throws FileNotFoundException, IOException {
        Parser parser = new Parser();
        MagazinePost post = new MagazinePostSelect().select(parser.toInteger(id));
        FileInputStream inputStream = new FileInputStream(post.getPdfNamePath());
        String filename = post.getId() + "_" + post.getTitle() + "_" + post.getMagazine() + ".pdf";
        download(response, inputStream, filename);
    }

    /**
     * This method generate the downloadable file
     *
     * @param response
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
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

    private void showJasperAsHTML(HttpServletResponse response) {
        String reportPath = "/home/jefemayoneso/Documents/Angular/projects/magazine-app/Magazine-Api/src/main/java/resources/Jasper/Simple_Blue.jasper";
        try {
            // get inputstream
            response.setContentType("text/html");
            InputStream inputStream = JRLoader.getFileInputStream(reportPath);
            JasperPrint jPrint = JasperFillManager.fillReport(inputStream, null, DB.DBConnection.getConnection());
            HtmlExporter htmlExporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
            htmlExporter.setExporterInput(new SimpleExporterInput(jPrint));
            htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
            htmlExporter.exportReport();
        } catch (Exception e) {
            System.out.println("Error JASPER " + e.getMessage());
        }
    }
}
