package com.betelgeuse.blockchain.data.firebase;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.data.dataListener.TickerDTOListListener;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListener;
import com.betelgeuse.blockchain.data.IDatabase;
import com.betelgeuse.blockchain.data.dataListener.UserOptionDTOListListener;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseDB implements IDatabase {


    private TickerDB tickerDB;
    private UserOptionDB userOptionDB;

    public FirebaseDB (FirebaseFirestore db) {
        tickerDB = new TickerDB(db);
    }

    @Override
    public void readTickersOfDate (String date, TickerDTOListListener listener) {
        tickerDB.readTickersOfDate(date, listener);
    }

    @Override
    public void readTickersFromDateToNow (String date, @Nullable TickerDTOListListener listener) {
        tickerDB.readTickersFromDateToNow(date, listener);
    }

    @Override
    public void readTickerOfDate (String toCurrency, String dateValue, @Nullable TickerDTOListener listener) {
        tickerDB.readTickerOfDate(toCurrency, dateValue, listener);
    }

    @Override
    public void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerDTOListListener listener) {
        tickerDB.readTickerFromDateToNow(toCurrency, fromDate, listener);
    }

    @Override
    public void readUserOptionsByEmail (String email, UserOptionDTOListListener listener) {

    }

    @Override
    public void createUserOption (UserOptionDTO userOption) {

    }
}
