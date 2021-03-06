package BackendUtilities;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;

/**
 * This class convert a JSON to object who extends from User this such as Admin,
 * Reader or Editor
 *
 * @author jefemayoneso
 */
public class Parser {

    // variables
    private Gson gson;

    /**
     * constructor
     */
    public Parser() {
        gson = new Gson();
    }

    /**
     * THis method transform any JSON string to OBJECT
     *
     * @param jsonString
     * @param type
     * @return
     */
    public Object toObject(String jsonString, Type type) {
        return this.gson.fromJson(jsonString, type);
    }

    /**
     * This method transform any OBJECT to JSON string
     *
     * @param src
     * @param typeOfSrc
     * @return
     */
    public String toJSON(Object src, Type typeOfSrc) {
        return this.gson.toJson(src, typeOfSrc);
    }

    public String toJSON(String stg) {
        return this.gson.toJson(stg);
    }

    /**
     * this method reads the BUFFERED READER from the FRONTEND and returns the
     * string read
     *
     * @param br
     * @return
     */
    public String getBody(BufferedReader br) {
        try {
            String body = "";
            String line = br.readLine();
            while (line != null) {
                body += line;
                line = br.readLine();
            }
            return body;
        } catch (IOException e) {
            System.out.println("Error parsing request string from buffered reader at [Parsers].[ReaderBR]\n" + e.getMessage());
            return null;
        }
    }

    /**
     * This method transform any lOCALDATE to SQL DATE
     *
     * @param date
     * @return
     */
    public Date toDate(LocalDate date) {
        try {
            return Date.valueOf(date);
        } catch (Exception e) {
            return null;
        }
    }

    public Date toDate(String date) {
        try {
            return Date.valueOf(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method transform any String to LocalDate
     *
     * @param dateString
     * @return
     */
    public LocalDate toLocalDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    /**
     * Parse a SQL date to LocalDate
     *
     * @param date
     * @return
     */
    public LocalDate toLocalDate(Date date) {
        return date.toLocalDate();
    }

    /**
     * Cast a string to Integer
     *
     * @param parameter
     * @return
     */
    public Integer toInteger(String parameter) {
        return Integer.valueOf(parameter);
    }
}
