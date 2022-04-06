package com.betelgeuse.blockchainindicators.indicators;

import com.betelgeuse.blockchainindicators.data.dto.TickerDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public Data (TickerDTO tickerDTO) {
        this.calendar = tickerDTO.getCalendar();
        this.symbol = tickerDTO.getSymbol();
        this.price = tickerDTO.getPrice();
        this.open = tickerDTO.getOpen();
        this.high = tickerDTO.getHigh();
        this.low = tickerDTO.getLow();
        this.candle = tickerDTO.getCandle();
        setChances();
    }

    public double candle;
    public String calendar;
    public double high;
    public double low;
    public double price;
    public String symbol;
    public double timeStamp;
    public double open;
    public double changeUpward;
    public double changeDownward;
    public double averageUpward;
    public double averageDownward;
    public double averagePrice;
    public double middleBand;
    public double upperBand;
    public double lowerBand;
    public double rs;
    public double rsi;
    public  double exponentialMovingAverage;

    public Data ( ) {

    }

    private Data setChances ( ) {
        double change = this.price - this.open;
        if (change > 0) {
            this.changeUpward = Math.abs(change);
            this.changeDownward = 0;
        }
        if (change < 0) {
            this.changeDownward = Math.abs(change);
            this.changeUpward = 0;
        }
        if (change == 0) {
            this.changeUpward = 0;
            this.changeDownward = 0;
        }
        return this;
    }

    public static List<Data> convertTickerListToDataList (List<TickerDTO> tickerlist) {

        List<Data> dataList = new ArrayList<>();
        for (TickerDTO ticker : tickerlist) {

            Data data = new Data();
            data.symbol = ticker.getSymbol();
            data.price = ticker.getPrice();
            data.calendar = ticker.getCalendar();
            data.timeStamp = ticker.getTimeStamp();
            data.open = ticker.getOpen();
            data.high = ticker.getHigh();
            data.low = ticker.getLow();
            data.candle = ticker.getCandle();
            double change = ticker.getPrice() - ticker.getOpen();
            if (change > 0) {
                data.changeUpward = Math.abs(change);
                data.changeDownward = 0;
            }
            if (change < 0) {
                data.changeDownward = Math.abs(change);
                data.changeUpward = 0;
            }
            if (change == 0) {
                data.changeUpward = 0;
                data.changeDownward = 0;
            }
            dataList.add(data);
        }

        return dataList;
    }

}
