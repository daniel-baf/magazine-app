package Models;

import APIMessages.SignupMessage;
import BackendUtilities.AES256cripter;
import DB.DAOs.Users.Editor.EditorInsert;
import DB.DAOs.Users.Reader.ReaderInsert;
import DB.Domain.Users.Editor;
import DB.Domain.Users.Reader;
import DB.Domain.Users.User;
import ENUMS.DAOResults;
import BackendUtilities.Parser;
import java.io.BufferedReader;

/**
 * This class validate and redirect user to respective page
 *
 * @author jefemayoneso
 */
public class SignupModel {

    /**
     * this method will create a new user, editor, admin or reader
     *
     * @param buffReader
     * @return
     */
    public SignupMessage signUp(BufferedReader buffReader) {
        // Utilities
        SignupMessage message = new SignupMessage();
        Parser parser = new Parser();
        String body = parser.getBody(buffReader);
        // variables
        int operationResult = 0; // 0 = NO inserted
        User user = (User) parser.toObject(body, User.class);
        user.setPassword(AES256cripter.encrypt(user.getPassword()));
        message.setMessage("NO_ERROR");
        // insert
        switch (user.getType()) {
            case "READER":
                // TODO validate and continue method
                operationResult = insertReader(parser, body, user);
                break;
            case "EDITOR":
                operationResult = insertEditor(parser, body, user);
                break;
            case "ADMIN":
                break;
            default:
                System.out.println("No recognized");
        }

        // SWITCH  operationReulst, do different things
        if (operationResult == -1) {
            message.setMessage(DAOResults.EMAIL_IN_USE.getMessage());
        } else if (operationResult == 0) {
            message.setMessage(DAOResults.ERROR_ON_INSERT.getMessage());
        }
        message.setUser(user);
        return message;
    }

    /**
     * Insert an Editor on DATABASE
     *
     * @param jsonParser
     * @param body
     * @param user
     * @return
     */
    private int insertEditor(Parser jsonParser, String body, User user) {
        Editor editor = (Editor) jsonParser.toObject(body, Editor.class); // create editor
        editor.setPassword(user.getPassword());
        int result = new EditorInsert().insert(editor);
        return result;
    }

    /**
     * Insert a user on DB
     *
     * @param jsonParser
     * @param body
     * @param user
     * @return
     */
    private int insertReader(Parser jsonParser, String body, User user) {
        Reader reader = (Reader) jsonParser.toObject(body, Reader.class);
        reader.setPassword(user.getPassword());
        int result = new ReaderInsert().insert(reader);
        return result;
    }
}
