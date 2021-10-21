package Models;

import BackendUtilities.Parser;
import DB.DAOs.Magazine.Post.MagazinePostSelect;
import DB.Domain.Magazine.MagazinePost;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author jefemayoneso
 */
public class PDFModel {

    MagazinePost post;

    public PDFModel(String id) {
        Parser parser = new Parser();
        this.post = new MagazinePostSelect().select(parser.toInteger(id));
    }

    public InputStream getInputStreamPdf() throws FileNotFoundException {
        return new FileInputStream(post.getPdfNamePath());
    }

    public String getFileName() {
        return this.post.getId() + "_" + this.post.getTitle() + "_" + this.post.getMagazine() + ".pdf";
    }
}
