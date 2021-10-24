package Models;

import ApiMessages.StringArrayMessage;
import DB.DAOs.Magazine.CategoryDAO;

/**
 *
 * @author jefemayoneso
 */
public class CategoriesModel {

    /**
     * Execute the actions from Categories from FRONTEND
     *
     * @param action
     * @param email
     * @return
     */
    public StringArrayMessage executeModel(String action, String email) {
        // Vars for response
        StringArrayMessage stringArrMessage = new StringArrayMessage();
        // Execute actions
        switch (action) {
            case "BY_USER":
                stringArrMessage.setStrings(new CategoryDAO().select(true, email));
                stringArrMessage.setMessage("FOUND");
                break;
            case "ALL":
                stringArrMessage.setStrings(new CategoryDAO().select(false, null));
                stringArrMessage.setMessage("FOUND");
                break;
            default:
                stringArrMessage.setMessage("NO_ACTION");
                break;
        }
        return stringArrMessage;
    }

}
