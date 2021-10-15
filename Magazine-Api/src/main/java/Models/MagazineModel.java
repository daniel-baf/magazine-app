package Models;

import APIMessages.MagazineMessage;
import DB.DAOs.Magazine.MagazineInsert;
import DB.DAOs.Magazine.MagazineSelect;
import DB.DAOs.Magazine.MagazineUpdate;
import DB.Domain.Magazine.Magazine;
import ENUMS.DAOResults;
import Parsers.Parser;
import java.io.BufferedReader;
import java.util.ArrayList;

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
            case "UPDATE":
                message.getMagazine().setApproved(true);
                result = new MagazineUpdate().update(message.getMagazine());
            default:
        }
        // set response message
        String messString = result == DAOResults.ERROR_ON_INSERT.getCode() ? "ERROR_INSERT" : "NO_ERROR";
        message.setMessage(messString);
        return message;
    }

    /**
     * return a list of magazines
     *
     * @param action
     * @param cuantity
     * @return
     */
    public ArrayList<Magazine> selectMagazines(String action, String cuantity) {
        Parser parser = new Parser();
        ArrayList<Magazine> mags = new ArrayList<>();
        switch (action) {
            case "ALL":
                mags = new MagazineSelect().select(parser.toInteger(cuantity), 0); // 0 = ALL
                break;
            case "NO_PUBLISHED":
                mags = new MagazineSelect().select(parser.toInteger(cuantity), 2); // 2 = NO PUBLISHED
                break;
            case "PUBLISHED":
                mags = new MagazineSelect().select(parser.toInteger(cuantity), 1); // 1 = PUBLISHED 
                break;
            case "ONE":
                break;
            default:
        }

        return mags;
    }
}
