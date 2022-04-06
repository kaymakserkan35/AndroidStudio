package com.betelgeuse.blockchainindicators.indicators;

import android.os.Build;

import com.betelgeuse.blockchainindicators.H;

import java.util.Comparator;
import java.util.List;

 abstract class Average  {
    protected List<Data> dataList;
    protected Period     period;

    public Average (List<Data> dataList, Period period) {
        this.dataList = dataList;
        this.period = period;
    }

    public Average ( ) {
    }

    public Average setData (List<Data> dataList, Period period) {
        this.period = period;
        this.dataList = dataList;
        return this;
    }
    public void sortDataByCalendar (Sort sort) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dataList.sort(new Comparator<Data>() {
                @Override
                public int compare (Data o1, Data o2) {
                    if (sort.equals(Sort.ASCENDING)) return o1.calendar.compareTo(o2.calendar);
                    else return o2.calendar.compareTo(o1.calendar);
                }
            });
        } else
            H.errorLog(this.getClass().getSimpleName(), "sortDataByDate", "(Build.VERSION.SDK_INT eterli degil!");
    }
    public abstract void analyze ( );
}
