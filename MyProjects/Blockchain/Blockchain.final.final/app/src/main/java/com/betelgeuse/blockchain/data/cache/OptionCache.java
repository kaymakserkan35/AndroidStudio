package com.betelgeuse.blockchain.data.cache;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.core.indicator.Period;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;
import com.firebase.ui.auth.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class OptionCache extends SQLiteOpenHelper implements IOptionCache {
    private String tableName;

    public OptionCache (@Nullable Context context) {
        super(context, UserOptionDTO.class.getSimpleName(), null, 1);
        this.tableName = UserOptionDTO.class.getSimpleName();
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS" + " " + tableName +        "(\n" +
                UserOptionDTO.Email_String + "  varchar(15) PRIMARY KEY,               \n" +
                UserOptionDTO.FromCurrency_String + " varchar(15) NOT NULL,            \n" +
                UserOptionDTO.ToCurrency_String + " varchar(15) NOT NULL,              \n" +
                UserOptionDTO.Period_INT + " INTEGER NOT NULL,                         \n" +
                UserOptionDTO.History_INT + " INTEGER NOT NULL                         \n" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS ";
        db.execSQL(sql + " " + tableName);
        onCreate(db);
    }

    @Override
    public boolean createUserOption (UserOptionDTO userOptionDTO) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserOptionDTO.Email_String, userOptionDTO.email);
        contentValues.put(UserOptionDTO.ToCurrency_String, userOptionDTO.toCurrency);
        contentValues.put(UserOptionDTO.FromCurrency_String, userOptionDTO.fromCurrency);
        contentValues.put(UserOptionDTO.History_INT, String.valueOf(userOptionDTO.history));
        contentValues.put(UserOptionDTO.Period_INT, String.valueOf(userOptionDTO.period));
        long result =
                writableDatabase.insert(UserOptionDTO.class.getSimpleName(), null, contentValues);
        writableDatabase.close();
        return result != -1;
    }

    @Override
    public UserOptionDTO readUserOption (String email) {
        return null;
    }

    @Override
    @SuppressLint("Range")
    public List<UserOptionDTO> readUserOptionsAll ( ) {
        List<UserOptionDTO> options = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String query = " select * from " + tableName;
        Cursor cursor = readableDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String _email = cursor.getString(cursor.getColumnIndex(UserOptionDTO.Email_String));
            String frC = cursor.getString(cursor.getColumnIndex(UserOptionDTO.FromCurrency_String));
            String tC = cursor.getString(cursor.getColumnIndex(UserOptionDTO.ToCurrency_String));
            int his = cursor.getInt((cursor.getColumnIndex(UserOptionDTO.History_INT)));
            int per = cursor.getInt((cursor.getColumnIndex(UserOptionDTO.Period_INT)));
            UserOptionDTO optionDTO = new UserOptionDTO(_email, frC, tC, Period.get(per), his);
            options.add(optionDTO);
        }
        return options;
    }

    @Override  @SuppressLint("Range")
    public List<UserOptionDTO> readUserOptionsAllByEmail (String email) {
        List<UserOptionDTO> options = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String [] arg={email+""};
        String query = "select * from  " + tableName + " WHERE " + UserOptionDTO.Email_String + "=?";
        Cursor cursor = readableDatabase.rawQuery(query, arg);
        while (cursor.moveToNext()) {
            String _email = cursor.getString(cursor.getColumnIndex(UserOptionDTO.Email_String));
            String frC = cursor.getString(cursor.getColumnIndex(UserOptionDTO.FromCurrency_String));
            String tC = cursor.getString(cursor.getColumnIndex(UserOptionDTO.ToCurrency_String));
            int his = cursor.getInt((cursor.getColumnIndex(UserOptionDTO.History_INT)));
            int per = cursor.getInt((cursor.getColumnIndex(UserOptionDTO.Period_INT)));
            UserOptionDTO optionDTO = new UserOptionDTO(_email, frC, tC, Period.get(per), his);
            options.add(optionDTO);
        }
        return options;
    }

    @Override
    public boolean deleteUserOption (UserOptionDTO userOptionDTO) {
        return false;
    }

    @Override
    public boolean updateUserOption (UserOptionDTO userOptionDTO) {
        return false;
    }
}
