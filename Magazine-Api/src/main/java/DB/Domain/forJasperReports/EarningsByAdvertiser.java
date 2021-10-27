package DB.Domain.forJasperReports;

import DB.Domain.Financial.Ad;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class EarningsByAdvertiser {

    private String advertiser;
    private ArrayList<Ad> ads;

    public EarningsByAdvertiser() {
    }

    public EarningsByAdvertiser(String advertiser, ArrayList<Ad> ads) {
        this.advertiser = advertiser;
        this.ads = ads;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public ArrayList<Ad> getAds() {
        return ads;
    }

    public void setAds(ArrayList<Ad> ads) {
        this.ads = ads;
    }

}
