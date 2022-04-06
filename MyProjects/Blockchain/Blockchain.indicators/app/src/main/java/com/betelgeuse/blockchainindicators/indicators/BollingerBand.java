package com.betelgeuse.blockchainindicators.indicators;

import com.betelgeuse.blockchainindicators.H;

import java.util.List;
import java.util.Locale;

public class BollingerBand extends Indicator {
    Average average;

    public BollingerBand (List<Data> dataList, Period period) {
        super(dataList, period);
    }

    public BollingerBand (List<Data> dataList, Period period, Average average) {
        super(dataList, period);
        this.average = average;
    }
    public BollingerBand (List<Data> dataList, Average average) {
        //default olarak period 20 alınır!!
        super(dataList, Period.twentyDay);
        this.average = average;
        average.setData(dataList,Period.twentyDay);
    }

    @Override
    public List<Data> analyze ( ) {
        double sum = 0;
        average.setData(this.dataList, this.period);
        average.analyze();
        sortDataByCalendar(Sort.DESCENDING);
        for (int i = 0; i < dataList.size()- period.getCode(); i++) {
            if ((dataList.size() - (i)) < period.getCode()) break;
            List<Data> _datalist = null;
            try {
                _datalist = dataList.subList(i, period.getCode() + i);
            } catch (Exception e) {
                H.errorLog(this.getClass().getSimpleName(), "analyze", e.getLocalizedMessage().toUpperCase(Locale.ROOT));
            }
            calculateBands(_datalist);
        }
        return dataList;
    }

    private void calculateBands(List<Data> dataList){

        if (dataList.size() < period.getCode()) {
            return;
        }
        double sum = 0;
        for (int i = 0; i < period.getCode(); i++) {
            double average = dataList.get(0).averagePrice;
            Data data = dataList.get(i);
            double sapma = data.price - average;
            double  squareOfDeviation = Math.pow( sapma,2);
           // if (sapma<0) {squareOfDeviation = squareOfDeviation * (-1);} dogru degil
            sum = sum + squareOfDeviation;
        }
        double variance = sum/(period.getCode()-1);
        double standardDeviation = Math.sqrt(variance);
        double middleBand = dataList.get(0).averagePrice;
        double upperBand = dataList.get(0).averagePrice + (2*standardDeviation);
        double lowerBand = dataList.get(0).averagePrice - (2*standardDeviation);

        dataList.get(0).middleBand = middleBand;
        dataList.get(0).lowerBand = lowerBand;
        dataList.get(0).upperBand = upperBand;
    }

}
