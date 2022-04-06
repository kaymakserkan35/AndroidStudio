package com.betelgeuse.blockchainindicators.indicators;

import android.os.Build;
import android.util.Log;

import com.betelgeuse.blockchainindicators.H;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class SimpleMovingAverage extends Average {
    public SimpleMovingAverage (List<Data> dataList, Period period) {
        super(dataList, period);
    }

    @Override
    public void analyze ( ) {
        sortDataByCalendar(Sort.DESCENDING);
        for (int i = 0; i < dataList.size()- period.getCode(); i++) {
            if ((dataList.size() - (i)) < period.getCode()) break;
            List<Data> _datalist = null;
            try {
                _datalist = dataList.subList(i, period.getCode() + i);
            } catch (Exception e) {
                H.errorLog(this.getClass().getSimpleName(), "analyze", e.getLocalizedMessage().toUpperCase(Locale.ROOT));
            }
            averageFirstDataInArray(_datalist, this.period);
        }

    }

    private void averageFirstDataInArray (List<Data> dataList, Period period) {
        if (dataList.size() < period.getCode()) {
            return;
        }
        double sumPrice = 0, averagePrice;
        double sumDownward = 0, averageDownward;
        double sumUpward = 0, averageUpward;
        for (int i = 0; i < period.getCode(); i++) {
            sumPrice = sumPrice + dataList.get(i).price;
            sumDownward = sumDownward + dataList.get(i).changeDownward;
            sumUpward = sumUpward + dataList.get(i).changeUpward;
        }
        averagePrice = sumPrice / period.getCode();
        averageDownward = sumDownward / period.getCode();
        averageUpward = sumUpward / period.getCode();

        dataList.get(0).averagePrice = averagePrice;
        dataList.get(0).averageUpward = averageUpward;
        dataList.get(0).averageDownward = averageDownward;

    }
}
