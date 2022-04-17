package com.betelgeuse.blockchainindicators.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.betelgeuse.blockchainindicators.H;
import com.betelgeuse.blockchainindicators.data.CallBackClasses.TickerListListener;
import com.betelgeuse.blockchainindicators.data.CallBackClasses.TickerListener;
import com.betelgeuse.blockchainindicators.data.dto.TickerDTO;
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

    public TickerDB (Context context, FirebaseFirestore db) {
        super(context);
        this.db = db;
        tickerCollection = db.collection(TickerDTO.class.getSimpleName());
    }

    @Override  // does work...
    public void readTickersOfDate (String date, @Nullable TickerListListener listener) {
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
    public void readTickersFromDateToNow (String date, @Nullable TickerListListener listener) {
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
    public void readTickerOfDate (String toCurrency, String dateValue, @Nullable TickerListener listener) {
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

    // does work...
    private void readTickerFromDateTo_EndOfTheYear (String fromCurrency, String toCurrency, String fromDate, @Nullable TickerListListener listener) {
        // does work ...
        String tickerId = (fromCurrency + "-" + toCurrency).toUpperCase();
        Date dateObj = parseStringDateToDateObject(fromDate);
        String yearSTRİNG = String.valueOf(dateObj.getYear());

        CollectionReference collRef = tickerCollection.document(tickerId).collection(yearSTRİNG);
        List<TickerDTO> tickerList = new ArrayList<>();
        collRef.whereGreaterThanOrEqualTo("calendar", fromDate).get().addOnCompleteListener((Task<QuerySnapshot> task) -> {
            if (task.isSuccessful()) {
                List<DocumentSnapshot> dSList = task.getResult().getDocuments();

                for (DocumentSnapshot ds : dSList) {
                    TickerDTO tickerDTO = ds.toObject(TickerDTO.class);
                    tickerList.add(tickerDTO);
                    H.debugLog(this.getClass().getSimpleName(), "readTickerInSingleYearToNowTESTİNG: " + tickerDTO.getSymbol() + tickerDTO.getCalendar());
                }
                /*-----------returning tickers-------------------------*/
                if (listener != null) {
                    listener.onSuccess(tickerList);
                }
            } else {
                H.errorLog(this.getClass().getSimpleName(), "readTickerInSingleYearToNowTESTİNG", task.getException().getLocalizedMessage());
            }
        });


    }

    @Override // does  work...
    public void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerListListener listener) {

        Date dateObj = parseStringDateToDateObject(fromDate);
        String fromYear = String.valueOf(dateObj.getYear());
        String toYear = getYearNowOfCurrentTimeZone();
        int fromYearINT = Integer.valueOf(fromYear);
        int toYearINT = Integer.valueOf(toYear);
        List<TickerDTO> returnTickerList = new ArrayList<>();

        do {
            int finalFromYearINT = fromYearINT;
            readTickerFromDateTo_EndOfTheYear("USD", toCurrency, fromDate, tickerList -> {
                returnTickerList.addAll(tickerList);
                if ((finalFromYearINT == toYearINT) && listener != null) {
                    listener.onSuccess(returnTickerList);
                }
            });
            fromYearINT = fromYearINT + 1;
            fromDate = fromYearINT + "-" + "01" + "-" + "01";

        } while (fromYearINT <= toYearINT);


    }

    public TickerDTO convertTicker (TickerDTO fromTicker, TickerDTO toTicker) {

        if (fromTicker.getCalendar() != toTicker.getCalendar()) return null;
        if (fromTicker.getFromCurrency() != toTicker.getFromCurrency()) return null;
        TickerDTO ticker = new TickerDTO();
        ticker.setSymbol(fromTicker.getToCurrency() + "/" + toTicker.getToCurrency());
        double USD_A = ticker.getPrice();
        double USD_B = ticker.getPrice();
        double A_USD = 1 / (USD_A);
        double B_USD = 1 / (USD_B);
        double A_B = A_USD / B_USD;
        ticker.setPrice(A_B);
        ticker.setOpen((1 / fromTicker.getOpen()) / (1 / toTicker.getOpen()));
        ticker.setCandle(ticker.getPrice() - ticker.getOpen());
        ticker.setHigh((1 / fromTicker.getHigh()) / (1 / toTicker.getHigh()));
        ticker.setLow((1 / fromTicker.getLow()) / (1 / toTicker.getLow()));
        ticker.setCalendar(fromTicker.getCalendar());
        ticker.setTimeStamp(fromTicker.getTimeStamp());
        return ticker;

    }

    public TickerDTO reverseTicker (TickerDTO ticker) {
        /*-------------------yeni bir ticker a gerek yok aslinda-----------------------------*/
        TickerDTO reversedTicker = new TickerDTO();
        reversedTicker.setSymbol(ticker.getToCurrency() + "/" + ticker.getFromCurrency());
        reversedTicker.setPrice(1 / (ticker.getPrice()));
        reversedTicker.setLow(1 / (ticker.getLow()));
        reversedTicker.setHigh(1 / (ticker.getHigh()));
        reversedTicker.setOpen(1 / (ticker.getOpen()));
        reversedTicker.setCalendar(ticker.getCalendar());
        reversedTicker.setTimeStamp(ticker.getTimeStamp());
        reversedTicker.setCandle(reversedTicker.getPrice() - reversedTicker.getOpen());
        /*-------------------var olanı geri dondurebiliriz---------------------------------*/
        String symbol = ticker.getSymbol().split("/")[1] + "/" + ticker.getSymbol().split("/")[0];
        ticker.setSymbol(symbol);
        ticker.setPrice(1 / (ticker.getPrice()));
        ticker.setOpen(1 / (ticker.getOpen()));
        ticker.setCandle(reversedTicker.getPrice() - reversedTicker.getOpen());

        ticker.setLow(1 / (ticker.getLow()));
        ticker.setHigh(1 / (ticker.getHigh()));
        ticker.setCalendar(ticker.getCalendar());
        ticker.setTimeStamp(ticker.getTimeStamp());

        return ticker;
    }


    /*-------------------------------TEST METHODS-------------------------------------------------------*/


    public void readTickerFromDateToNowTESTİNG (String toCurrency, String fromDate, @Nullable TickerListListener listener) {

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
                /*---------------------------------------------------*/
                if (listener != null) {
                    listener.onSuccess(tickerList);
                }
            });
        }

        /*-----------------------------------------------------*/

    }

    public void readTickerOfDateTESTİNG ( ) {
        CollectionReference collRef =
                tickerCollection.document("USD" + "-" + "BTC").collection(String.valueOf(2022));
        Task<QuerySnapshot> task = collRef.whereEqualTo("calendar", "2022-03-18").limit(1).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete (@NonNull Task<QuerySnapshot> task) {
                List<DocumentSnapshot> dSList = task.getResult().getDocuments();
                TickerDTO tickerDTO = dSList.get(0).toObject(TickerDTO.class);
                Log.d("TAG", "onComplete: " + tickerDTO.getSymbol() + tickerDTO.getCalendar());

            }
        });
    }

    public void readAdminTESTİNG ( ) {
        CollectionReference collRef = db.collection("user");
        Task<QuerySnapshot> task = collRef.whereEqualTo("role", "admin").limit(1).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete (@NonNull Task<QuerySnapshot> task) {
                List<DocumentSnapshot> dSList = task.getResult().getDocuments();
                Log.d("TAG", "onComplete: " + dSList.get(0).getString("role"));

            }
        });
    }
}

