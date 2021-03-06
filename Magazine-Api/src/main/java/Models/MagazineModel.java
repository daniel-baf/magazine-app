package Models;

import ApiMessages.MagazineMessage;
import DB.DAOs.Magazine.MagazineInsert;
import DB.DAOs.Magazine.MagazineSelect;
import DB.DAOs.Magazine.MagazineUpdate;
import DB.Domain.Magazine.Magazine;
import DB.GeneralPaths;
import BackendUtilities.Parser;
import java.io.BufferedReader;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

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
                result = new MagazineUpdate().update(message.getMagazine()); // USED FROM FRONTEND
                break;
            case "UPDATE_MAG_VALUES":
                message.getMagazine().setApproved(!message.getMagazine().isUnlisted());
                result = new MagazineUpdate().updateWitTags(message.getMagazine());
                break;
            default:
        }
        // set response message
        String messString = result == GeneralPaths.ERROR_ON_INSERT.getCode() ? "ERROR_INSERT" : "NO_ERROR";
        message.setMessage(messString);
        return message;
    }

    /**
     * return a list of magazines
     *
     * @param request
     * @return
     */
    public ArrayList<Magazine> selectMagazines(HttpServletRequest request) {
        Parser parser = new Parser();
        ArrayList<Magazine> mags = new ArrayList<>();
        MagazineSelect magSelect = new MagazineSelect();
        switch (request.getParameter("action")) {
            case "ALL":
                mags = magSelect.select(parser.toInteger(request.getParameter("cuantity")), 0); // 0 = ALL
                break;
            case "NO_PUBLISHED":
                mags = magSelect.select(parser.toInteger(request.getParameter("cuantity")), 2); // 2 = NO PUBLISHED
                break;
            case "PUBLISHED":
                mags = magSelect.select(parser.toInteger(request.getParameter("cuantity")), 1); // 1 = PUBLISHED 
                break;
            case "ONE":
                mags = new ArrayList<>();
                mags.add(magSelect.select(request.getParameter("mag-name")));
                System.out.println(mags.get(0).isUnlisted());
                break;
            case "USER_INTEREST":
                mags = magSelect.select(
                        parser.toInteger(request.getParameter("limit")),
                        parser.toInteger(request.getParameter("offset")),
                        request.getParameter("reader"));
                break;
            case "EDITOR_OWNED":
                mags = magSelect.select(request.getParameter("editor"),
                        parser.toInteger(request.getParameter("limit")),
                        parser.toInteger(request.getParameter("offset")));
            default:
        }

        return mags;
    }
}
