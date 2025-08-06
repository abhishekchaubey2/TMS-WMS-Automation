package common.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class ExpressDateTimeUtility {
    private static final String TIME_ZONE = "Asia/Kolkata";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String ISO_8601_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    private static final String DD_MM_YYYY = "dd/MM/yyyy";

    /*** This function returns datetime in UTC
     Accept days as long, ex. Current date time : 0, Tomorrow : 1, Day after : 2 and so on ***/
    public static String getDateTime(long days) {
        Instant instant = Instant.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Instant requestedDateTimeInUTC = instant.plus(days, ChronoUnit.DAYS);
        Date myDate = Date.from(requestedDateTimeInUTC);
        return dateFormat.format(myDate);
    }

    public static String getISTDateTimeWithDayHourMinuteAndSecond(Long dayOffset, Long hours, Long minutes, Long seconds) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(TIME_ZONE));
        System.out.println("Current Date Time : " + now);

        // Apply the offsets
        ZonedDateTime requestedDayDateTime = now.plusDays(dayOffset)
                .plusHours(hours + 5L)
                .plusMinutes(minutes + 30L)
                .plusSeconds(seconds);


        // Convert to UTC
        ZonedDateTime utcZonedDateTime = requestedDayDateTime.withZoneSameInstant(ZoneId.of("UTC"));

        // Format the final datetime to the required format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO_8601_DATE_TIME_FORMAT);
        String formattedDateTime = utcZonedDateTime.format(formatter);
        System.out.println("UTC Date Time : " + formattedDateTime);
        return formattedDateTime;
    }

    public static String getDateMonthYearFormatDate(long days){
        Instant instant = Instant.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
        Date date = new Date();
        Instant plusDay = instant.plus(days, ChronoUnit.DAYS);
        Date myDate = Date.from(plusDay);
        String formattedDate = dateFormat.format(myDate);
        System.out.println("Formatted Date: " + formattedDate);
        return formattedDate;
    }

    public static String getModularCurrentTimeStamp(){
        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
        int timeStamp = (int) (currentTimeInSeconds % 1000000000);
        return timeStamp+"";
    }
}
