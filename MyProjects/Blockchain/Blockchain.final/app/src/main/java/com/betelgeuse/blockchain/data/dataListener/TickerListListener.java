package com.betelgeuse.blockchain.data.dataListener;

import com.betelgeuse.blockchain.data.dto.TickerDTO;

import java.util.List;

public interface TickerListListener {
    public  void onSuccess(List<TickerDTO> tickerList);
}
