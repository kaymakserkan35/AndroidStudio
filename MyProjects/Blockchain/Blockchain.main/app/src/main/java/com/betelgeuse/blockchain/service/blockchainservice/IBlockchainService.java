package com.betelgeuse.blockchain.service.blockchainservice;

import com.betelgeuse.blockchain.dto.PriceEventDTO;
import com.betelgeuse.blockchain.dto.SymbolStatusDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IBlockchainService {

    @GET("tickers")
    Call<List<PriceEventDTO>> getTickers();
    @GET("symbols")
    Call<List<SymbolStatusDTO>> getSymbols();
    @GET("symbols/{symbol}")
    Call<SymbolStatusDTO> getSymbol(@Path("symbol")String symbol);
}
