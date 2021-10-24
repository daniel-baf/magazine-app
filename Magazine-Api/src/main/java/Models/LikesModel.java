package Models;

import DB.DAOs.Magazine.Financials.LikeInsert;
import DB.DAOs.Magazine.Financials.LikeSelect;
import DB.Domain.Magazine.Like;

/**
 *
 * @author jefemayoneso
 */
public class LikesModel {

    public int getLikesCounter(String magazine) {
        return new LikeSelect().countLikes(magazine);
    }

    public String leaveLike(Like like) {
        return new LikeInsert().insert(like) != 0 ? "NO_ERROR" : "ERROR_INSERT";
    }
}
