package com.betelgeuse.blockchainindicators.data.CallBackClasses;

import com.betelgeuse.blockchainindicators.data.dto.TickerDTO;

public interface TickerListener {
    public  void onSuccess(TickerDTO ticker);
}
