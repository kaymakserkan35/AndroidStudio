package com.betelgeuse.dragrecycleritemtoanothertabview;

import android.content.ClipData;
import android.content.ClipDescription;
import android.nfc.Tag;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.betelgeuse.dragrecycleritemtoanothertabview.ui.main.SectionsPagerAdapter;
import com.betelgeuse.dragrecycleritemtoanothertabview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnDragListener {

    private        ActivityMainBinding binding;
    private static String              TAB = "TAB";

    private void setCustomViewToTabs (ViewPager viewPager, SectionsPagerAdapter viewPagerAdapter) {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        int length = tabLayout.getTabCount();
        for (int i = 0; i < length; i++) {
            tabLayout.getTabAt(i).setCustomView(viewPagerAdapter.getTabView(i));
        }
    }

    private void setOnDragListenerToTabsView (@NonNull TabLayout tabLayout, View.OnDragListener onDragListener) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.getCustomView().setId(i);
            tab.getCustomView().setTag(TAB + String.valueOf(i));
            tab.getCustomView().setOnDragListener(onDragListener);
        }
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter =
                new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.removeAllTabs();
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(viewPager);
        setCustomViewToTabs(viewPager, sectionsPagerAdapter);
        // tabs.setOnDragListener(this);
        setOnDragListenerToTabsView(tabs, MainActivity.this);
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onDrag (View view, DragEvent event) {
        View draggableObj = (View) event.getLocalState();
        LinearLayout layout = ((LinearLayout) view);
        Log.e("event", String.valueOf(  event.getAction()));
        Log.e("draggableObj", draggableObj.getTag().toString() );
        Log.e("view", view.getTag().toString() );

        if (view.getTag().toString().contains((CharSequence) MainActivity.TAB)) {
            Log.e("TAG", "if (view.getTag().toString().contains((CharSequence) MainActivity.TAB))" );
            if (event.getAction() == DragEvent.ACTION_DRAG_LOCATION) {
                Log.e("TAG", "if (event.getAction() == DragEvent.ACTION_DRAG_LOCATION) " );
                int position = view.getId();
                binding.tabs.selectTab(binding.tabs.getTabAt(position), true);
                sleep(100);
            }
        }

        return true;
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}