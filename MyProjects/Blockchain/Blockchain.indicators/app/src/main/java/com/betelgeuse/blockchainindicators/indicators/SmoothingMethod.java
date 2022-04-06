package com.betelgeuse.blockchainindicators.indicators;

import android.util.Log;

import com.betelgeuse.blockchainindicators.H;

import java.util.Iterator;
import java.util.List;

public class SmoothingMethod extends Average {
    public SmoothingMethod (List<Data> dataList, Period period) {
        this.period = period;
        this.dataList = dataList;
    }

    @Override
    public void analyze ( ) {
        this.sortDataByCalendar(Sort.ASCENDING);
        int count = 1;
        Data currentData = null;
        Data previousData = null;
        Iterator<Data> dataIterator = this.dataList.iterator();
        while (dataIterator.hasNext()) {
            currentData = dataIterator.next();
            if (count < period.getCode()) {
                if (previousData == null) {
                    currentData.averagePrice = currentData.price;
                    currentData.averageUpward = currentData.changeUpward;
                    currentData.averageDownward = currentData.changeDownward;
                }
                if (previousData != null) {
                    currentData.averagePrice =
                            ((previousData.averagePrice * count) + currentData.price) / (count + 1);
                    currentData.averageUpward =
                            ((previousData.averageUpward * count) + currentData.changeUpward) / (count + 1);
                    currentData.averageDownward =
                            ((previousData.averageDownward * count) + currentData.changeDownward) / (count + 1);
                }
                count = count + 1;
                previousData = currentData;

            } else {
                currentData.averagePrice =
                        ((previousData.averagePrice * (period.getCode() - 1)) + currentData.price) / (period.getCode());

                currentData.averageUpward =
                        ((previousData.averageUpward * (period.getCode() - 1)) + currentData.changeUpward) / (period.getCode());
                currentData.averageDownward =
                        ((previousData.averageDownward * (period.getCode() - 1)) + currentData.changeDownward) / (period.getCode());
            }
            H.debugLog(this.getClass().getSimpleName(), "SmoothingMethod", currentData.symbol + "-" + currentData.calendar + " --> " + " averageDownward : " + currentData.averageDownward + " ,\" averageUpward : \" " + currentData.averageUpward);
        }

    }


}
