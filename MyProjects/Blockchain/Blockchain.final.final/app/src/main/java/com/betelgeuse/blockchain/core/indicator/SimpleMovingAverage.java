package com.betelgeuse.blockchain.core.indicator;

import java.util.List;

public class SimpleMovingAverage extends Average {
    public SimpleMovingAverage (List<Data> dataList, Period period) {
        super(dataList, period);
    }

    @Override
    public List<Data> analyze ( ) {
        return null;
    }
}
