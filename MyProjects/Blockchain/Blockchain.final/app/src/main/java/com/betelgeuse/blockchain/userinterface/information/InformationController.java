package com.betelgeuse.blockchain.userinterface.information;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.betelgeuse.blockchain.core.controller.BaseController;

import java.util.List;

public class InformationController  extends ViewModel {
    LiveData<InformationModel> modelLiveData;
    BaseController baseController = new BaseController(null);
    public  void  getData(){
       List<InformationModel> infos = baseController.getData(null);
    }
}
