package com.betelgeuse.blockchain.core.indicator;

import android.os.Build;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.H;

import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.List;

public abstract class Indicator {
    private Average    average;
    private Period     period;
    protected List<Data> dataList;

    protected Indicator (List<Data> dataList, @Nullable Period period) {
        this.dataList = dataList;
        if (period != null) this.period = period;
        else this.period = Period.fourteenDay;
        this.average = setAverage(SimpleMovingAverage.class);

    }

    public void sortDataListByCalendar (Sort sort) {
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

    public <subAverage extends Average> subAverage setAverage (Class<subAverage> subAverageType) {
        subAverage obj = null;
        try {
            Class<?> clazz = Class.forName(subAverageType.getName());
            Constructor<?> ctor = clazz.getConstructor(List.class, Period.class);
            Object object = ctor.newInstance(this.dataList, this.period);
            obj = (subAverage) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public abstract List<Data> analyze ( );

}
