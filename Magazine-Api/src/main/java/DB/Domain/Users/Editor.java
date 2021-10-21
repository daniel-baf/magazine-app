package DB.Domain.Users;

public class Editor extends User {

    private String description;
    private String imgPath;

    public Editor() {
    }

    public Editor(String email, String password) {
        super(email, password);
    }

    public Editor(String email, String password, String name) {
        super(email, password, name);
    }

    public Editor(String email, String password, String name, String description, String imgPath) {
        super(email, password, name);
        this.description = description;
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + " [description]=" + this.getDescription();
    }

    /**
     * @return the impgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
