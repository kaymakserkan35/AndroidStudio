package com.betelgeuse.blockchain;

import android.content.Context;

import com.betelgeuse.blockchain.core.controller.InformationCoreController;
import com.betelgeuse.blockchain.core.indicator.BollingerBand;
import com.betelgeuse.blockchain.core.indicator.Data;
import com.betelgeuse.blockchain.core.indicator.Period;
import com.betelgeuse.blockchain.core.indicator.RelativeStrengthIndex;
import com.betelgeuse.blockchain.core.indicator.SimpleMovingAverage;
import com.betelgeuse.blockchain.core.indicator.Sort;
import com.betelgeuse.blockchain.data.cache.Cache;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListListener;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListener;
import com.betelgeuse.blockchain.data.dto.TickerDTO;
import com.betelgeuse.blockchain.data.firebase.FirebaseDB;
import com.betelgeuse.blockchain.data.firebase.TickerDB;
import com.betelgeuse.blockchain.data.output.DataManager;
import com.betelgeuse.blockchain.userinterface.Model.UserOptionModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Testing {
    Context context;
    DataManager               dataManager;
    InformationCoreController informationCoreController;
    public Testing (Context context) {
        this.context = context;
        this.dataManager = new DataManager(new FirebaseDB(FirebaseFirestore.getInstance()),new Cache(context));
        this.informationCoreController = new InformationCoreController(context);
        tickerDB = new TickerDB(FirebaseFirestore.getInstance());
    }

    TickerDB tickerDB ;

    public Testing RelativeStrengthIndexTESTİNG (List<Data> dataList,Period period) {
        RelativeStrengthIndex relativeStrengthIndex = new RelativeStrengthIndex(dataList,period);
        relativeStrengthIndex.setAverage(SimpleMovingAverage.class);
        relativeStrengthIndex.analyze();
        return  this;
    }
    public Testing BollingerBandsTESTİNG (List<Data> dataList,Period period) {
        BollingerBand bollingerBand = new BollingerBand(dataList, period);
        bollingerBand.analyze();
        bollingerBand.sortDataListByCalendar(Sort.ASCENDING);
        Data data = new Data(null);
        return  this;
    }

    public Testing seedUserOptions (String email ) {
        UserOptionModel userOptionModel = new UserOptionModel(email,"USD","BTC",Period.fourteenDay,30);
        informationCoreController.createUserOption(userOptionModel);
        return  this;
    }
    public Testing readTickerFromDateToNow(){
        tickerDB.readTickerFromDateToNow("BTC", "2022-04-01", new TickerDTOListListener() {
            @Override
            public void onSuccess (List<TickerDTO> tickerList) {
                H.debugLog(this.getClass().getSimpleName(),String.valueOf(tickerList.size()));
            }
        });
        return  this;
    }
    public Testing readTickerOfDay(){
        tickerDB.readTickerOfDate("BTC", "2022-04-16", new TickerDTOListener() {
            @Override
            public void onSuccess (TickerDTO ticker) {
                H.debugLog(this.getClass().getSimpleName(),"readTickerOfDate",ticker.getSymbol());
            }
        });
        return  this;
    }
}
