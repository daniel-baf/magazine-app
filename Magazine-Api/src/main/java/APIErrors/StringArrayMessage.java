package APIErrors;

import java.util.ArrayList;

/**
 * This class is used to send a StringArray and a status message to FRONTEND
 *
 * @author jefemayoneso
 */
public class StringArrayMessage {

    private String message;
    private ArrayList<String> array;

    public StringArrayMessage() {
    }

    public StringArrayMessage(String message, ArrayList<String> array) {
        this.message = message;
        this.array = array;
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
     * @return the strings
     */
    public ArrayList<String> getArray() {
        return array;
    }

    /**
     * @param array the strings to set
     */
    public void setStrings(ArrayList<String> array) {
        this.array = array;
    }

}
