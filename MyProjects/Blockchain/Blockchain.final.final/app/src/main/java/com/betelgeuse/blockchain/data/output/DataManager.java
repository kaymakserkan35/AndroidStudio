package com.betelgeuse.blockchain.data.output;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.H;
import com.betelgeuse.blockchain.data.IDatabase;
import com.betelgeuse.blockchain.data.cache.ICache;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListListener;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListener;
import com.betelgeuse.blockchain.data.dataListener.UserOptionDTOListListener;
import com.betelgeuse.blockchain.data.dto.TickerDTO;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;
import com.betelgeuse.blockchain.userinterface.Model.UserOptionModel;

import java.util.ArrayList;
import java.util.List;

public class DataManager  extends AdvancedDataManager {
    ICache cache;
    IDatabase database;
    public DataManager (IDatabase database,ICache cache) {
        this.cache = cache;
        this.database = database;
    }
    public  void  readTickerHistory(String fromCurrency, String toCurrency, int history, TickerDTOListListener listener){

        if (fromCurrency.equalsIgnoreCase(this.baseCurrency)) {
            String date = getDaysAgoUTC_AsSimpleDateFormatString(history);
            database.readTickerFromDateToNow(toCurrency, date, tickerList -> {
                cache.createTickerList(tickerList);
                listener.onSuccess(tickerList);
            });
        }
        if (toCurrency.equalsIgnoreCase(baseCurrency)) {
            String date = getDaysAgoUTC_AsSimpleDateFormatString(history);
            database.readTickerFromDateToNow(toCurrency, date, tickerList -> {
                    reverseTickers(tickerList);
                    cache.createTickerList(tickerList);
                    listener.onSuccess(tickerList);
            });
        }
        if (!fromCurrency.equalsIgnoreCase(baseCurrency) && toCurrency.equalsIgnoreCase(baseCurrency)) {
            String date = getDaysAgoUTC_AsSimpleDateFormatString(history);
        }

    }
    public  void  readUserOptionsByEmail(String email, UserOptionDTOListListener listener){
        List<UserOptionDTO> userOptionDTOList =    cache.readUserOptionsAllByEmail(email);
        if (userOptionDTOList!= null) { listener.onSuccess(userOptionDTOList); }
        else database.readUserOptionsByEmail(email,listener);
    }
    public void createUserOption (UserOptionDTO userOption) {
        cache.createUserOption(userOption);
        database.createUserOption(userOption);
    }

}
