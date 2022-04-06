package com.betelgeuse.blockchainindicators.indicators;

import java.util.List;

public class MovingAverageConvergenceDivergence  extends  Indicator{
    ExponentialMovingAverage exponentialMovingAverage;
    Period fastPeriod,slowPeriod;
    public MovingAverageConvergenceDivergence (List<Data> dataList, Period signalPeriod,Period fastPeriod,Period slowPeriod,ExponentialMovingAverage exponentialMovingAverage) {
        super(dataList, signalPeriod);
        this.fastPeriod = fastPeriod;
        this.slowPeriod = slowPeriod;
        this.exponentialMovingAverage = exponentialMovingAverage;
    }

    @Override
    public List<Data> analyze ( ) {
        exponentialMovingAverage.setData(dataList,fastPeriod);
        exponentialMovingAverage.analyze();
        exponentialMovingAverage.setData(dataList,period);
        exponentialMovingAverage.analyze();
        exponentialMovingAverage.setData(dataList,slowPeriod);
        exponentialMovingAverage.analyze();

        // data yapısını nasıl ayarlarım data icin bir design pattern ???
        // çünki fastPeriodEMA , slowPeriodEMA, periodEMA yı farlı farklı kayıt etmek istemiyorum.
        // kullan at parametreler cünkü bunlar...
        return null;
    }
}
