package com.betelgeuse.blockchain.data.dataListener;

import com.betelgeuse.blockchain.data.dto.TickerDTO;

public interface TickerListener {
    public  void onSuccess(TickerDTO ticker);
}
