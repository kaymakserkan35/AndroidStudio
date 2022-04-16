package com.betelgeuse.blockchain.data;

import android.content.Context;
import android.icu.util.Currency;
import android.os.Build;

;

import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public abstract class ADatabase {
    private MyDate myDate;
    private String timezoneOfLondra   = "GMT";
    private String dateFormatPattern1  = "yyy-MM-dd";
    private String dateFormatPattern2 = "yyyy-MM-dd HH:mm:ss";

    public ADatabase () {
        myDate = new MyDate();
    }
    public String generateUniqueID ( ) {
        String uniqueID = getDateNowOfTimeZone(timezoneOfLondra) + "_" + UUID.randomUUID().toString();
        return uniqueID;
    }

    public String getDateNowOfTimeZone (String timezone) {
      return   myDate.getDateNowOfTimeZone(timezone,null);
      // output : 18-03-2022
    }
    public String getDateNowOfCurrentTimeZone ( ) {
      return   myDate.getDateNowOfCurrentTimeZone();
      // output : 18-03-2022
    }
    public String getYearNowOfTimezone (String timezone) {
     return    myDate.getYearNowOfTimezone(timezone);
    }
    public String getYearNowOfCurrentTimeZone ( ) {
      return   myDate.getYearNowOfCurrentTimeZone();
    }
    public Date convertLocalDateToUTC(Date date) {
       return  myDate.convertLocalDateToUTC(date);
    }
    public Date parseStringDateToDateObject(String date) {
        return myDate.parseStringDateToDateObject(date);
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
