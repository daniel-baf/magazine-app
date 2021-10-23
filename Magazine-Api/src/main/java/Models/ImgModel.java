package Models;

import DB.DAOs.Users.EditorSelect;
import DB.DAOs.Users.ReaderSelect;

/**
 *
 * @author jefemayoneso
 */
public class ImgModel {

    String path;
    String type;
    String user;
    
    public ImgModel(String user, String type) {
        this.user = user;
        this.type = type;
    }

    public String findPath() {
        switch (this.type) {
            case "EDITOR":
                return new EditorSelect().select(this.user).getImgPath();
            case "READER":
                return new ReaderSelect().select(this.user).getImgPath();
            default:
                System.out.println("no conozco" + this.type);
        }
        return null;
    }
    
}
