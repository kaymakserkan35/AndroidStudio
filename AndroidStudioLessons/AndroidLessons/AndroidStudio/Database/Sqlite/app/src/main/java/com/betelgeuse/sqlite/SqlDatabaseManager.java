package com.betelgeuse.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SqlDatabaseManager {
    public SqlDatabaseManager (SqLiteContext context) {
        this.context = context;
    }

    SqLiteContext context;

    public boolean create (String email, String symbol, Period period) {
        SQLiteDatabase writableDatabase = context.getWritableDatabase();
       ContentValues contentValues =  new ContentValues();
       contentValues.put("email",email);
        contentValues.put("symbol",symbol);
        contentValues.put("period", String.valueOf(period));
        long result=  writableDatabase.insert(Option.class.getSimpleName(),null,contentValues);
        writableDatabase.close();
        return result !=-1;
    }
    @SuppressLint("Range")
    public Option read (String  emailAsId){
        Option option = new Option();
        SQLiteDatabase db=context.getWritableDatabase();
        String [] arg={emailAsId+""};
        Cursor cursor = db.rawQuery("SELECT * FROM "+context.tableName+" WHERE email=?",arg);

        String _email = cursor.getString(cursor.getColumnIndex("email"));
        option.setEmail(_email);
        String symbol = cursor.getString(cursor.getColumnIndex("symbol"));
        option.setSymbol(symbol);
        String period = cursor.getString(cursor.getColumnIndex("period"));
        option.setPeriod(Period.valueOf(period));
        return  option;
    }
    @SuppressLint("Range")
    public List<Option> readAll () {
        List<Option> options = new ArrayList<>();
        SQLiteDatabase readableDatabase = context.getReadableDatabase();
        String query = "select * from Option";
        Cursor cursor = readableDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Option option = new Option();
            String _email = cursor.getString(cursor.getColumnIndex("email"));
            option.setEmail(_email);
            String symbol = cursor.getString(cursor.getColumnIndex("symbol"));
            option.setSymbol(symbol);
            String period = cursor.getString(cursor.getColumnIndex("period"));
            option.setPeriod(Period.valueOf(period));
            options.add(option);
        }
        readableDatabase.close();
        return  options;
    }
    public boolean update(String emailAsId, String symbol, Period period){
        SQLiteDatabase db=context.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("symbol",symbol);
        cv.put("period", String.valueOf(period));
        String [] arg={emailAsId+"",symbol+"",String.valueOf(period)};
        int result=db.update(context.tableName,cv,"id=?",arg);
        return result !=0;
    }
    public boolean delete (String emailAsId, String symbol, Period period){
        SQLiteDatabase db=context.getWritableDatabase();
        String [] arg={emailAsId+"",symbol+"",String.valueOf(period)+""};
        int result=db.delete(context.tableName,"email=? AND symbol=? AND period=?",arg);
        return result>0;
    }

    public long countData(){
        SQLiteDatabase db=context.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,context.tableName);
    }
}