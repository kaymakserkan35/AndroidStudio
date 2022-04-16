package com.betelgeuse.blockchain.core.controller;


import android.content.Context;

import com.betelgeuse.blockchain.core.indicator.Data;
import com.betelgeuse.blockchain.core.indicator.Indicator;
import com.betelgeuse.blockchain.core.indicator.RelativeStrengthIndex;
import com.betelgeuse.blockchain.data.dataListener.TickerListListener;
import com.betelgeuse.blockchain.data.dto.TickerDTO;
import com.betelgeuse.blockchain.data.firebase.FirebaseDB;
import com.betelgeuse.blockchain.data.output.DataManager;
import com.betelgeuse.blockchain.userinterface.Option;
import com.betelgeuse.blockchain.userinterface.information.InformationModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class BaseController {
    public BaseController (List<Option> options) {
        this.options = options;
    }

    List<Option> options;
    DataManager  database = new DataManager(new FirebaseDB(FirebaseFirestore.getInstance()));
    public  List<InformationModel> getData(List<Option> options){
            List<InformationModel> infos = new ArrayList<>();
        for (Option option: options) {
            database.readTickerHistory(option.fromCurrency, option.toCurrency, option.history, new TickerListListener() {
                @Override
                public void onSuccess (List<TickerDTO> tickerList) {
                    List<Data> dataList = Data.convertTickerListToDataList(tickerList);
                    Indicator indicator = new RelativeStrengthIndex(dataList,option.period);
                  infos.addAll(  InformationModel.convertDataListToInfoModelList(dataList));
                }
            });
        }
        return  infos;
    }
}
