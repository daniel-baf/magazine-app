package Models;

import DB.DAOs.Magazine.Financials.AdvertiserInsert;
import DB.DAOs.Magazine.Financials.AdvertiserSelect;
import DB.DAOs.Magazine.Reactions.AdInsert;
import DB.DAOs.Magazine.Reactions.AdSelect;
import DB.Domain.Financial.Ad;
import DB.Domain.Financial.Advertiser;
import java.util.ArrayList;
import javax.servlet.http.Part;

/**
 *
 * @author jefemayoneso
 */
public class AdsModel {

    /**
     * Get a list of advertisers
     *
     * @param limit
     * @param offset
     * @param action
     * @return
     */
    public ArrayList<String> getAdvertiser(int limit, int offset, String action) {
        switch (action) {
            case "GET_ADVERTISERS":
                return new AdvertiserSelect().getAdvertisers(limit, offset);
            default:
                return null;
        }
    }

    /**
     * Return a message about the status of insert, NO_ERROR if DAO returns != 0
     * , ERROR if = 0
     *
     * @param advertiser
     * @return
     */
    public String registNewAdvertiser(Advertiser advertiser) {
        return new AdvertiserInsert().insert(advertiser) != 0 ? "NO_ERROR" : "ERROR_INSERT";
    }

    /**
     * Create an Ad to DB
     *
     * @param ad
     * @param part
     * @return
     */
    public String createNewAdd(Ad ad, Part part) {
        if (ad.getType() == 2) {
            ad.setVideoUrl(null);
        } else if (ad.getType() == 3) {
            ad.setImgLocalPath(null);
        }
        return new AdInsert().insert(ad, part) != 0 ? "NO_ERROR" : "ERROR_INSERT";
    }

    /**
     * Return a random ad or null
     *
     * @param type
     * @param user
     * @return
     */
    public Ad getRandomAd(int type, String user) {
        return new AdSelect().getRandomAdByUserIntrest(type, user);
    }
}
