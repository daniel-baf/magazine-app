package BackendUtilities;

/**
 *
 * @author jefemayoneso
 */
public class VarsChecker {

    /**
     * Return true if strings are valid dates
     *
     * @param d1
     * @param d2
     * @return
     */
    public boolean needReportByDates(String d1, String d2) {
        Parser parser = new Parser();
        try {
            if (d2.isBlank() || d2.isEmpty()) {
                return false;
            }
            if (d1.isBlank() || d1.isEmpty()) {
                return false;
            } else {
                if (parser.toLocalDate(d1).isAfter(parser.toLocalDate(d2))) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }
}
