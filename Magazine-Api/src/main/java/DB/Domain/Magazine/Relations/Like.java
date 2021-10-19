package DB.Domain.Magazine.Relations;

import java.time.LocalDate;

/**
 *
 * @author jefemayoneso
 */
public class Like {

    private LocalDate date;
    private String dateString;
    private String magazine;
    private String user;

    public Like() {
    }

    public Like(LocalDate date, String dateString, String magazine, String user) {
        this.date = date;
        this.dateString = dateString;
        this.magazine = magazine;
        this.user = user;
        // cast
        this.dateString = date.toString();
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the dateString
     */
    public String getDateString() {
        return dateString;
    }

    /**
     * @param dateString the dateString to set
     */
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    /**
     * @return the magazine
     */
    public String getMagazine() {
        return magazine;
    }

    /**
     * @param magazine the magazine to set
     */
    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

}
