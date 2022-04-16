package com.betelgeuse.blockchain.data.firebase;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.data.dataListener.TickerListListener;
import com.betelgeuse.blockchain.data.dataListener.TickerListener;
import com.betelgeuse.blockchain.data.IDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseDB implements IDatabase {


    private TickerDB tickerDB;

    public FirebaseDB (FirebaseFirestore db) {
        tickerDB = new TickerDB(db);
    }

    @Override
    public void readTickersOfDate (String date, TickerListListener listener) {
        tickerDB.readTickersOfDate(date, listener);
    }

    @Override
    public void readTickersFromDateToNow (String date, @Nullable TickerListListener listener) {
        tickerDB.readTickersFromDateToNow(date, listener);
    }

    @Override
    public void readTickerOfDate (String toCurrency, String dateValue, @Nullable TickerListener listener) {
        tickerDB.readTickerOfDate(toCurrency, dateValue, listener);
    }

    @Override
    public void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerListListener listener) {
        tickerDB.readTickerFromDateToNow(toCurrency, fromDate, listener);
    }

}
