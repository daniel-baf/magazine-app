package Models;

import DB.DAOs.Magazine.Relations.LikeSelect;

/**
 *
 * @author jefemayoneso
 */
public class LikesModel {

    public int getLikesCounter(String magazine) {
        return new LikeSelect().countLikes(magazine);
    }
}
