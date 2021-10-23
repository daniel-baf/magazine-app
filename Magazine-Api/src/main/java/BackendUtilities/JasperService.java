package BackendUtilities;

import DB.GeneralPaths;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author jefemayoneso
 */
public class JasperService {

    public void printReport(OutputStream output, String jasperPath, Map<String, Object> mapParameters) throws JRException {
//        InputStream jasperCompiled = getClass().getClassLoader().getResourceAsStream(jasperPath);
        InputStream jasperCompiled = JRLoader.getFileInputStream(jasperPath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperCompiled, mapParameters, DB.DBConnection.getConnection());
        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
    }

    public String getRespectiveEditorJasperPath(String repType, boolean validDates) {
        String path1 = GeneralPaths.JASPER_EDITOR_PATH.getMessage();
        switch (repType) {
            case "comments-mag":
                return validDates ? path1 + "MagsEditorComments.jasper" : path1 + "MagsEditorCommentsNoParms.jasper";
            case "subs-mag":
                return validDates ? path1 + "MagsEditorSubscription.jasper" : path1 + "MagsEditorSubscriptionNoParms.jasper";
            case "most-liked":
                return validDates ? path1 + "MagLikes.jasper" : path1 + "MagLikesNoParms.jasper";
            case "earnings":
                return validDates ? path1 + "EditorEarning.jasper" : path1 + "EditorEarningNoParms.jasper";
            default:
                return null;
        }
    }

    public Map<String, Object> getOwnerDatesMap(Date dateStart, Date dateEnd, String owner, boolean validDates, String subDir) {
        Map<String, Object> map = new HashMap<>();
        map.put("owner", owner);
        map.put("date_start", dateStart);
        map.put("date_end", dateEnd);
        map.put("SUB_REP_DIR", subDir);
        return map;
    }

}
