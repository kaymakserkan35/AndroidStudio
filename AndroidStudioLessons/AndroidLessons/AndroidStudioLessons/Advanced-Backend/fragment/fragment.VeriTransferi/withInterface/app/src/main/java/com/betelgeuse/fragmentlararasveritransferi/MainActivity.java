package com.betelgeuse.fragmentlararasveritransferi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity  implements DataTransferInterface   {


    private   void   createFragments(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frameLayoutForFragmentA,new FragmentA());
        ft.add(R.id.frameLayoutForFragmentB,new FragmentB());
        ft.commit();
    }
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        Log.e("TAG", "onCreate: "+savedInstanceState.toString()+"is null" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFragments();
    }
    @Override
    public void transferDataTypeString ( ) {

    }

    @Override
    public void transferDataObjectTo_B (DataObject dataObject) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentB fragmentB =(FragmentB) fragmentManager.findFragmentById(R.id.frameLayoutForFragmentB);
        fragmentB.textViewB.setText(dataObject.Name);
    }


}