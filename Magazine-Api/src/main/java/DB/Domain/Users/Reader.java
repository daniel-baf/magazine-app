package DB.Domain.Users;

/**
 * Representation of DATABASE table
 *
 * @author jefemayoneso
 */
public class Reader extends User {

    private String imgPath;

    public Reader(String imgPath) {
        this.imgPath = imgPath;
    }

    public Reader(String email, String password, String name, String imgPath) {
        super(email, password, name);
        this.imgPath = imgPath;
    }

    /**
     * @return the imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath the imgPath to set
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

}
