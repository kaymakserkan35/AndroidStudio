package com.betelgeuse.blockchain.data.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.H;
import com.betelgeuse.blockchain.data.ADatabase;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListListener;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListener;
import com.betelgeuse.blockchain.data.ITickerDB;
import com.betelgeuse.blockchain.data.dto.TickerDTO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TickerDB extends ADatabase implements ITickerDB {
    private FirebaseFirestore   db;
    private CollectionReference tickerCollection;

    public TickerDB (FirebaseFirestore db) {
        super();
        this.db = db;
        tickerCollection = db.collection(TickerDTO.class.getSimpleName());
    }

    @Override  // does work...
    public void readTickersOfDate (String date, @Nullable TickerDTOListListener listener) {
        Date dateObj = parseStringDateToDateObject(date);
        String year = String.valueOf(dateObj.getYear());
        H.debugLog(this.getClass().getSimpleName(), "readTickersOfDate", year);
        db.collectionGroup(year).whereEqualTo("calendar", date).get()
                .addOnSuccessListener((QuerySnapshot queryDocumentSnapshots) -> {
                    List<TickerDTO> tickerDTOList = new ArrayList<>();
                    //H.debugLog(this.getClass().getSimpleName(), "readTickersOfDate", "success!");
                    for (QueryDocumentSnapshot snap : queryDocumentSnapshots) {
                        tickerDTOList.add(snap.toObject(TickerDTO.class));
                        H.debugLog(this.getClass().getSimpleName(), "readTickersOfDate", snap.getData().toString());
                    }
                    if (listener != null) {
                        // H.debugLog(this.getClass().getSimpleName(), "readTickersOfDate", "tickerList size : " + String.valueOf(tickerDTOList.size()));
                        listener.onSuccess(tickerDTOList);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure (@NonNull Exception e) {
                        H.errorLog(this.getClass().getSimpleName(), "readTickersOfDate", e.getLocalizedMessage());
                    }
                });
    }

    @Override // does work...
    public void readTickersFromDateToNow (String date, @Nullable TickerDTOListListener listener) {
        Date dateObj = parseStringDateToDateObject(date);
        String year = String.valueOf(dateObj.getYear());
        // collectionGroup için path index...
        db.collectionGroup(year).whereGreaterThan("calendar", date).get()
                .addOnSuccessListener((QuerySnapshot queryDocumentSnapshots) -> {
                            List<TickerDTO> tickerList = null;
                            H.debugLog(this.getClass().getSimpleName(), "readTickersFromDateToNow", "success!");
                            for (QueryDocumentSnapshot snap : queryDocumentSnapshots) {
                                tickerList.add(snap.toObject(TickerDTO.class));
                                H.debugLog(this.getClass().getSimpleName(), "readTickersFromDateToNow", snap.getData().toString());
                            }
                            if (listener != null) {
                                listener.onSuccess(tickerList);
                            }
                        }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure (@NonNull Exception e) {
                        H.errorLog(this.getClass().getSimpleName(), "readTickersFromDateToNow", e.getLocalizedMessage());
                    }
                });
    }

    @Override // does work...
    public void readTickerOfDate (String toCurrency, String dateValue, @Nullable TickerDTOListener listener) {
        Date dateObj = parseStringDateToDateObject(dateValue);
        String tickerId = "USD" + "-" + toCurrency;
        String year = String.valueOf(dateObj.getYear());
        DocumentReference docRef =
                tickerCollection.document(tickerId).collection(year).document(dateValue);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete (@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        TickerDTO tickerDTO = document.toObject(TickerDTO.class);
                        if (listener != null) {
                            listener.onSuccess(tickerDTO);
                        }
                        H.debugLog(this.getClass().getSimpleName(), "readTickerOfDate", document.getData().toString());
                    } else {
                        Log.d("readTickerOfDate", "No such document");
                    }
                } else {
                    H.debugLog("readTickerOfDate", "get failed with ", task.getException().getLocalizedMessage());
                }
            }
        });
    }

    @Override // does work...
    public void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerDTOListListener listener) {

        List<TickerDTO> tickerList = new ArrayList<>();
        Date dateObj = parseStringDateToDateObject(fromDate);
        String fromYear = String.valueOf(dateObj.getYear());

        for (int year =
             Integer.valueOf(fromYear); year <= Integer.valueOf(getYearNowOfCurrentTimeZone()); year++) {
            CollectionReference collRef =
                    tickerCollection.document("USD" + "-" + toCurrency).collection(String.valueOf(year));
            collRef.whereGreaterThanOrEqualTo("calendar", fromDate).get().addOnCompleteListener((Task<QuerySnapshot> task) -> {
                List<DocumentSnapshot> dSList = task.getResult().getDocuments();
                for (DocumentSnapshot ds : dSList) {
                    TickerDTO tickerDTO = ds.toObject(TickerDTO.class);
                    tickerList.add(tickerDTO);
                    H.debugLog("TAG", "onComplete: " + tickerDTO.getSymbol() + tickerDTO.getCalendar());
                }
            });

        }

        /*-----------------------------------------------------*/
        if (listener != null) {
            listener.onSuccess(tickerList);
        }

    }

}

