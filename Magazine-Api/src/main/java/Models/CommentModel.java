package Models;

import DB.DAOs.Magazine.Financials.CommentInsert;
import DB.DAOs.Magazine.Financials.CommentSelect;
import DB.Domain.Magazine.Comment;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class CommentModel {

    /**
     * *
     * Return a list of magazines with comments
     *
     * @param mag
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<Comment> getCommentsForMag(String mag, int limit, int offset) {
        return new CommentSelect().getComments(limit, offset, mag);
    }

    /**
     * Insert a comMent to DB
     *
     * @param comment
     * @return
     */
    public String publishComment(Comment comment) {
        if (new CommentInsert().insert(comment) != 0) {
            return "NO_ERROR";
        }
        return "ERROR_ON_INSERT";
    }
}
