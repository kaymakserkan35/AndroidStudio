package com.betelgeuse.blockchainindicators.indicators;

import android.os.Build;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchainindicators.H;

import java.util.Comparator;
import java.util.List;

public abstract class Indicator {
    public Period     period;
    public List<Data> dataList;
    protected Indicator ( ) {
    }
    protected Indicator (List<Data> dataList,Period period ) {
        this.period = period;
        this.dataList = dataList;
    }

    public boolean setPeriod(int period){
       Period _period = Period.get(period);
       if (_period != null) {
           this.period = _period;
           return true;
       }
       else    {
           return  false;
       }
    }
    public void setData (@Nullable Period period, List<Data> dataList) {
        if (period==null){
            this.period = Period.fourteenDay;
        }
        this.dataList = dataList;
        this.period = period;
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

    public abstract List<Data> analyze ( );
}
