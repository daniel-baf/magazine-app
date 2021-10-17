package APIMessages;

import DB.Domain.Magazine.MagazinePost;

/**
 * This is a class to receive JSON from FRONTEND for Magazine Posts
 *
 * @author jefemayoneso
 */
public class MagazinePostMessage {

    private String message;
    private MagazinePost post;

    public MagazinePostMessage() {
    }

    public MagazinePostMessage(String message, MagazinePost post) {
        this.message = message;
        this.post = post;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the post
     */
    public MagazinePost getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(MagazinePost post) {
        this.post = post;
    }
}
