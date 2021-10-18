package DB.Domain.Magazine;

import java.time.LocalDate;
import javax.servlet.http.Part;

/**
 * This class is a representation of a post at database
 *
 * @author jefemayoneso
 */
public class MagazinePost {

    private int id;
    private String title;
    private String dateString;
    private String magazine;
    private String pdfNamePath;
    private LocalDate date;
    private Part pdfPart;

    public MagazinePost() {
    }

    public MagazinePost(int id, String title, String magazine, LocalDate date, String pdfNamePath) {
        this.id = id;
        this.title = title;
        this.magazine = magazine;
        this.date = date;
        this.pdfNamePath = pdfNamePath;
        // casts
        this.dateString = date.toString();
    }

    public MagazinePost(int id, String title, String dateString, String magazine, String pdfNamePath, LocalDate date, Part pdfPart) {
        this.id = id;
        this.title = title;
        this.dateString = dateString;
        this.magazine = magazine;
        this.pdfNamePath = pdfNamePath;
        this.date = date;
        this.pdfPart = pdfPart;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the pdf
     */
    public String getPdfNamePath() {
        return pdfNamePath;
    }

    /**
     * @param pdfNamePath
     */
    public void setPdfNamePath(String pdfNamePath) {
        this.pdfNamePath = pdfNamePath;
    }

    @Override
    public String toString() {
        return "post id=" + this.getId() + " title=" + this.getTitle() + " magazine=" + this.getMagazine() + " date=" + this.getDateString() + "path=" + this.getPdfNamePath();
    }

    /**
     * @return the pdfPart
     */
    public Part getPdfPart() {
        return pdfPart;
    }

    /**
     * @param pdfPart the pdfPart to set
     */
    public void setPdfPart(Part pdfPart) {
        this.pdfPart = pdfPart;
    }

}
