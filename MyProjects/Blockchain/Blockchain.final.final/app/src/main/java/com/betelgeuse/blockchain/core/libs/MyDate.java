package com.betelgeuse.blockchain.core.libs;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class MyDate {
    public static class TimeZones {
        public static final String TimeZoneOfLondra_UTC = "GMT";
    }
    public static class DateFormats {
        public static final String complexDateFormat = "yyyy-MM-dd HH:mm:ss";
        public static final String simpleDateFormat  = "yyy-MM-dd";
    }
    public String getDateNowOfTimeZone (String timezone, String dateFormat) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if (dateFormat == null) {
                dateFormat = DateFormats.simpleDateFormat;
            }
            String now = null;
            SimpleDateFormat ISO_8601_FORMAT = null;
            ISO_8601_FORMAT = new SimpleDateFormat(dateFormat);
            ISO_8601_FORMAT.setTimeZone(android.icu.util.TimeZone.getTimeZone(timezone));
            now = ISO_8601_FORMAT.format(new Date());
            return now; // output : 18-03-2022
        }
        return null;

    }

    public String getDateNowOfCurrentTimeZone ( ) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            String now = null;
            SimpleDateFormat ISO_8601_FORMAT = null;
            ISO_8601_FORMAT = new SimpleDateFormat(DateFormats.simpleDateFormat);
            now = ISO_8601_FORMAT.format(new Date());
            return now; // output : 18-03-2022
        }
        return null;
    }

    public String getYearNowOfTimezone (String timezone) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(timezone));
            return String.valueOf(calendar.getWeekYear());
        }
        return null;
    }

    public String getYearNowOfCurrentTimeZone ( ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Calendar calendar = Calendar.getInstance();
            return String.valueOf(calendar.getWeekYear());
        }
        return null;

    }

    public Date convertDateToDateUTC (Date date) {
        Date dateUTC = new Date();
        dateUTC.setTime(date.getTime() + date.getTimezoneOffset() * 100 * 60);
        return dateUTC;
    }

    public Date parseStringDateToDateObject (String date) {

        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String dayOfMonth = date.split("-")[2];
        Date _date =
                new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(dayOfMonth));
        return _date;
    }

}
