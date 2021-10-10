package APIErrors;

import DB.Domain.Magazine.Magazine;

/**
 * This class is for transaction with magazines
 *
 * @author jefemayoneso
 */
public class MagazineMessage {

    private String message;
    private Magazine magazine;

    public MagazineMessage() {
    }

    public MagazineMessage(String message, Magazine magazine) {
        this.message = message;
        this.magazine = magazine;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

}
