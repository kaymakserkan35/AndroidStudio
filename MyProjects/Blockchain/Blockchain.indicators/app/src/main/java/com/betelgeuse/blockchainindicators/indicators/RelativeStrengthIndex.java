package com.betelgeuse.blockchainindicators.indicators;

import android.util.Log;

import java.util.Iterator;
import java.util.List;

public class RelativeStrengthIndex extends Indicator {
    private Average average;

    public RelativeStrengthIndex ( ) {
        super();
    }
    public RelativeStrengthIndex (List<Data> dataList, Period period) {
        super(dataList, period);
    }
    public RelativeStrengthIndex (List<Data> dataList, Period period, Average average) {
        super(dataList, period);
        this.average = average;
    }

    public void setAverageStrategy (Average average) {
        this.average = average;
    }

    @Override
    public List<Data> analyze ( ) {
        average.setData(dataList, this.period).analyze();
        /*----------------------------------------------------*/
        Iterator<Data> dataIterator = dataList.iterator();
        while (dataIterator.hasNext()) {
            Data dataCurrent = dataIterator.next();
            dataCurrent.rs = dataCurrent.averageUpward / dataCurrent.averageDownward;
            dataCurrent.rsi = 100 - (100 * (1 / (1 + (dataCurrent.rs))));
        }
        return dataList;

    }

}
