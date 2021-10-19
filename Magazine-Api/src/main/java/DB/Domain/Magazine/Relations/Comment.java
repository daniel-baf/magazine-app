package DB.Domain.Magazine.Relations;

import java.time.LocalDate;

/**
 *
 * @author jefemayoneso
 */
public class Comment {

    private int id;
    private LocalDate date;
    private String text;
    private String user;
    private String magazine;
    private String dateString;

    public Comment() {
    }

    public Comment(LocalDate date, String text, String user, String magazine) {
        this.date = date;
        this.text = text;
        this.user = user;
        this.magazine = magazine;
    }

    public Comment(int id, LocalDate date, String text, String user, String magazine) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.user = user;
        this.magazine = magazine;
        // for FE
        this.dateString = this.date.toString();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
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

}
