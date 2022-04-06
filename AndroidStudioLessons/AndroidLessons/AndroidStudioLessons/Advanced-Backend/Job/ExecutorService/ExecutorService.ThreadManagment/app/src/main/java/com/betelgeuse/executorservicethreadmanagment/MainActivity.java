package com.betelgeuse.executorservicethreadmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart ( ) {
        super.onStart();

        try {
            ExecutorService executor =  Executors.newFixedThreadPool(3);

            for (int i = 0; i < 200; i++) {
                Thread thread = new Thread(new MyThread("thread-->"+i,1000));
                executor.execute(thread);
            }
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        *  newFixedThreadPool metodu ile 5 farklı iş parçasının aynı anda çalıştırılabileceği belirtilmiştir.
        *  Daha sonrasında for döngüsü içinde 20 adet Thread tanımlanmasına rağmen executor servisi gelen işleri düzene sokar
        * ve 5 Thread üzerinde işlem gerçekleştirmez. Sonradan eklenen işlemler sıraya (queue) sokulur ve mevcut işlemler bitirildikçe çalıştırılır.
        *  Böylece sistem kaynakları işlem parçaları tarafından kontrolsüzce harcanamaz.
        *  shutdown metodu ise yeni işlem alımını durdurur ve mevcut işlemlerin bitirilmesini sağlar.
        * awaitTermination ise mevcut işlemlerin bitirilmesi için belirli bir süre tanır ve bu sürenin sonunda ExecutorService tamamen kapatılır.
        * */
    }
}