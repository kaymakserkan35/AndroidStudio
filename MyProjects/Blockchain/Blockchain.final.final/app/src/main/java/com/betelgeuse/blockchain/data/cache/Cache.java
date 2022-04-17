package com.betelgeuse.blockchain.data.cache;

import android.content.Context;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.data.dto.TickerDTO;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;

import java.util.List;

public class Cache implements  ICache {

    public Cache(@Nullable Context context){

        optionCache = new OptionCache(context);
        tickerCache = new TickerCache(context);
    }

    IOptionCache optionCache ;
    ITickerCache tickerCache ;

    @Override
    public boolean createUserOption (UserOptionDTO userOptionDTO) {
       return  optionCache.createUserOption(userOptionDTO);
    }

    @Override
    public UserOptionDTO readUserOption (String email) {
        return null;
    }

    @Override
    public List<UserOptionDTO> readUserOptionsAll ( ) {
        return null;
    }

    @Override
    public List<UserOptionDTO> readUserOptionsAllByEmail (String email) {
         return optionCache.readUserOptionsAllByEmail(email);
    }

    @Override
    public boolean deleteUserOption (UserOptionDTO userOptionDTO) {
        return false;
    }

    @Override
    public boolean updateUserOption (UserOptionDTO userOptionDTO) {
        return false;
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
    public boolean updateTicker (TickerDTO ticker) {
        return false;
    }

    @Override
    public boolean deleteTicker (TickerDTO ticker) {
        return false;
    }
}
