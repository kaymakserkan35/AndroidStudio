package com.betelgeuse.blockchain.service.blockchainservice.manager;

import android.content.Context;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.service.CallBackClasses.CallBackPriceEvent;
import com.betelgeuse.blockchain.service.blockchainservice.BlockchainService;

public class BlockchainServiceManager implements IService {
    public String  baseURL = "https://api.blockchain.com/v3/exchange/";
    Context           context;
    BlockchainService blockchainService;

    public BlockchainServiceManager (Context context) {
        this.context = context;
        blockchainService = new BlockchainService(context,baseURL,null);

    }
    public void  getTickers(@Nullable CallBackPriceEvent callBackPriceEvent){
        blockchainService.getTickers(callBackPriceEvent);
    }

}
