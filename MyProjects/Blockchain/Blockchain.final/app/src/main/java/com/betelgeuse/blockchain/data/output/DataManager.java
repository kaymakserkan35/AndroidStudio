package com.betelgeuse.blockchain.data.output;

import com.betelgeuse.blockchain.data.IDatabase;
import com.betelgeuse.blockchain.data.dataListener.TickerListListener;

public class DataManager {
    IDatabase database;
    public DataManager (IDatabase database) {
        this.database = database;
    }
    public void readTickersOfDate (String date , TickerListListener listener) {
        // cache den al!!...
        if (true) {
            database.readTickersOfDate(date, listener);
        } else {
        }
    }

    public  void  readTickerHistory(String fromCurrency,String toCurrency,int history,TickerListListener listener){
        listener.onSuccess(null);
    }

}
