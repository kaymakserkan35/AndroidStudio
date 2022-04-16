package com.betelgeuse.optionmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,"İtem id: "+item.getItemId(),Toast.LENGTH_LONG);
        Toast temp=Toast.makeText(this,"Toast Gösterildi",Toast.LENGTH_LONG);
        switch (item.getItemId()){
            case R.id.option_1:
                temp.show();
                break;
            case R.id.option_2:
                ProgressDialog dial=new ProgressDialog(MainActivity.this);
                dial.setMax(100);
                dial.show();
                break;
            case R.id.option_3:
                Log.e("Some","Some Logging Work as proper");
                break;
        }
        return true;
    }
}