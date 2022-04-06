package com.betelgeuse.blockchainindicators.indicators;

import java.util.List;

public class ExponentialMovingAverage extends  Average {
    private   double   alfa = 2/(period.getCode()+1);
    public ExponentialMovingAverage (List<Data> dataList, Period period) {
        super(dataList, period);
    }

    @Override
    public void analyze ( ) {
        sortDataByCalendar(Sort.ASCENDING);
        Data previousData = null;
        Data currentData = null;
        for (int i = 0; i <dataList.size(); i++) {
            double ema = 0;
            currentData = dataList.get(i);
            if (previousData == null) {
                ema = 0 + alfa*(currentData.price - 0);
            }
            if (previousData!= null) {
                ema = previousData.exponentialMovingAverage + alfa*(currentData.price-previousData.exponentialMovingAverage);
            }
            currentData.exponentialMovingAverage = ema;
            previousData = currentData;
        }
        sortDataByCalendar(Sort.DESCENDING);
    }
}
