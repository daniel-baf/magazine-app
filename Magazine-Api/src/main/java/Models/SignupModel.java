package Models;

import APIErrors.SignupMessage;
import DB.DAOs.Users.Editor.EditorInsert;
import DB.DAOs.Users.Reader.ReaderInsert;
import DB.Domain.Users.Editor;
import DB.Domain.Users.Reader;
import DB.Domain.Users.User;
import ENUMS.DAOResults;
import Parsers.Gson.JsonParser;
import Parsers.ReaderBR;
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
        ReaderBR readerBR = new ReaderBR();
        JsonParser jsonParser = new JsonParser();
        String body = readerBR.getBody(buffReader);
        // variables
        int operationResult = 0; // 0 = NO inserted
        User user = (User) jsonParser.getUserFromObject(body, User.class);
        message.setMessage("NO_ERROR");
        // insert
        switch (user.getType()) {
            case "READER":
                // TODO validate and continue method
                operationResult = insertReader(jsonParser, body, user);
                break;
            case "EDITOR":
                operationResult = insertEditor(jsonParser, body, user);
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
     * Configure a User to be send to FRONTEND
     *
     * @param email
     * @param password
     * @param name
     * @param type
     * @return
     */
    private User configureUser(String email, String password, String name, String type) {
        return new User(email, password, name, type);
    }

    /**
     * Insert an Editor on DATABASE
     *
     * @param jsonParser
     * @param body
     * @param user
     * @return
     */
    private int insertEditor(JsonParser jsonParser, String body, User user) {
        Editor editor = (Editor) jsonParser.getUserFromObject(body, Editor.class); // create editor
        int result = new EditorInsert().insert(editor);
        if (result > 0) {
            user = configureUser(editor.getEmail(), editor.getPassword(), editor.getName(), "EDITOR");
        }
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
    private int insertReader(JsonParser jsonParser, String body, User user) {
        Reader reader = (Reader) jsonParser.getUserFromObject(body, Reader.class);
        int result = new ReaderInsert().insert(reader);
        if (result > 0) {
            user = configureUser(reader.getEmail(), reader.getPassword(), reader.getName(), "READER");
        }
        return result;
    }
}
