package com.betelgeuse.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqLiteContext extends SQLiteOpenHelper {
    String tableName;
    int    databaseVersion;

    public SqLiteContext (@Nullable Context context, Class entityOfTable) {
        super(context, entityOfTable.getSimpleName(), null, 1);
        this.tableName = entityOfTable.getSimpleName() ;
    }

    public SqLiteContext (@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS" +" "+ tableName + "(\n" +
                OptionEnt.email + "  varchar(25) PRIMARY KEY,             \n" +
                OptionEnt.symbol + " varchar(25) NOT NULL,                \n" +
                OptionEnt.period + " varchar(25) NOT NULL                 \n" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS ";
        db.execSQL(sql +" "+ tableName);
        onCreate(db);
    }
}
