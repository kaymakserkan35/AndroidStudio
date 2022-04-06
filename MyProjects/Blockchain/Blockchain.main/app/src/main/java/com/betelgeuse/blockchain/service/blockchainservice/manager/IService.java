package com.betelgeuse.blockchain.service.blockchainservice.manager;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.service.CallBackClasses.CallBackPriceEvent;

public interface IService {
    void getTickers(@Nullable CallBackPriceEvent priceEventDTOCallBack);
}
