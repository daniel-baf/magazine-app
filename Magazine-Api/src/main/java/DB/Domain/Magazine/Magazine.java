package DB.Domain.Magazine;

import java.time.LocalDate;

/**
 *
 * @author jefemayoneso
 */
public class Magazine {

    private String name;
    private String editor;
    private String description;
    private String category;
    private String dateString;
    private Double mensuality;
    private Double companyFee;
    private Double costPerDay;
    private LocalDate date;
    private boolean allowLikes;
    private boolean allowComment;
    private boolean approved;

    public Magazine() {
    }

    public Magazine(String name, Double mensuality, Double companyFee, Double costPerDay, LocalDate date, String description, boolean allowLikes, boolean allowComment, String editor, String catgory) {
        this.name = name;
        this.mensuality = mensuality;
        this.companyFee = companyFee;
        this.costPerDay = costPerDay;
        this.date = date;
        this.description = description;
        this.allowLikes = allowLikes;
        this.allowComment = allowComment;
        this.editor = editor;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mensuality
     */
    public Double getMensuality() {
        return mensuality;
    }

    /**
     * @param mensuality the mensuality to set
     */
    public void setMensuality(Double mensuality) {
        this.mensuality = mensuality;
    }

    /**
     * @return the companyFee
     */
    public Double getCompanyFee() {
        return companyFee;
    }

    /**
     * @param companyFee the companyFee to set
     */
    public void setCompanyFee(Double companyFee) {
        this.companyFee = companyFee;
    }

    /**
     * @return the costPerDay
     */
    public Double getCostPerDay() {
        return costPerDay;
    }

    /**
     * @param costPerDay the costPerDay to set
     */
    public void setCostPerDay(Double costPerDay) {
        this.costPerDay = costPerDay;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the allowLikes
     */
    public boolean isAllowLikes() {
        return allowLikes;
    }

    /**
     * @param allowLikes the allowLikes to set
     */
    public void setAllowLikes(boolean allowLikes) {
        this.allowLikes = allowLikes;
    }

    /**
     * @return the allowComment
     */
    public boolean isAllowComment() {
        return allowComment;
    }

    /**
     * @param allowComment the allowComment to set
     */
    public void setAllowComment(boolean allowComment) {
        this.allowComment = allowComment;
    }

    /**
     * @return the editor
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @param editor the editor to set
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the approved
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
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
