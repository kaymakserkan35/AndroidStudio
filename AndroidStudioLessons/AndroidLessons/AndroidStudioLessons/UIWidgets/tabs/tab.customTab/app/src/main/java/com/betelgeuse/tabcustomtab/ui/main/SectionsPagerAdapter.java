package com.betelgeuse.tabcustomtab.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.betelgeuse.tabcustomtab.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[]   TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final        Context mContext;

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
        return "Tab["+String.valueOf(position)+"]";
    }



    @Override
    public int getCount ( ) {
        // Show 2 total pages.
        return 10;
    }


    public View getTabView(int position) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.tab_layout, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        ViewGroup layout = (ViewGroup) view.findViewById(R.id.layout);

      //  layout.setBackgroundResource(this.mColorList.get(position));
        icon.setImageResource(R.drawable.tab_icon);
        title.setText(this.getPageTitle(position));
        return view;
    }
}