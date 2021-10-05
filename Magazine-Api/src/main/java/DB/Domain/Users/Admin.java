package DB.Domain.Users;

/**
 * This is a representation of Admin at database
 *
 * @author jefemayoneso
 */
public class Admin extends User {

    // CONSTRUCTORS
    public Admin() {
        super();
    }
    
    public Admin(String _email, String _password) {
        super(_email, _password);
    }
    
    public Admin(String _email, String _password, String _name) {
        super(_email, _password, _name);
    }
}