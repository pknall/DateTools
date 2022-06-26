package applications.world.hello.com;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

public class DateToolsTest {

    /*
     * Constructors
     */

    @Test public void canInstantiateDateToolsWithoutDatePatternAndTimeZone() {
        DateTools dateTools = new DateTools();
        assertNotNull(dateTools);
    }

    @Test public void canInstantiateDateToolsWithDatePatternOnly() {
        String datePattern = "MM/dd/yyyy";
        DateTools dateTools = new DateTools(datePattern);
        assertNotNull(dateTools);
    }

    @Test public void canInstantiateDateToolsWithTimeZoneOnly() {
        TimeZone timeZone = TimeZone.getTimeZone("EST");
        DateTools dateTools = new DateTools(timeZone);
        assertNotNull(dateTools);
    }

    @Test public void canInstantiateDateToolsWithDatePatternAndTimeZone() {
        String datePattern = "MM/dd/yyyy";
        TimeZone timeZone = TimeZone.getTimeZone("EST");
        DateTools dateTools = new DateTools(datePattern,timeZone);
        assertNotNull(dateTools);
    }

    @Test public void instantiatingDateToolsWithNullDatePatternThrowsNullPointerException() {
        TimeZone timeZone = TimeZone.getTimeZone("EST");
        assertThrows(NullPointerException.class, () -> new DateTools(null, timeZone));
    }

    @Test public void instantiatingDateToolsWithMalformedDatePatternThrowsIllegalArgumentException() {
        String invalidDatePattern = "I am not a format string.";
        assertThrows(IllegalArgumentException.class, () -> new DateTools(invalidDatePattern));
    }

    @Test public void instantiatingDateToolsWithNullTimeZoneThrowsNullPointerException() {
        String datePattern = "MM/dd/yyyy";
        assertThrows(NullPointerException.class, () -> new DateTools(datePattern, null));
    }

    /*
     * getDateFormatString Accessor
     */

    @Test public void getDateFormatStringWhenStringIsProvidedIsCorrect() {
        String datePattern = "MM/dd/yyyy";
        DateTools dateTools = new DateTools(datePattern);
        assertEquals(datePattern, dateTools.getSimpleDateFormatPattern());
    }

    @Test public void getDateFormatStringWhenStringIsNotProvidedIsCorrect() {
        DateTools dateTools = new DateTools();
        assertEquals(dateTools.getDefaultDatePattern(), dateTools.getSimpleDateFormatPattern());
    }

    /*
     * getTimeZone Accessor
     */

    @Test public void getTimeZoneWhenTimeZoneIsProvided() {
        // Be careful with Zone IDs.  Readings below:
        // https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html
        TimeZone timeZone = TimeZone.getTimeZone("EST");
        DateTools dateTools = new DateTools(timeZone);
        assertEquals("Eastern Standard Time", dateTools.getTimeZoneDisplayName());
    }

    @Test public void getTimeZoneWhenTimeZoneIsNotProvided() {
        DateTools dateTools = new DateTools();
        assertEquals("Greenwich Mean Time", dateTools.getTimeZoneDisplayName());
    }

    /*
     * Date Object to String Conversion
     */

    @Test public void convertNullDateReturnsNullPointerException() {
        DateTools dateTools = new DateTools();
        assertThrows(NullPointerException.class, () -> dateTools.convertDateToStringFormat(null));
    }

    @Test public void convertDateToStringSuccessfully() {
        Date date = new Date(0);
        String dateString = "1970-01-01 00:00:00 GMT";
        String datePattern = "yyyy-MM-dd HH:mm:ss zzz";
        DateTools dateTools = new DateTools(datePattern);

        assertEquals(dateString, dateTools.convertDateToStringFormat(date));
    }

    /*
     * String to Date Object Conversion
     */

    @Test public void convertMalformedDatePatternReturnsParseException() {
        String dateString = "1970-01-01 00:00:00 E_T";
        String datePattern = "yyyy-MM-dd HH:mm:ss zzz";
        DateTools dateTools = new DateTools(datePattern);
        assertThrows(ParseException.class, () -> dateTools.convertStringToDate(dateString));
    }

    @Test public void convertNullStringReturnsNullPointerException() {
        String datePattern = "yyyy-MM-dd HH:mm:ss zzz";
        DateTools dateTools = new DateTools(datePattern);
        assertThrows(NullPointerException.class, () -> dateTools.convertStringToDate(null));
    }

    @Test public void convertStringToDateSuccessfully() throws ParseException {
        // Week Year format caused some issues here.  Readings below
        // https://stackoverflow.com/questions/8686331/y-returns-2012-while-y-returns-2011-in-simpledateformat
        // https://docs.oracle.com/javase/7/docs/api/java/util/GregorianCalendar.html
        String dateString = "1970-01-01 00:00:00 EST";
        String dateFormat = "yyyy-MM-dd HH:mm:ss zzz";
        DateTools dateTools = new DateTools(dateFormat);
        assertEquals("Thu Jan 01 00:00:00 EST 1970", dateTools.convertStringToDate(dateString).toString());
    }

}
