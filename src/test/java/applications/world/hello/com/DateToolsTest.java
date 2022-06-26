package applications.world.hello.com;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

public class DateToolsTest {

    @Test
    public void canInstantiateDateToolsWithoutDateStringAndTimeZone() {
        DateTools dateTools = new DateTools();
        assertNotNull(dateTools);
    }

    @Test
    public void canInstantiateDateToolsWithDateStringOnly() {
        String s = "MM/dd/yyyy";
        DateTools dateTools = new DateTools(s);
        assertNotNull(dateTools);
    }

    @Test
    public void canInstantiateDateToolsWithTimeZoneOnly() {
        TimeZone timeZone = TimeZone.getTimeZone("EST");
        DateTools dateTools = new DateTools(timeZone);
        assertNotNull(dateTools);
    }

    @Test
    public void canInstantiateDateToolsWithDateStringAndTimeZone() {
        String s = "MM/dd/yyyy";
        TimeZone timeZone = TimeZone.getTimeZone("EST");
        DateTools dateTools = new DateTools(s,timeZone);
        assertNotNull(dateTools);
    }

    @Test
    public void getDateFormatStringWhenStringIsProvided() {
        String s = "MM/dd/yyyy";
        DateTools dateTools = new DateTools(s);
        assertEquals(s, dateTools.getSimpleDateFormatPattern());
    }

    @Test
    public void getDateFormatStringWhenStringIsNotProvided() {
        DateTools dateTools = new DateTools();
        assertEquals("MM/dd/yyyy", dateTools.getSimpleDateFormatPattern());
    }

    @Test
    public void getDateFormatStringWhenMalformedFormatStringIsSuppliedThrowsException() {
        String s = "I am not a format string.";
        assertThrows(IllegalArgumentException.class, () -> new DateTools(s));
    }

    @Test
    // Week Year format caused some issues here.  Readings below
    // https://stackoverflow.com/questions/8686331/y-returns-2012-while-y-returns-2011-in-simpledateformat
    // https://docs.oracle.com/javase/7/docs/api/java/util/GregorianCalendar.html
    public void formatDateStringSuccessfully() {
        String s = "1970-01-01 00:00:00 EST";
        String format = "yyyy-MM-dd HH:mm:ss zzz";
        DateTools dateTools = new DateTools(format);
        assertEquals("Thu Jan 01 00:00:00 EST 1970", dateTools.convertStringToDate(s).toString());
    }

    @Test
    public void convertMalformedDateStringReturnsNull() {
        String s = "1970-01-01 00:00:00 E_T";
        String format = "yyyy-MM-dd HH:mm:ss zzz";
        DateTools dateTools = new DateTools(format);
        assertNull(dateTools.convertStringToDate(s));
    }

    @Test
    // Be careful with Zone IDs.  Readings below:
    // https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html
    public void getTimeZoneWhenTimeZoneIsProvided() {
        TimeZone timeZone = TimeZone.getTimeZone("EST");
        DateTools dateTools = new DateTools(timeZone);
        assertEquals("Eastern Standard Time", dateTools.getTimeZoneDisplayName());
    }

    @Test
    public void getTimeZoneWhenTimeZoneIsNotProvided() {
        DateTools dateTools = new DateTools();
        assertEquals("Greenwich Mean Time", dateTools.getTimeZoneDisplayName());
    }

    @Test
    public void convertDateToStringSuccessfully() {
        Date date = new Date(0);
        String actualDate = "1970-01-01 00:00:00 GMT";
        String format = "yyyy-MM-dd HH:mm:ss zzz";
        DateTools dateTools = new DateTools(format);

        assertEquals(actualDate, dateTools.convertDateToStringFormat(date));
    }

}
