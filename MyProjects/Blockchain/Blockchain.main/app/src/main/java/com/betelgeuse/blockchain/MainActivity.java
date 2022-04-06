package com.betelgeuse.blockchain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.betelgeuse.blockchain.data.CallBackClasses.TickerListListener;
import com.betelgeuse.blockchain.data.CallBackClasses.TickerListener;
import com.betelgeuse.blockchain.data.DataManager;
import com.betelgeuse.blockchain.data.firebase.FirebaseDB;
import com.betelgeuse.blockchain.data.firebase.TickerDB;
import com.betelgeuse.blockchain.dto.PriceEventDTO;
import com.betelgeuse.blockchain.dto.TickerDTO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
// ctrl mouse click  --> property nin nerede tanımlı nerede kulllanılmış bilgilerini sunar!!

    Button testingButton;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testingButton = findViewById(R.id.testingButton);
        testingButton.setOnClickListener(v -> {
            TickerDB db = new TickerDB(this, FirebaseFirestore.getInstance());
            /*-----------------------------------------------------------------------*/
            //db.readTickerFromDateToNow("BTC","2021-12-26",null);
            /*------------------------------------------------------------*/
             //db.readTickerOfDate("BSD","2022-03-20",null);
            /*------------------------------------------------------------*/
            //db.readTickersOfDate("2022-03-22", null);
            /*------------------------------------------------------------*/
            //db.readTickerFromDateToNow();
            /*------------------------------------------------------------*/
            //db.readTickerOfDateTESTİNG();
            /*---------------------------------------------------------------*/
            //db.readTickerFromDateToNowTESTİNG();
            /*-------------------------------------------------------------------*/
            db.readTickerFromDateToNow("BTC","2021-06-13",null);

        });
    }

}