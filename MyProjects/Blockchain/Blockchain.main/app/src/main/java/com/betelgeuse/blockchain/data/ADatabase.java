package com.betelgeuse.blockchain.data;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Currency;
import android.net.ParseException;
import android.os.Build;

import androidx.annotation.RequiresApi;
;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

public abstract class ADatabase {
    private String timezoneOfLondra   = "GMT";
    private String dateFormatPattern1  = "yyy-MM-dd";
    private String dateFormatPattern2 = "yyyy-MM-dd HH:mm:ss";
    Context context;

    public ADatabase (Context context) {
        this.context = context;
    }

    public String generateUniqueID ( ) {
        String uniqueID =
                getDateNowOfTimeZone(timezoneOfLondra) + "_" + UUID.randomUUID().toString();
        return uniqueID;
    }

    public String getDateNowOfTimeZone (String timezone) {
        String now = null;
        SimpleDateFormat ISO_8601_FORMAT = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            ISO_8601_FORMAT = new SimpleDateFormat(dateFormatPattern1);
            ISO_8601_FORMAT.setTimeZone(android.icu.util.TimeZone.getTimeZone(timezone));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            now = ISO_8601_FORMAT.format(new Date());
        }
        return now; // output : 18-03-2022
    }
    public String getDateNowOfCurrentTimeZone ( ) {
        SimpleDateFormat ISO_8601_FORMAT = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            ISO_8601_FORMAT = new SimpleDateFormat(dateFormatPattern1);
        }
        String now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            now = ISO_8601_FORMAT.format(new Date());
        }
        return now; // output : 18-03-2022
    }
    public String getYearNowOfTimezone (String timezone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(timezone));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return String.valueOf(calendar.getWeekYear());
        } else {
            return null;
        }
    }
    public String getYearNowOfCurrentTimeZone ( ) {
        Calendar calendar = Calendar.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return String.valueOf(calendar.getWeekYear());
        } else {
            return null;
        }
    }
    public Date convertLocalDateToUTC(Date date) {
        Date dateUTC = new Date();
        dateUTC.setTime(date.getTime()+date.getTimezoneOffset()*100*60);
        return  dateUTC;
    }
    public Date parseStringDateToDateObject(String date) {

        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String dayOfMonth = date.split("-")[2];
        Date _date = new Date(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(dayOfMonth));
         return  _date;
        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Date date1;
            DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern1);
            dateFormat.setLenient(false);
            try {
                date1  = dateFormat.parse(date);
                return  date1;
            } catch (ParseException | java.text.ParseException e) {
                System.out.println(e.getLocalizedMessage());
            }

        }
        return  null;
        */
    }
    /*--------------------------------------------------------------------*/
    private Currency getCurrency (Locale locale) {
        Currency currency = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            currency = Currency.getInstance(locale);

        }
        return currency;
    }

    public String getCurrencySymbol (Locale locale) {
        String symbol = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            symbol = getCurrency(locale).getSymbol();
        }
        return symbol;
    }

    private Set<Currency> getAllCurrencies ( ) {
        Set<Currency> toret = new HashSet<Currency>();
        Locale[] locs = Locale.getAvailableLocales();
        for (Locale loc : locs) {
            try {
                Currency currency = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    currency = Currency.getInstance(loc);
                }
                if (currency != null) {
                    toret.add(currency);
                }
            } catch (Exception exc) {
                // Locale not found
            }
        }
        return toret;

    }

}
