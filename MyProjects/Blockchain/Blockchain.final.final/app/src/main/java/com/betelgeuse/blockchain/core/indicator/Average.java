package com.betelgeuse.blockchain.core.indicator;

import android.os.Build;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.H;

import java.util.Comparator;
import java.util.List;

public abstract class Average {
     protected List<Data> dataList;
     protected Period     period;
    public Average (List<Data> dataList,@Nullable Period period) {
        if (period!= null) this.period = period;
        else  this.period = Period.fourteenDay;
        this.dataList = dataList;

    }
    public Average setData (List<Data> dataList,@Nullable Period period) {
        if (period!= null) this.period = period;
        else  this.period = Period.fourteenDay;
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

        } else H.errorLog(this.getClass().getSimpleName(), "sortDataByDate", "(Build.VERSION.SDK_INT eterli degil!");
    }
    public abstract List<Data> analyze ( );

}
