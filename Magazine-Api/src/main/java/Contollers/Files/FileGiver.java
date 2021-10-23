package Contollers.Files;

import DB.DAOs.Magazine.MagazinePostSelect;
import DB.Domain.Magazine.MagazinePost;
import BackendUtilities.Parser;
import DB.GeneralPaths;
import Models.ImgModel;
import Models.PDFModel;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
        request.setCharacterEncoding("UTF-8");
        try {
            switch (request.getParameter("action")) {
                case "SHOW_PDF":
                    MagazinePost post = new MagazinePostSelect().select(parser.toInteger(request.getParameter("id")));
                    showFile(response, post.getPdfNamePath(), "application/pdf");
                case "DOWNLOAD_PDF":
                    PDFModel modelPdf = new PDFModel(request.getParameter("id"));
                    download(response, modelPdf.getInputStreamPdf(), modelPdf.getFileName());
                    break;
                case "GET_PROF_PIC":
                    ImgModel modelImg = new ImgModel(request.getParameter("user"), request.getParameter("type"));
                    showFile(response, modelImg.findPath(), "application/image");
                    break;
                case "GET_AD_PIC":
                    showFile(response, GeneralPaths.FILES_IMG_PATH_AD.getMessage() + request.getParameter("id"), "application/image");
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
    private void showFile(HttpServletResponse response, String path, String contentType) {
        try ( BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(path))) {
            response.setContentType(contentType);
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        } catch (Exception e) {
            System.out.println("Error trying to show a file at [FileGiver] " + e.getMessage());
        }
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
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        }
    }

//    private void showJasperAsHTML(HttpServletResponse response) {
//        String reportPath = "/home/jefemayoneso/Documents/Angular/projects/magazine-app/Magazine-Api/src/main/java/resources/Jasper/Simple_Blue.jasper";
//        try {
//            // get inputstream
//            response.setContentType("text/html");
//            InputStream inputStream = JRLoader.getFileInputStream(reportPath);
//            JasperPrint jPrint = JasperFillManager.fillReport(inputStream, null, DB.DBConnection.getConnection());
//            HtmlExporter htmlExporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
//            htmlExporter.setExporterInput(new SimpleExporterInput(jPrint));
//            htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//            htmlExporter.exportReport();
//        } catch (Exception e) {
//            System.out.println("Error JASPER " + e.getMessage());
//        }
//    }
}
