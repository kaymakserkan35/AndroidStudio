package com.betelgeuse.blockchain.userinterface.Model;

import com.betelgeuse.blockchain.core.indicator.Data;

import java.util.ArrayList;
import java.util.List;

public class InformationModel {
     //do not hold data

    public  String fromCurrency;
    public  String toCurrency;
    public  InformationModel(Data data){
        this.fromCurrency = data.symbol.split("/")[0];
        this.toCurrency = data.symbol.split("/")[1];
    }

    public  static List<InformationModel> convertDataListToInfoModelList(List<Data> dataList){
        List<InformationModel> informationModelList = new ArrayList<>();
        for (Data data : dataList) {
            InformationModel informationModel = new InformationModel(data);
            informationModelList.add(informationModel);
        }
        return informationModelList;
    }
}
