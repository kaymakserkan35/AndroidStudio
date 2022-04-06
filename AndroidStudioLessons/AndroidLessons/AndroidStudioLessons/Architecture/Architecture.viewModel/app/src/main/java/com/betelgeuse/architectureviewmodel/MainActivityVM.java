package com.betelgeuse.architectureviewmodel;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.lifecycle.ViewModel;

import java.util.Date;

public class MainActivityVM  extends ViewModel {
    //ViewModel singleton gibi ama değil, sürekli aynı instance korur.
    private String instanceTİME = null;

    public  String  getData(){
        // aynı tarihin döndürülmesi, bu sınıfın nesnesinin ayni olduguun kanitidir!!
        if (instanceTİME== null) {  createData();}
        return  instanceTİME;
    }

    public void createData(){
        SimpleDateFormat formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        }
        Date date = new Date();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            instanceTİME = formatter.format(date);
        }
    }

}
