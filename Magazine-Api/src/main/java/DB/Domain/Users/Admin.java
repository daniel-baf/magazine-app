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

    public Admin(String email, String password) {
        super(email, password);
    }

    public Admin(String email, String password, String name) {
        super(email, password, name);
    }
}
