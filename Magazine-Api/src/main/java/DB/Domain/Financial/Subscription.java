package DB.Domain.Financial;

import Parsers.Parser;
import java.time.LocalDate;

/**
 *
 * @author jefemayoneso
 */
public class Subscription {

    private int id;
    private int months;
    private LocalDate expirationDate;
    private LocalDate acquisitionDate;
    private String expirationDateString;
    private String acquisitionDateString;
    private String magazine;
    private String reader;

    public Subscription() {
    }

    public Subscription(int id, int months, LocalDate expirationDate, LocalDate acquisitionDate, String magazine, String reader) {
        Parser parser = new Parser();
        this.id = id;
        this.months = months;
        this.expirationDate = expirationDate;
        this.acquisitionDate = acquisitionDate;
        this.magazine = magazine;
        this.reader = reader;
        // string to date
        this.acquisitionDateString = acquisitionDate.toString();
        this.expirationDateString = expirationDate.toString();
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
     * @return the months
     */
    public int getMonths() {
        return months;
    }

    /**
     * @param months the months to set
     */
    public void setMonths(int months) {
        this.months = months;
    }

    /**
     * @return the expirationDate
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return the acquisitionDate
     */
    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * @param acquisitionDate the acquisitionDate to set
     */
    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
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
     * @return the reader
     */
    public String getReader() {
        return reader;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(String reader) {
        this.reader = reader;
    }

    /**
     * @return the expirationDateString
     */
    public String getExpirationDateString() {
        return expirationDateString;
    }

    /**
     * @param expirationDateString the expirationDateString to set
     */
    public void setExpirationDateString(String expirationDateString) {
        this.expirationDateString = expirationDateString;
    }

    /**
     * @return the acquisitionDateString
     */
    public String getAcquisitionDateString() {
        return acquisitionDateString;
    }

    /**
     * @param acquisitionDateString the acquisitionDateString to set
     */
    public void setAcquisitionDateString(String acquisitionDateString) {
        this.acquisitionDateString = acquisitionDateString;
    }

}
