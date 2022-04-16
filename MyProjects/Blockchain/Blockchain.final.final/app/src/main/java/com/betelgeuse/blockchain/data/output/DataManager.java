package com.betelgeuse.blockchain.data.output;

import androidx.annotation.Nullable;

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
    private void readTickersOfDate (String date , TickerDTOListListener listener) {
        // cache den al!!...
        if (true) {
            database.readTickersOfDate(date, listener);
        } else {
        }
    }
    private void readTickersFromDateToNow (String date, @Nullable TickerDTOListListener listener) {

    }
    private void readTickerOfDate (String toCurrency, String dateValue, @Nullable TickerDTOListener listener) {

    }
    private void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerDTOListListener listener) {

    }
    public  void  readTickerHistory(String fromCurrency, String toCurrency, int history, TickerDTOListListener listener){
        readTickerFromDateToNow("BTC","2022-03-01",listener);
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
