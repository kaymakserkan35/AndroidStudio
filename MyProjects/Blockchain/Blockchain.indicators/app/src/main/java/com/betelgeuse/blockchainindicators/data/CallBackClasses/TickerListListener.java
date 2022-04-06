package com.betelgeuse.blockchainindicators.data.CallBackClasses;

import com.betelgeuse.blockchainindicators.data.dto.TickerDTO;
import java.util.List;

public interface TickerListListener {
    public  void onSuccess(List<TickerDTO> tickerList);
}
