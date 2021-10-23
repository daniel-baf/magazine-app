package Models;

import ApiMessages.SignupMessage;
import BackendUtilities.AES256cripter;
import DB.DAOs.Users.AdminUpdate;
import DB.DAOs.Users.EditorUpdate;
import DB.Domain.Users.Admin;
import DB.Domain.Users.Editor;
import DB.Domain.Users.Reader;
import DB.Domain.Users.User;
import DB.GeneralPaths;
import BackendUtilities.Parser;
import DB.DAOs.Users.ReaderUpdate;
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
        String body = parser.getBody(buffer);
        SignupMessage message = new SignupMessage();
        User user = (User) parser.toObject(body, User.class);
        AES256cripter encrypter = new AES256cripter();
        int result = 0;

        switch (user.getType()) {
            case "READER":
                Reader reader = (Reader) parser.toObject(body, Reader.class);
                reader.setPassword(encrypter.encrypt(reader.getPassword()));
                result = new ReaderUpdate().update(reader);
                System.out.println(reader.getName() + " " + reader.getPassword());
                break;
            case "EDITOR":
                Editor editor = (Editor) parser.toObject(body, Editor.class);
                editor.setPassword(encrypter.encrypt(editor.getPassword()));
                result = new EditorUpdate().update(editor);
                break;
            case "ADMIN":
                Admin admin = (Admin) parser.toObject(body, Admin.class);
                admin.setPassword(encrypter.encrypt(admin.getPassword()));
                result = new AdminUpdate().update(admin);
                break;
            default:
        }
        message.setUser(user);
        if (result == GeneralPaths.NO_ERROR.getCode()) {
            message.setMessage(GeneralPaths.NO_ERROR.getMessage());
        } else {
            message.setMessage(GeneralPaths.ERROR_ON_INSERT.getMessage());
        }
        return message;
    }
}
