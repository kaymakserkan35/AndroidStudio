package com.betelgeuse.blockchain.data.firebase;

import android.content.Context;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.data.CallBackClasses.TickerListListener;
import com.betelgeuse.blockchain.data.CallBackClasses.TickerListener;
import com.betelgeuse.blockchain.data.IDatabase;
import com.betelgeuse.blockchain.data.ITickerDB;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class FirebaseDB implements IDatabase {


    private TickerDB tickerDB;

    public FirebaseDB (Context context, FirebaseFirestore db) {
        tickerDB = new TickerDB(context, db);
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
