package Models;

import APIErrors.StringArrayMessage;
import DB.Domain.Magazine.CategoryDAO;

/**
 *
 * @author jefemayoneso
 */
public class CategoriesModel {

    public StringArrayMessage executeModel(String action, String email) {
        StringArrayMessage stringArrMessage = new StringArrayMessage();
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
