package ApiMessages;

import DB.Domain.Users.User;

/**
 *
 * @author jefemayoneso
 */
public class SignupMessage {

    private String message;
    private User user;

    public SignupMessage() {
    }

    public SignupMessage(String message, User user) {
        this.message = message;
        this.user = user;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
