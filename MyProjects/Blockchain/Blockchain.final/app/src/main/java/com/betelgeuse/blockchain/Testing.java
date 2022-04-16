package com.betelgeuse.blockchain;

import android.content.Context;

import com.betelgeuse.blockchain.core.indicator.BollingerBand;
import com.betelgeuse.blockchain.core.indicator.Data;
import com.betelgeuse.blockchain.core.indicator.Period;
import com.betelgeuse.blockchain.core.indicator.RelativeStrengthIndex;
import com.betelgeuse.blockchain.core.indicator.SimpleMovingAverage;
import com.betelgeuse.blockchain.core.indicator.Sort;
import com.betelgeuse.blockchain.data.firebase.TickerDB;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Testing {
    Context context;
    public Testing (Context context) {
      this.context = context;
      tickerDB = new TickerDB(FirebaseFirestore.getInstance());
    }

    TickerDB tickerDB ;

    public void RelativeStrengthIndexTESTİNG (List<Data> dataList,Period period) {
        RelativeStrengthIndex relativeStrengthIndex = new RelativeStrengthIndex(dataList,period);
        relativeStrengthIndex.setAverage(SimpleMovingAverage.class);
        relativeStrengthIndex.analyze();
    }
    public void BollingerBandsTESTİNG (List<Data> dataList,Period period) {
        BollingerBand bollingerBand = new BollingerBand(dataList, period);
        bollingerBand.analyze();
        bollingerBand.sortDataListByCalendar(Sort.ASCENDING);
        Data data = new Data(null);
    }

}
