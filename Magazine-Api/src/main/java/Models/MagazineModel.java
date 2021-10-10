package Models;

import APIErrors.MagazineMessage;
import DB.DAOs.Magazine.MagazineInsert;
import ENUMS.DAOResults;
import Parsers.Parser;
import java.io.BufferedReader;

/**
 *
 * @author jefemayoneso
 */
public class MagazineModel {

    public MagazineMessage executeModel(BufferedReader buffer) {
        Parser parser = new Parser();
        MagazineMessage message = (MagazineMessage) parser.getObjectFromJson(parser.getBody(buffer), MagazineMessage.class);
        message.getMagazine().setDate(parser.toLocalDate(message.getMagazine().getDateString()));
        int result = 0;

        switch (message.getMessage()) {
            case "QUEUE":
                result = new MagazineInsert().insert(message.getMagazine());
                break;
            default:
        }
        String messString = result == DAOResults.ERROR_ON_INSERT.getCode() ? "ERROR_INSERT" : "NO_ERROR";
        message.setMessage(messString);
        return message;
    }
}
