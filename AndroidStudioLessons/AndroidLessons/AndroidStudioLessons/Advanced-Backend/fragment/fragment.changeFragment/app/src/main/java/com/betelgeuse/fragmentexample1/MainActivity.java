package com.betelgeuse.fragmentexample1;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
// import androidx.fragment.app.Fragment;
// import android.app.Fragment;
// import android.app.FragmentManager;
// import android.app.FragmentTransaction;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Count count = new Count(0);
    int count_int=0;
    Integer count_INTEGER = new Integer(0);
    Button buttona ;
    Button buttonb;

    public void count(){
        Log.e("callFragment", "count_INTEGER_CLASS:" +count_INTEGER.toString() );
        count_INTEGER= count_INTEGER+1;
        Log.e("callFragment", "count.count: "+count.count );
        count.count=count.count+1;
        Log.e("callFragment", "count_int_primitive: "+count_int );
        count_int=count_int+1;
    }
    public  void  callFragment(Fragment fragment){
        count();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutForFragments,fragment);
        fragmentTransaction.commit();

    }
    @Override
    protected void onCreate (Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttona = findViewById(R.id.buttonFragmentA);
        buttonb = findViewById(R.id.buttonFragmentB);


        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                FragmentA fragmentA = FragmentA.newInstance();
                callFragment(fragmentA);
            }
        });

        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                FragmentB fragmentB = FragmentB.newInstance("","");
                callFragment(fragmentB);
            }
        });


    }



}