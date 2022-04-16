package com.betelgeuse.asynctask;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MyAsyncTask extends AsyncTask<BackgroundObject, OnProgressObject, OnPostObject> {
    public MyAsyncTask (MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onCancelled (OnPostObject onPostObject) {
        super.onCancelled(onPostObject);
        activity.textView.setText("işlem iptal edildi");
    }

    private void progressBarSetMin (ProgressBar progressBar, int minValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            progressBar.setMin(minValue);
        } else {
            Log.e("progressBarSetMin", String.valueOf(Build.VERSION.SDK_INT));
            Log.e("progressBarSetMin", "min android sdk required");
        }
    }

    MainActivity activity;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPreExecute ( ) {
        super.onPreExecute();
        activity.progressBar.setMax(100);
        // activity.progressBar.setMin(0);
        progressBarSetMin(activity.progressBar, 0);
        activity.progressBar.setProgress(0, true);
        activity.textView.setText("İşlem Başlıyor...");
    }

    @Override
    protected void onPostExecute (OnPostObject onPostObject) {
        super.onPostExecute(onPostObject);
        activity.textView.setText(onPostObject.ResultMessage);
    }

    @Override
    protected void onProgressUpdate (OnProgressObject... values) {
        super.onProgressUpdate(values);
        activity.progressBar.setProgress(values[0].sayac);
        activity.textView.setText(" İşlemin durumu % " + String.valueOf(values[0].sayac));
    }

    @Override
    protected OnPostObject doInBackground (BackgroundObject... backgroundObjects) {
        OnProgressObject onProgressObject = new OnProgressObject();
        int sayac = 0;
        try {

            while (sayac < 100) {
                Thread.sleep(500);
                sayac++;

                onProgressObject.sayac = sayac;
                publishProgress(onProgressObject);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        OnPostObject onPostObject = new OnPostObject();
        onPostObject.ResultMessage = "Process finished Successfully !";
        return onPostObject;
    }
}
