package TestPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "FIleUPloadTest", urlPatterns = {"/FIleUPloadTest"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = "/home/jefemayoneso/"
)
public class FIleUPloadTest extends HttpServlet {

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
        try {
            System.out.println("intentado");
            Part filePart = request.getPart("datafile");
            String fileName = filePart.getHeader("Content-type");
            InputStream fileStream = filePart.getInputStream();
            System.out.println(fileName);
            System.out.println(filePart.getHeader("Content-disposition"));

            try ( BufferedReader in = new BufferedReader(new InputStreamReader(fileStream))) {
                String line = in.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = in.readLine();
                }
                String filePath = "/home/jefemayoneso/";
                filePart.write(filePath);

                System.out.println("done");

            } catch (Exception ex) {
                // manejo de error
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
