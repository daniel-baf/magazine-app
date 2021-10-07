package Models;

import APIErrors.SignupMessage;
import DB.DAOs.Users.UserCommonDAO;
import DB.Domain.Users.User;
import ENUMS.DAOResults;
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
    public SignupMessage verifyUser(BufferedReader br) {
        // check for admins
        ReaderBR rbr = new ReaderBR();
        // variables
        SignupMessage message = new SignupMessage();
        User user = (User) new JsonParser().getUserFromObject(rbr.getBody(br), User.class);
        User userDB = searchUser(user.getEmail());
        // set types
        String status = userDB != null && match(user, userDB) ? DAOResults.NO_ERROR.getMessage() : DAOResults.UNAUTHORIZED.getMessage();
        message.setMessage(status);
        message.setUser(userDB);
        return message;
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
        return new UserCommonDAO().emailRegisted(email);
    }
}
