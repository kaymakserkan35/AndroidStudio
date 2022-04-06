package com.betelgeuse.tabs.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.betelgeuse.tabs.R;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[]   TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final        int     tabCount   = 2;
    private final        Context mContext;

    public SectionsPagerAdapter (Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem (int position) {

        switch (position + 1) {
            case 1:
                return FragmentA.newInstance();
            case 2:
                return FragmentB.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override /*tab başlıklarını belirler*/
    public CharSequence getPageTitle (int position) {
        return mContext.getResources().getString(TAB_TITLES[position]) + " : " + String.valueOf(position);
    }

    @Override /* tab sayısını belirler*/
    public int getCount ( ) {
        return this.tabCount;
    }
}