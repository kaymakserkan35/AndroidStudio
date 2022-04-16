package com.betelgeuse.apihttprequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;


public class Service {

    private   String Tickers = "https://api.blockchain.com/v3/exchange/tickers";
    private  static Object lockObj = new Object();
    private  static  Service instance=null;
    public  static  Service getInstance(){
        if (instance==null){
            synchronized (lockObj){
                instance = new Service();
            }
        }
        return instance;
    }

    public  ServiceResult getTickers()  {
        ServiceResult serviceResult = ServiceResult.getInstance();
        Thread thread = new Thread(){
            @Override
            public void run ( ) {
                try {
                    BufferedReader bufferedReader = null;
                    URL url = new URL(Tickers);
                    HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                    StringBuilder stringBuilder = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String row ;
                    while ((row=bufferedReader.readLine())!=null){
                        stringBuilder.append(row+"\n");
                    }
                      stringBuilder.toString();

                    serviceResult.isSuccess=true;
                    serviceResult.result=stringBuilder.toString();
                }
                catch (Exception e){
                    serviceResult.isSuccess=false;
                    serviceResult.result=e.getLocalizedMessage();
                }
            }
        };
        thread.start();
        return  serviceResult;
    }

}
