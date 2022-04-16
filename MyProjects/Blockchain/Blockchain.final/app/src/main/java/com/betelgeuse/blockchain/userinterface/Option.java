package com.betelgeuse.blockchain.userinterface;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.core.indicator.Average;
import com.betelgeuse.blockchain.core.indicator.Period;

public class Option {
    public String email;
    public  String fromCurrency;
    public  String toCurrency;
    @Nullable
    public  Period period;
    @Nullable
    public  Class<Average> averageClass;
    public  int history;
}
