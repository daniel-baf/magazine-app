package Models;

import APIMessages.MagazinePostMessage;
import DB.DAOs.Magazine.Post.MagazinePostInsert;
import DB.DAOs.Magazine.Post.MagazinePostSelect;
import DB.Domain.Magazine.MagazinePost;
import BackendUtilities.Parser;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * Redirect to multiple actions for Magazine POSTs
 *
 * @author jefemayoneso
 */
public class MagazinePostModel {

    /**
     * Update a new post and save the file at server
     *
     * @param request
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public MagazinePostMessage createNewPost(HttpServletRequest request) throws IOException, ServletException {
        Parser parser = new Parser();
        Part filePart = request.getPart("datafile");
        MagazinePostMessage message = (MagazinePostMessage) parser.toObject(request.getParameter("mag-post"), MagazinePostMessage.class);

        switch (message.getMessage()) {
            case "CREATE":
                message.getPost().setPdfPart(filePart);
                message.getPost().setDate(parser.toLocalDate(message.getPost().getDateString()));
                message.setMessage(new MagazinePostInsert().insert(message.getPost()) != 0 ? "NO_ERROR" : "ERROR_INSERT");
                break;
            default:
                message.setMessage("UNKNOWN_ACTION");
        }
        return message;
    }

    public ArrayList<MagazinePost> getPosts(HttpServletRequest request) {
        Parser parser = new Parser();
        ArrayList<MagazinePost> posts;
        switch (request.getParameter("action")) {
            case "FOR_MAG":
                posts = new MagazinePostSelect().select(request.getParameter("magazine"),
                        parser.toInteger(request.getParameter("limit")), parser.toInteger(request.getParameter("offset")));
                break;
            case "SPECIFIC":
                posts = new ArrayList<MagazinePost>();
                posts.add(new MagazinePostSelect().select(parser.toInteger(request.getParameter("id"))));
                break;
            default:
                System.out.println("UNKNOWN action at [MagazinePostModel]");
                return null;
        }
        return posts;
    }
}
