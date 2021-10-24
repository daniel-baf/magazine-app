package DB.Domain.forJasperReports;

import java.sql.Date;

/**
 *
 * @author jefemayoneso
 */
public class EarningResult {

    private String type;
    private double entry;
    private double loss;
    private Date date;

    public EarningResult() {
    }

    public EarningResult(String type, double entry, double loss, Date date) {
        this.type = type;
        this.entry = entry;
        this.loss = loss;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getEntry() {
        return entry;
    }

    public void setEntry(double entry) {
        this.entry = entry;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
