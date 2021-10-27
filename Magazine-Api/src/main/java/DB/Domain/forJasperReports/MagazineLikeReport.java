package DB.Domain.forJasperReports;

import DB.Domain.Magazine.Like;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class MagazineLikeReport {

    private String magazine;
    private ArrayList<Like> likes;

    public MagazineLikeReport() {
    }

    public MagazineLikeReport(String magazine, ArrayList<Like> likes) {
        this.magazine = magazine;
        this.likes = likes;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

}
