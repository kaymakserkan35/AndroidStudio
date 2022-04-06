package com.betelgeuse.blockchain.service.blockchainservice;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.H;
import com.betelgeuse.blockchain.dto.PriceEventDTO;
import com.betelgeuse.blockchain.dto.SymbolStatusDTO;
import com.betelgeuse.blockchain.service.CallBackClasses.CallBackPriceEvent;
import com.betelgeuse.blockchain.service.blockchainservice.manager.IService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlockchainService extends RetrofitService implements IService {
   private IBlockchainService blockchainService;
    public BlockchainService (Context context, String baseURL, String apiKey) {
        super(context,baseURL,apiKey);
          retrofit = initRetrofit(baseURL);
        blockchainService= retrofit.create(IBlockchainService.class);
    }

    @Override
    public void getTickers (@Nullable CallBackPriceEvent priceEventCallBack) {
        blockchainService.getTickers().enqueue(new Callback<List<PriceEventDTO>>() {
            @Override
            public void onResponse (Call<List<PriceEventDTO>> call, Response<List<PriceEventDTO>> response) {
                if (priceEventCallBack==null) {
                    List<PriceEventDTO> priceEventList = response.body();
                    for (PriceEventDTO priceEvent : priceEventList) {
                       H.debugLog(this.getClass().getSimpleName(),"getTickers",priceEvent.getSymbol());
                    }
                }
                else {
                    priceEventCallBack.onSuccess(response.body());
                }

            }
            @Override
            public void onFailure (@Nullable Call<List<PriceEventDTO>> call, Throwable t) {
                H.errorLog(this.getClass().getSimpleName(),"getSymbols",t.getLocalizedMessage());
            }
        });

    }



}
