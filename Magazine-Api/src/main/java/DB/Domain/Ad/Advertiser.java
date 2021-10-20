package DB.Domain.Ad;

/**
 *
 * @author jefemayoneso
 */
public class Advertiser {

    private String name;

    public Advertiser() {
    }

    public Advertiser(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
