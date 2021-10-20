package DB.Domain.Ad;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class Ad {

    private int id;
    private int shownCounter;
    private int type; // 1 = text, 2 = text and img 3 = video and text
    private Double advertiserPaid;
    private LocalDate expirationDate;
    private LocalDate startDate;
    private String expirationDateString;
    private String startDateString;
    private String advertiser;
    private String shownUrl;
    private String videoUrl;
    private String imgLocalPath;
    private String text;
    private ArrayList<String> tags;

    public Ad() {
    }

    public Ad(int id, int shownCounter, int type) {
        this.id = id;
        this.shownCounter = shownCounter;
        this.type = type;
    }

    public Ad(int id, int shownCounter, int type, Double advertiserPaid, LocalDate expirationDate, LocalDate startDate, String advertiser, String shownUrl, String text) {
        this.id = id;
        this.shownCounter = shownCounter;
        this.type = type;
        this.advertiserPaid = advertiserPaid;
        this.expirationDate = expirationDate;
        this.startDate = startDate;
        this.advertiser = advertiser;
        this.shownUrl = shownUrl;
        this.text = text;
        // paser
        this.startDateString = startDate.toString();
        this.expirationDateString = expirationDate.toString();
    }

    public Ad(int id, int shownCounter, int type, Double advertiserPaid, LocalDate expirationDate, LocalDate startDate, String advertiser, String shownUrl, String imgLocalPath, String text) {
        this.id = id;
        this.shownCounter = shownCounter;
        this.type = type;
        this.advertiserPaid = advertiserPaid;
        this.expirationDate = expirationDate;
        this.startDate = startDate;
        this.advertiser = advertiser;
        this.shownUrl = shownUrl;
        this.imgLocalPath = imgLocalPath;
        this.text = text;
        // paser
        this.startDateString = startDate.toString();
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
     * @return the shownCounter
     */
    public int getShownCounter() {
        return shownCounter;
    }

    /**
     * @param shownCounter the shownCounter to set
     */
    public void setShownCounter(int shownCounter) {
        this.shownCounter = shownCounter;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the advertiserPaid
     */
    public Double getAdvertiserPaid() {
        return advertiserPaid;
    }

    /**
     * @param advertiserPaid the advertiserPaid to set
     */
    public void setAdvertiserPaid(Double advertiserPaid) {
        this.advertiserPaid = advertiserPaid;
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
     * @return the startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the advertiser
     */
    public String getAdvertiser() {
        return advertiser;
    }

    /**
     * @param advertiser the advertiser to set
     */
    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    /**
     * @return the shownUrl
     */
    public String getShownUrl() {
        return shownUrl;
    }

    /**
     * @param shownUrl the shownUrl to set
     */
    public void setShownUrl(String shownUrl) {
        this.shownUrl = shownUrl;
    }

    /**
     * @return the videoUrl
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * @param videoUrl the videoUrl to set
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * @return the imgLocalPath
     */
    public String getImgLocalPath() {
        return imgLocalPath;
    }

    /**
     * @param imgLocalPath the imgLocalPath to set
     */
    public void setImgLocalPath(String imgLocalPath) {
        this.imgLocalPath = imgLocalPath;
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
     * @return the tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
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
     * @return the startDateString
     */
    public String getStartDateString() {
        return startDateString;
    }

    /**
     * @param startDateString the startDateString to set
     */
    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

}
