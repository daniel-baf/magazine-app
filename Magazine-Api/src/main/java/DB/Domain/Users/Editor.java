package DB.Domain.Users;

public class Editor extends User {

    private String description;

    public Editor() {
    }

    public Editor(String email, String password) {
        super(email, password);
    }

    public Editor(String email, String password, String name) {
        super(email, password, name);
    }

    public Editor(String email, String password, String name, String description) {
        super(email, password, name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + " [description]=" + this.description;
    }
}
