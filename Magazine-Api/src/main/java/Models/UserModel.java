package Models;

import APIErrors.SignupMessage;
import DB.DAOs.Users.Admin.AdminUpdate;
import DB.DAOs.Users.Editor.EditorUpdate;
import DB.DAOs.Users.Reader.ReaderUpdate;
import DB.Domain.Users.Admin;
import DB.Domain.Users.Editor;
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

    /**
     * This method update the info of any <user>, this can be a <Reader>,
     * <Admin> or <Editor>
     *
     * @param buffer
     * @return
     */
    public SignupMessage updateUser(BufferedReader buffer) {
        Parser parser = new Parser();
        int result = 0;
        String body = parser.getBody(buffer);
        SignupMessage message = new SignupMessage();
        User user = (User) parser.toObject(body, User.class);
        switch (user.getType()) {
            case "READER":
                Reader reader = (Reader) parser.toObject(body, Reader.class);
                result = new ReaderUpdate().update(reader);
                break;
            case "EDITOR":
                Editor editor = (Editor) parser.toObject(body, Editor.class);
                result = new EditorUpdate().update(editor);
                break;
            case "ADMIN":
                Admin admin = (Admin) parser.toObject(body, Admin.class);
                result = new AdminUpdate().update(admin);
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
