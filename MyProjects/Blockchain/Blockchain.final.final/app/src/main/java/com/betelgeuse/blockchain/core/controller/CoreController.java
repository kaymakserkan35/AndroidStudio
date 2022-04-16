package com.betelgeuse.blockchain.core.controller;


import android.content.Context;

import com.betelgeuse.blockchain.core.indicator.Data;
import com.betelgeuse.blockchain.core.indicator.Indicator;
import com.betelgeuse.blockchain.core.indicator.RelativeStrengthIndex;
import com.betelgeuse.blockchain.data.cache.Cache;
import com.betelgeuse.blockchain.data.dataListener.UserOptionDTOListListener;
import com.betelgeuse.blockchain.data.dto.TickerDTO;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;
import com.betelgeuse.blockchain.data.firebase.FirebaseDB;
import com.betelgeuse.blockchain.data.output.DataManager;
import com.betelgeuse.blockchain.userinterface.Model.InformationModel;
import com.betelgeuse.blockchain.userinterface.Model.InformationModelListListener;
import com.betelgeuse.blockchain.userinterface.Model.UserOptionModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CoreController {

    public CoreController (Context context){
        this.database = new DataManager(new FirebaseDB(FirebaseFirestore.getInstance()),new Cache(context));
    }
    DataManager         database;
    private void readUserOptionsByEmail (String email, UserOptionDTOListListener listener) {
        database.readUserOptionsByEmail(email, listener);
    }

    public void getData (String email, InformationModelListListener listener) {
        readUserOptionsByEmail(email, (List<UserOptionDTO> userOptionDTOList) -> {
            int count = 0; int size = 1;
            for (UserOptionDTO userOptionDTO : userOptionDTOList) {
                count = count +1;
                int finalCount = count;
                database.readTickerHistory(userOptionDTO.fromCurrency, userOptionDTO.toCurrency, userOptionDTO.history, (List<TickerDTO> tickerList) -> {
                    List<Data> dataList = Data.convertTickerListToDataList(tickerList);
                    Indicator indicator = new RelativeStrengthIndex(dataList, userOptionDTO.period);
                    indicator.analyze();
                    if (finalCount == size) listener.onSuccess(InformationModel.convertDataListToInfoModelList(dataList));
                });
            }
        });

    }

    public void createUserOption (UserOptionModel userOptionModel) {
        UserOptionDTO userOptionDTO = new UserOptionDTO(userOptionModel);
        database.createUserOption(userOptionDTO);
    }
}
