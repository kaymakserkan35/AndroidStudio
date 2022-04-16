package com.betelgeuse.tabpagerstrip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> menuList = new ArrayList<>();

    public ViewPagerAdapter (@NonNull FragmentManager fm) {
        super(fm);
        for (int i = 0; i < 20; i++) {
            menuList.add("data->" + String.valueOf(i));
        }


    }

    @NonNull
    @Override
    public Fragment getItem (int position) {

        return FragmentMain.newInstance("Data" + String.valueOf(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle (int position) {
        return "Title : " + String.valueOf(position);
    }

    @Override
    public int getCount ( ) {
        return menuList.size();
    }
}
