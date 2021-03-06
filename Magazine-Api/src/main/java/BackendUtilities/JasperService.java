package BackendUtilities;

import DB.Domain.forJasperReports.EarningResult;
import DB.Domain.forJasperReports.MaganizeSubscriptionReport;
import DB.Domain.forJasperReports.MagazineCommentsReport;
import DB.GeneralPaths;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author jefemayoneso
 */
public class JasperService {

    /**
     * Print a JasperReport as PDF using a outputstream
     *
     * @param output
     * @param jasperPath
     * @param mapParameters
     * @throws JRException
     */
    public void printReport(OutputStream output, String jasperPath, Map<String, Object> mapParameters) throws JRException {
        InputStream jasperCompiled = getClass().getClassLoader().getResourceAsStream(jasperPath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperCompiled, mapParameters, DB.DBConnection.getConnection());
        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
    }

    /**
     * Fill a report with a list of objects generated from java as
     * JRBeanCollection
     *
     * @param list
     * @param jasperPath
     * @param mapParameters
     * @param output
     * @throws JRException
     */
    public void printReportWithComplexBeans(ArrayList<MaganizeSubscriptionReport> list, String jasperPath, Map<String, Object> mapParameters, OutputStream output) throws JRException {
        InputStream jasperCompiled = getClass().getClassLoader().getResourceAsStream(jasperPath);
//        JRDataSource source = new JRBeanCollectionDataSource(list);
        JasperPrint printer = JasperFillManager.fillReport(jasperCompiled, mapParameters, new JRBeanCollectionDataSource(list));
        JasperExportManager.exportReportToPdfStream(printer, output);
    }

    public void printReportWithComplexBeans1(ArrayList<MagazineCommentsReport> list, String jasperPath, Map<String, Object> mapParameters, OutputStream output) throws JRException {
        InputStream jasperCompiled = getClass().getClassLoader().getResourceAsStream(jasperPath);
        JasperPrint printer = JasperFillManager.fillReport(jasperCompiled, mapParameters, new JRBeanCollectionDataSource(list));
        JasperExportManager.exportReportToPdfStream(printer, output);
    }

    public void printReportWithComplexBeans2(ArrayList<EarningResult> list, String jasperPath, Map<String, Object> mapParameters, OutputStream output) throws JRException {
        InputStream jasperCompiled = getClass().getClassLoader().getResourceAsStream(jasperPath);
        JasperPrint printer = JasperFillManager.fillReport(jasperCompiled, mapParameters, new JRBeanCollectionDataSource(list));
        JasperExportManager.exportReportToPdfStream(printer, output);
    }

    /**
     * Return the relative path of a Jasper report
     *
     * @param repType
     * @param validDates
     * @return
     */
    public String getMasterReportPathEditor(String repType, boolean validDates) {
        String path1 = GeneralPaths.JASPER_EDITOR_MAIN_PATH_RELATIVE.getMessage();
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

    /**
     * Return the relative path of a Jasper report for admin
     *
     * @param repType
     * @param validDates
     * @return
     */
    public String getMasterReportPathAdmin(String repType, boolean validDates) {
        String path1 = GeneralPaths.JASPER_ADMIN_MAIN_PATH_RELATIVE.getMessage();
        switch (repType) {
            case "earns-advers":
                return validDates ? path1 + "EarningsAds.jasper" : path1 + "EarningsAdsNoParms.jasper";
            case "most-subscribed":
                return path1 + "TopMags.jasper";
            case "most-commented":
                return path1 + "MagsMostComments.jasper";
            case "earns-mags":
                return validDates ? path1 + "MagEarning.jasper" : path1 + "MagEarningsNoParms.jasper";
            case "total-earnings":
                return path1 + "TotalEarnings.jasper";
            default:
                return null;
        }
    }

    /**
     * Generate a Map Key with owner, date_start date_end and SUB_REP_DIR for
     * jasper master report
     *
     * @param dateStart
     * @param dateEnd
     * @param owner
     * @param validDates
     * @param subDir
     * @return
     */
    public Map<String, Object> getBasicKeyMapForJasper(Date dateStart, Date dateEnd, String owner, boolean validDates, String subDir) {
        Map<String, Object> map = new HashMap<>();
        map.put("owner", owner);
        map.put("date_start", dateStart);
        map.put("date_end", dateEnd);
        map.put("SUB_REP_DIR", subDir);
        return map;
    }

}
