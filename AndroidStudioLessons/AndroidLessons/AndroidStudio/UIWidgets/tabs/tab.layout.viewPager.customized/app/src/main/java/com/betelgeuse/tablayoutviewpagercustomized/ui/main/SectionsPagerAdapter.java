package com.betelgeuse.tablayoutviewpagercustomized.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.betelgeuse.tablayoutviewpagercustomized.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final        Context mContext;

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
     //   TextView tv = (TextView) v.findViewById(R.id.text);
      //  tv.setText(getPageTitle(position));
      //  ImageView img = (ImageView) v.findViewById(R.id.accelerate);
      //  img.setImageResource(R.drawable.ic_launcher_background);
        return v;
    }
    public SectionsPagerAdapter (Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem (int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle (int position) {
        return  ("Tab[" + String.valueOf(position).toString()+"]");
    }

    @Override
    public int getCount ( ) {
        // Show 2 total pages.
        return 20;
    }

}