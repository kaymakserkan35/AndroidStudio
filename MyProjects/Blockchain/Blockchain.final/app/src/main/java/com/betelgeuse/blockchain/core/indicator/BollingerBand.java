package com.betelgeuse.blockchain.core.indicator;

import com.betelgeuse.blockchain.core.indicator.Data;
import com.betelgeuse.blockchain.core.indicator.Indicator;
import com.betelgeuse.blockchain.core.indicator.Period;

import java.util.List;

public class BollingerBand extends Indicator {
    public BollingerBand (List<Data> dataList, Period period) {
        super(dataList, period);
    }

    @Override
    public List<Data> analyze ( ) {
        return null;
    }

}
