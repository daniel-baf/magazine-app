package DB.Domain.Magazine;

import java.io.File;
import java.time.LocalDate;

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
    private String pdfBase64;
//    private File pdf;
    private LocalDate date;

    public MagazinePost() {
    }

    public MagazinePost(int id, String title, String magazine, LocalDate date, File pdf) {
        this.id = id;
        this.title = title;
        this.magazine = magazine;
        this.date = date;
//        this.pdf = pdf;
        // casts
        this.dateString = date.toString();
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

//    /**
//     * @return the pdf
//     */
//    public File getPdf() {
//        return pdf;
//    }
//    /**
//     * @param pdf the pdf to set
//     */
//    public void setPdf(File pdf) {
//        this.setPdf(pdf);
//    }
    @Override
    public String toString() {
        return "post id=" + this.getId() + " title=" + this.getTitle() + " magazine=" + this.getMagazine() + " date=" + this.getDateString();
    }

    /**
     * @return the pdfBase64
     */
    public String getPdfBase64() {
        return pdfBase64;
    }

    /**
     * @param pdfBase64 the pdfBase64 to set
     */
    public void setPdfBase64(String pdfBase64) {
        this.pdfBase64 = pdfBase64;
    }

}
