package com.betelgeuse.blockchain.core.indicator;

import com.betelgeuse.blockchain.data.dto.TickerDTO;

import java.util.ArrayList;
import java.util.List;

public class Data extends AData {
    RsiData           rsiData;
    BollingerBandData bollingerBandData;
    protected Data initRsiData () {
        this.rsiData = new RsiData(this);
        return  this;
    }
    protected Data initBollingerBandData () {
        this.bollingerBandData = new BollingerBandData();
        return  this;
    }
    public Data (TickerDTO tickerDTO) {
        super(tickerDTO);
    }
    public static List<Data> convertTickerListToDataList (List<TickerDTO> tickers) {

        List<Data> dataList = new ArrayList<>();
        for (TickerDTO ticker : tickers) {
            Data data = new Data(ticker);
            dataList.add(data);
        }

        return dataList;
    }
}
