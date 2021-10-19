package Models;

import DB.DAOs.Magazine.Relations.CommentInsert;
import DB.DAOs.Magazine.Relations.CommentSelect;
import DB.Domain.Magazine.Relations.Comment;
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
        if (new CommentInsert().insert(comment) > 0) {
            return "NO_ERROR";
        } else {
            return "ERROR_ON_INSERT";
        }
    }
}
