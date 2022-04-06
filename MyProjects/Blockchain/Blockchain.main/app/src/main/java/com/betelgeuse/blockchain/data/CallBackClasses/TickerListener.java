package com.betelgeuse.blockchain.data.CallBackClasses;

import com.betelgeuse.blockchain.dto.TickerDTO;

public interface TickerListener {
    public  void onSuccess(TickerDTO ticker);
}
