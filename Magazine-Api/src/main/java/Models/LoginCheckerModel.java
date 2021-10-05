package Models;

import DB.DAOs.Users.Admin.AdminSelectDAO;
import DB.Domain.Users.User;
import Parsers.Gson.JsonParser;
import Parsers.ReaderBR;
import java.io.BufferedReader;

/**
 * this method checks if a user is Admin, Reader or Editor, and validate
 * credentials
 *
 * @author jefemayoneso
 */
public class LoginCheckerModel {

    public LoginCheckerModel() {
    }

    /**
     * Verify if the credentials are correct and give access to system
     *
     * @param br the FRONTEND connection to get JSON
     * @return User as JSON
     */
    public User verifyUser(BufferedReader br) {
        // check for admins
        ReaderBR rbr = new ReaderBR();
        // variables
        User user = new JsonParser().getAdminFromJson(rbr.getBody(br));
        User userDB = searchUser(user.getEmail());
        // set types
        user.setType(userDB.getType());
        return user;
    }

    /**
     * Compare the entry password and the email, if match, user can access
     * system
     *
     * @param user1
     * @param user2
     * @return
     */
    private boolean match(User user1, User user2) {
        return user1.getEmail().equals(user2.getEmail()) && user2.getPassword().equals(user1.getPassword());
    }

    /**
     * The user logs in the same page, admins, readers and editor, this method,
     * search on admin, user and reader tables to find a match
     *
     * @param email
     * @return
     */
    private User searchUser(String email) {
        User user = new AdminSelectDAO().select(email); // SEARCH admin
        if (user != null) {
            user.setType("ADMIN");
            return user;
        }
        // search reader

        return null;
    }
}
