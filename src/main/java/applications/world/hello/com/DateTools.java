package applications.world.hello.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTools {

    protected final SimpleDateFormat dateFormat;


    /**
     * Default constructor.  Automatically assigns the SimpleDateFormat pattern to "MM/dd/yyyy".
     */
    public DateTools() {
        this("MM/dd/yyyy", TimeZone.getTimeZone("Greenwich Mean Time"));
    }

    /**
     *
     * @param dateFormat SimpleDateFormat pattern for String to Date conversion.
     */
    public DateTools(String dateFormat) {
        this(dateFormat, TimeZone.getTimeZone("Greenwich Mean Time"));
    }

    /**
     *
     * @param timeZone Assigns the time zone to base calculations in.
     */
    public DateTools(TimeZone timeZone) {
        this("MM/dd/yyyy", timeZone);
    }

    /**
     *
     * @param dateFormat SimpleDateFormat string describing the format for Date and Time conversions.
     * @param timeZone Assigns the time zone to base calculations in.
     */
    public DateTools(String dateFormat, TimeZone timeZone) {
        this.dateFormat = new SimpleDateFormat(dateFormat);
        this.dateFormat.setTimeZone(timeZone);
    }

    /**
     * Converts a Date String to a Date value.  Returns null on failure.
     * @param dateString Date to be converted.
     * @return Date object assigned to the date provided.
     */
    public Date convertStringToDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        }
        catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Returns the SimpleDateFormat pattern being used.
     * @return Date object assigned to the Date and Time of dateString.
     */
    public String getSimpleDateFormatPattern() {
        return dateFormat.toLocalizedPattern();
    }

    /**
     *
     * @return Display Name of the current Time Zone.
     */
    public String getTimeZoneDisplayName() {
        return dateFormat.getTimeZone().getDisplayName();
    }

    /**
     *
     * @param date Date object to be converted.
     * @return Date and Time contained in the supplied Date Object formatted with the SimpleDateFormat assigned.
     */
    public String convertDateToStringFormat(Date date) {
        return dateFormat.format(date);
    }
}
