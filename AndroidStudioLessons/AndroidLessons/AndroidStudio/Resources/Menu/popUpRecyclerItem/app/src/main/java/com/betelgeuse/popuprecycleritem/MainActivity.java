package com.betelgeuse.popuprecycleritem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     RecyclerView recyclerView= findViewById(R.id.recyclerView);
      ImageButton imageButton   = findViewById(R.id.options);
    }

    public  void  createPopupMenu(Context context, View view){
        PopupMenu popup = new PopupMenu(context, view);
        //inflating menu from xml resource
        popup.inflate(R.menu.menu_option);
        //adding click listener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        //handle menu1 click
                        break;
                    case R.id.menu2:
                        //handle menu2 click
                        break;
                    case R.id.menu3:
                        //handle menu3 click
                        break;
                }
                return false;
            }
        });
        //displaying the popup
        popup.show();
    }
}