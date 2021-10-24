package DB.Domain.forJasperReports;

import DB.Domain.Magazine.Comment;
import java.util.List;

/**
 *
 * @author jefemayoneso
 */
public class MagazineCommentsReport {

    private String magazine;
    private List<Comment> comments;

    public MagazineCommentsReport() {
    }

    public MagazineCommentsReport(String magazine, List<Comment> comments) {
        this.magazine = magazine;
        this.comments = comments;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
