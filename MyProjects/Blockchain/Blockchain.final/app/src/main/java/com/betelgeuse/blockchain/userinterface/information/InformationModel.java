package com.betelgeuse.blockchain.userinterface.information;

import com.betelgeuse.blockchain.core.indicator.Data;

import java.util.ArrayList;
import java.util.List;

public class InformationModel {
     //do not hold data
    public  InformationModel(Data data){
        //convert data to InfoModel
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
