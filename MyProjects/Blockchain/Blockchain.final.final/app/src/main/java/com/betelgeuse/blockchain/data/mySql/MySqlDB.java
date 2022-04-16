package com.betelgeuse.blockchain.data.mySql;

import android.content.Context;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.data.IDatabase;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListListener;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListener;
import com.betelgeuse.blockchain.data.dataListener.UserOptionDTOListListener;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;

public class MySqlDB implements IDatabase
{

    @Override
    public void readTickersOfDate (String date, @Nullable TickerDTOListListener listener) {

    }

    @Override
    public void readTickersFromDateToNow (String date, @Nullable TickerDTOListListener listener) {

    }

    @Override
    public void readTickerOfDate (String toCurrency, String dateValue, @Nullable TickerDTOListener listener) {

    }

    @Override
    public void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerDTOListListener listener) {

    }

    @Override
    public void readUserOptionsByEmail (String email, UserOptionDTOListListener listener) {

    }

    @Override
    public void createUserOption (UserOptionDTO userOption) {

    }
}
