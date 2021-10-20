package Models;

import DB.DAOs.Magazine.Relations.LikeInsert;
import DB.DAOs.Magazine.Relations.LikeSelect;
import DB.Domain.Magazine.Relations.Like;

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
