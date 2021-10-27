package DB.Models.forJasperReports;

import DB.DAOs.Magazine.Financials.CommentSelect;
import DB.DAOs.Magazine.Financials.LikeSelect;
import DB.DAOs.Magazine.Financials.SubscriptionSelect;
import DB.DAOs.Magazine.MagazineSelect;
import DB.Domain.Financial.Subscription;
import DB.Domain.Magazine.Comment;
import DB.Domain.forJasperReports.EditorEarnings;
import DB.Domain.forJasperReports.MaganizeSubscriptionReport;
import DB.Domain.forJasperReports.MagazineCommentsReport;
import DB.Domain.forJasperReports.MagazineLikeReport;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class EditorReportModel {

    public ArrayList<MagazineCommentsReport> getMagsMostCommented(String editor, Date date1, Date date2, boolean validDates) {
        // get names
        ArrayList<String> names = new MagazineSelect().selectOwned(editor);
        ArrayList<MagazineCommentsReport> magsComm = new ArrayList<>();
        // objects
        CommentSelect cs = new CommentSelect();
        // vars
        ArrayList<Comment> comments;
        // create array list with comments
        for (String name : names) {
            comments = cs.getComments(name, date1, date2, validDates);
            magsComm.add(new MagazineCommentsReport(name, comments));
        }
        return magsComm;
    }

    public ArrayList<MaganizeSubscriptionReport> getMagsMostSUbscribed(String editor, Date date1, Date date2, boolean validDates) {
        ArrayList<String> magazines = new MagazineSelect().selectOwned(editor);
        ArrayList<MaganizeSubscriptionReport> magsSubs = new ArrayList<>();
        // need the subs
        SubscriptionSelect subsSelect = new SubscriptionSelect();
        for (String mag : magazines) {
            magsSubs.add(new MaganizeSubscriptionReport(mag, subsSelect.select(validDates, date1, date2, mag)));
        }
        return magsSubs;
    }

    public ArrayList<MagazineLikeReport> getMagazineLikesReport(String editor, Date date1, Date date2, boolean validDates) {
        ArrayList<String> magazines = new MagazineSelect().selectOwned(editor);
        ArrayList<MagazineLikeReport> likesReport = new ArrayList<>();
        for (String mag : magazines) {
            likesReport.add(new MagazineLikeReport(mag, new LikeSelect().select(mag)));
        }
        return likesReport;
    }

    public ArrayList<EditorEarnings> getEditorEarnings(String editor, Date date1, Date date2, boolean validDates) {
        ArrayList<String> magazines = new MagazineSelect().selectOwned(editor);
        ArrayList<EditorEarnings> magsEarnings = new ArrayList<>();
        for (String mag : magazines) {
            ArrayList<Subscription> subs = new SubscriptionSelect().select(validDates, date1, date2, mag);
            magsEarnings.add(new EditorEarnings(mag, new SubscriptionSelect().getSubsWithFee(validDates, date1, date2, mag, false)));
        }
        return magsEarnings;
    }
}
