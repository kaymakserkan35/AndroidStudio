package com.betelgeuse.blockchain.service.CallBackClasses;

import com.betelgeuse.blockchain.dto.PriceEventDTO;

import java.util.List;

public interface CallBackPriceEvent {
    void onSuccess (PriceEventDTO priceEvent);
    void onSuccess (List<PriceEventDTO> priceEventList);
}
