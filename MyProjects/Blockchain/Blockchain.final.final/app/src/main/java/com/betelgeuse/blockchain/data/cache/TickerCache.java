package com.betelgeuse.blockchain.data.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.data.dto.TickerDTO;

import java.util.List;

public class TickerCache extends SQLiteOpenHelper implements  ITickerCache {

    public TickerCache(@Nullable Context context) {
        super(context,TickerDTO.class.getSimpleName(),null,1);
    }
    public TickerCache (@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public boolean createTicker (TickerDTO ticker) {
        return false;
    }
    @Override
    public boolean createTickerList (List<TickerDTO> tickers) {
        return false;
    }

    @Override
    public TickerDTO readTicker (String id) {
        return null;
    }

    @Override
    public List<TickerDTO> readTickersAll ( ) {
        return null;
    }

    @Override
    public List<TickerDTO> readTickersByQuery ( ) {
        return null;
    }

    @Override
    public boolean updateTicker (TickerDTO ticker){
        return false;
    }

    @Override
    public boolean deleteTicker (TickerDTO ticker) {
        return false;
    }
}
