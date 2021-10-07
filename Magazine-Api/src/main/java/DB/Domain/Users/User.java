package DB.Domain.Users;

/**
 * This is an abstract class to represent Admin, User and Reader at FRONTEND
 *
 * @author jefemayoneso
 */
public class User {

    private String email;
    private String password;
    private String name;
    private String type; // NOT ON DB, used to share data from frontend easier

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String password, String name, String type) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
    }

    // GETTERS AND SETTERS
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[name]=" + this.name + " [password]=" + this.password + " [email]=" + this.email + " [type]=" + this.type;
    }
}
