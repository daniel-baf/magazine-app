package APIMessages;

import DB.Domain.Magazine.Relations.Comment;

/**
 *
 * @author jefemayoneso
 */
public class CommentMessage {

    private String message;
    private Comment comment;

    public CommentMessage() {
    }

    public CommentMessage(String message, Comment comment) {
        this.message = message;
        this.comment = comment;
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
     * @return the comment
     */
    public Comment getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(Comment comment) {
        this.comment = comment;
    }

}
