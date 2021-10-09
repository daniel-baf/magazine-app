package Models;

import APIErrors.SignupMessage;
import DB.DAOs.Users.Reader.ReaderUpdate;
import DB.Domain.Users.Reader;
import DB.Domain.Users.User;
import ENUMS.DAOResults;
import Parsers.Parser;
import java.io.BufferedReader;

/**
 * This method create some User actions
 *
 * @author jefemayoneso
 */
public class UserModel {

    public SignupMessage updateUser(BufferedReader buffer) {
        Parser parser = new Parser();
        int result = 0;
        String body = parser.getBody(buffer);
        SignupMessage message = new SignupMessage();
        User user = (User) parser.getObjectFromJson(body, User.class);
        switch (user.getType()) {
            case "READER":
                Reader reader = (Reader) parser.getObjectFromJson(body, Reader.class);
                result = new ReaderUpdate().update(reader);
                break;
            case "EDITOR":
                break;
            case "ADMIN":
                break;
            default:
        }
        message.setUser(user);
        if (result == DAOResults.NO_ERROR.getCode()) {
            message.setMessage(DAOResults.NO_ERROR.getMessage());
        } else {
            message.setMessage(DAOResults.ERROR_ON_INSERT.getMessage());
        }
        return message;
    }
}
