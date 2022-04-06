package com.betelgeuse.blockchain.data.CallBackClasses;

import com.betelgeuse.blockchain.dto.TickerDTO;

import java.util.List;

public interface TickerListListener {
    public  void onSuccess(List<TickerDTO> tickerList);
}
