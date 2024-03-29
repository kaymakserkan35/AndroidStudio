package com.betelgeuse.blockchain.data;

import android.icu.util.Currency;
import android.os.Build;

;

import com.betelgeuse.blockchain.core.libs.MyDate;

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
        String uniqueID = getDate_AsSimpleDateFormatString(timezoneOfLondra) + "_" + UUID.randomUUID().toString();
        return uniqueID;
    }

    public String getDaysAgoUTC_AsSimpleDateFormatString(int daysAgo){
      return myDate.getDaysAgoUTC_AsSimpleDateFormatString(daysAgo);
    }
    public String getDate_AsSimpleDateFormatString (String timezone) {
      return   myDate.getDate_AsSimpleDateFormatString();
      // output : 18-03-2022
    }
    public String getYearNowOfCurrentTimeZone ( ) {
      return   myDate.getYearUTC();
    }
    public Date parseStringDateToDateObject(String date) {
        return myDate.convertSimpleDateFormatStringToDateObject(date);
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
