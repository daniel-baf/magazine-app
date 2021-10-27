package DB.Domain.forJasperReports;

import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class EditorEarnings {

    private String magazine;
    ArrayList<ReaderSubscription> subs;

    public EditorEarnings() {
    }

    public EditorEarnings(String magazine, ArrayList<ReaderSubscription> subs) {
        this.magazine = magazine;
        this.subs = subs;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public ArrayList<ReaderSubscription> getSubs() {
        return subs;
    }

    public void setSubs(ArrayList<ReaderSubscription> subs) {
        this.subs = subs;
    }

}
