package Models;

import APIErrors.MagazineMessage;
import Parsers.Parser;
import java.io.BufferedReader;

/**
 *
 * @author jefemayoneso
 */
public class MagazineModel {

    public MagazineMessage executeModel(BufferedReader buffer) {
        Parser parser = new Parser();
        MagazineMessage message = null;

        try {
            message = (MagazineMessage) parser.getObjectFromJson(parser.getBody(buffer), MagazineMessage.class);
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("ERror" + e.getMessage());
        }
        return message;
    }
}
