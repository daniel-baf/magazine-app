package DB.Domain.Users;

/**
 * This is an abstract class to represent Admin, User and Reader at FRONTEND
 *
 * @author jefemayoneso
 */
public class User {

    private String _email;
    private String _password;
    private String _name;
    private String _type;

    public User() {
    }

    public User(String _email, String _password) {
        this._email = _email;
        this._password = _password;
    }

    public User(String _email, String _password, String _name) {
        this._email = _email;
        this._password = _password;
        this._name = _name;
    }

    // GETTERS AND SETTERS
    /**
     * @return the _email
     */
    public String getEmail() {
        return _email;
    }

    /**
     * @param _email the _email to set
     */
    public void setEmail(String _email) {
        this._email = _email;
    }

    /**
     * @return the _password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * @param _password the _password to set
     */
    public void setPassword(String _password) {
        this._password = _password;
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * @return the _type
     */
    public String getType() {
        return _type;
    }

    /**
     * @param _type the _type to set
     */
    public void setType(String _type) {
        this._type = _type;
    }

}
