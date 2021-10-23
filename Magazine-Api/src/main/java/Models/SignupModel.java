package Models;

import ApiMessages.SignupMessage;
import BackendUtilities.AES256cripter;
import DB.DAOs.Users.EditorInsert;
import DB.DAOs.Users.ReaderInsert;
import DB.Domain.Users.Editor;
import DB.Domain.Users.Reader;
import DB.Domain.Users.User;
import DB.GeneralPaths;
import BackendUtilities.Parser;
import javax.servlet.http.Part;

/**
 * This class validate and redirect user to respective page
 *
 * @author jefemayoneso
 */
public class SignupModel {

    /**
     * this method will create a new user, editor, admin or reader
     *
     * @param user
     * @param part
     * @param body
     * @return
     */
    public SignupMessage signUp(User user, Part part, String body) {
        // Utilities
        SignupMessage message = new SignupMessage();
        Parser parser = new Parser();
        // variables
        int operationResult = 0; // 0 = NO inserted
        user.setPassword(AES256cripter.encrypt(user.getPassword()));
        message.setMessage("NO_ERROR");
        // insert
        switch (user.getType()) {
            case "READER":
                // TODO validate and continue method
                operationResult = insertReader(parser, body, user, part);
                break;
            case "EDITOR":
                operationResult = insertEditor(parser, body, user, part);
                break;
            case "ADMIN":
                break;
            default:
                System.out.println("No recognized");
        }

        // SWITCH  operationReulst, do different things
        if (operationResult == -1) {
            message.setMessage(GeneralPaths.EMAIL_IN_USE.getMessage());
        } else if (operationResult == 0) {
            message.setMessage(GeneralPaths.ERROR_ON_INSERT.getMessage());
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
    private int insertEditor(Parser jsonParser, String body, User user, Part part) {
        Editor editor = (Editor) jsonParser.toObject(body, Editor.class); // create editor
        editor.setPassword(user.getPassword());
        int result = new EditorInsert().insert(editor, part);
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
    private int insertReader(Parser jsonParser, String body, User user, Part part) {
        Reader reader = (Reader) jsonParser.toObject(body, Reader.class);
        reader.setPassword(user.getPassword());
        int result = new ReaderInsert().insert(reader, part);
        return result;
    }
}
