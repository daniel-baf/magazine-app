package Models;

import APIErrors.MagazineMessage;
import DB.DAOs.Magazine.MagazineInsert;
import ENUMS.DAOResults;
import Parsers.Parser;
import java.io.BufferedReader;

/**
 * This class execute the Magazine creation actions
 *
 * @author jefemayoneso
 */
public class MagazineModel {

    /**
     * Execute the possible actions from Magazines from FRONTEND
     *
     * @param buffer
     * @return
     */
    public MagazineMessage executeModel(BufferedReader buffer) {
        // create vars
        Parser parser = new Parser();
        int result = 0;
        // create the object response to frontend
        MagazineMessage message = (MagazineMessage) parser.toObject(parser.getBody(buffer), MagazineMessage.class);
        // set the String Date as LocalDate at message response
        message.getMagazine().setDate(parser.toLocalDate(message.getMagazine().getDateString()));
        // switch action=? at URL
        switch (message.getMessage()) {
            case "QUEUE":
                result = new MagazineInsert().insert(message.getMagazine());
                break;
            default:
        }
        // set response message
        String messString = result == DAOResults.ERROR_ON_INSERT.getCode() ? "ERROR_INSERT" : "NO_ERROR";
        message.setMessage(messString);
        return message;
    }
}
