package DB.Domain.Users;

/**
 * Representation of DATABASE table
 *
 * @author jefemayoneso
 */
public class Reader extends User {

    public Reader() {
    }

    public Reader(String email, String password) {
        super(email, password);
    }

    public Reader(String email, String password, String name) {
        super(email, password, name);
    }

}
