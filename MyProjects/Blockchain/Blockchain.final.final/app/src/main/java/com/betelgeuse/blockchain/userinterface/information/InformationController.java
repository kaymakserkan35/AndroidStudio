package com.betelgeuse.blockchain.userinterface.information;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.betelgeuse.blockchain.core.controller.CoreController;
import com.betelgeuse.blockchain.userinterface.Model.InformationModel;
import com.betelgeuse.blockchain.userinterface.Model.InformationModelListListener;

import java.util.List;

public class InformationController  extends ViewModel {
    InformationController(Context context) {
        coreController = new CoreController(context);
    }
    LiveData<InformationModel> modelLiveData;
    CoreController             coreController ;
    public  void getData(String email, InformationModelListListener listener){
        coreController.getData(email,listener);
    }
}
