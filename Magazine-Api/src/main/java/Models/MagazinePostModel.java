package Models;

import APIMessages.MagazinePostMessage;
import Parsers.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * Redirect to multiple actions for Magazine POSTs
 *
 * @author jefemayoneso
 */
public class MagazinePostModel {

    public MagazinePostMessage updatePost(HttpServletRequest request) throws IOException, ServletException {
        Parser parser = new Parser();

        Part filePart = request.getPart("datafile");
        System.out.println(filePart);
        String tmp = request.getParameter("mag-post");
        System.out.println(tmp);
        MagazinePostMessage message = (MagazinePostMessage) parser.toObject(tmp, MagazinePostMessage.class);
        System.out.println(message.toString());
//        String fileName = filePart.getHeader("Content-type");
//        InputStream fileStream = filePart.getInputStream();
//        System.out.println(fileName);
//        System.out.println(filePart.getHeader("Content-disposition"));
//
//        try ( BufferedReader in = new BufferedReader(new InputStreamReader(fileStream))) {
//            String line = in.readLine();
//            while (line != null) {
//                System.out.println(line);
//                line = in.readLine();
//            }
//            String filePath = "/home/jefemayoneso" + "/" + "archivo";
//            filePart.write(filePath);
//
//        } catch (Exception ex) {
//            // manejo de error
//        }

        System.out.println(request.getParameter("mag-post"));

//        MagazinePostMessage messageResponse = (MagazinePostMessage) parser.toObject(request.getParameter("object"), MagazinePostMessage.class);
//        System.out.println(messageResponse.toString());
//        System.out.println("casted");
        return null;
    }

}
