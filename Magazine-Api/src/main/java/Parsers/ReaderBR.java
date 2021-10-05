package Parsers;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderBR {

    public ReaderBR() {
    }

    /**
     * this method reads the BR from the FRONTEND and returns the string readed
     *
     * @param br
     * @return
     */
    public String getBody(BufferedReader br) {
        try {
            String body = "";
            String line = br.readLine();
            while (line != null) {
                body += line;
                line = br.readLine();
            }
            System.out.println(body);
            return body;
        } catch (IOException e) {
            System.out.println("Error parsing request string from buffered reader at [Parsers].[ReaderBR]\n" + e.getMessage());
            return null;
        }
    }
}
