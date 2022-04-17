package com.betelgeuse.blockchain.userinterface.information;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.betelgeuse.blockchain.H;
import com.betelgeuse.blockchain.core.controller.InformationCoreController;
import com.betelgeuse.blockchain.userinterface.Model.InformationModel;
import com.betelgeuse.blockchain.userinterface.Model.InformationModelListListener;

import java.util.List;

public class InformationController  extends ViewModel {
    MutableLiveData<List<InformationModel>> infos;
    InformationController(@Nullable Context context) {
        informationCoreController = new InformationCoreController(context);
        if (infos==null) infos = new MutableLiveData<>();
    }
    LiveData<InformationModel> modelLiveData;
    InformationCoreController  informationCoreController;
    public  void getData(String email){
        informationCoreController.getData(email, infoList -> infos.setValue(infoList));
    }
}
