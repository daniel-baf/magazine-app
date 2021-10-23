package BackendUtilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.Part;

/**
 *
 * @author jefemayoneso
 */
public class FileWriterCP {

    public boolean write(Part filePart, String filePath) {
        try ( InputStream inStream = filePart.getInputStream();  BufferedReader reader = new BufferedReader(new InputStreamReader(inStream))) {
            // save pdf
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
            }
//            String filePath = path + newFileName + getFileExtension(filePart.getSubmittedFileName()); // I deleted the newFIleName attribute
            filePart.write(filePath);
            return true;
        } catch (Exception e) {
            System.out.println("Error while writing file at [FileWriterCP] " + e.getMessage());
            return false;
        }
    }

    public String getFileExtension(String fileNameSubmited) {
        int lastIndexOf = fileNameSubmited.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return fileNameSubmited.substring(lastIndexOf);
    }
}
