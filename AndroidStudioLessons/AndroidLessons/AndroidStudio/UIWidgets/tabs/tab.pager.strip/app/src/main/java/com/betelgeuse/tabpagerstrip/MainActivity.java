package com.betelgeuse.tabpagerstrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    PagerTabStrip pagerTabStrip;
    ViewPager     viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerTabStrip = findViewById(R.id.pagerTabStrip);
        pagerTabStrip.set
        pagerTabStrip.setBackgroundResource(R.drawable.rectangle_border);
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setTextColor(Color.WHITE);
        pagerTabStrip.setTabIndicatorColor(Color.WHITE);
        viewPager=findViewById(R.id.viewPager);
         viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
         viewPager.setAdapter(viewPagerAdapter);

    }
}