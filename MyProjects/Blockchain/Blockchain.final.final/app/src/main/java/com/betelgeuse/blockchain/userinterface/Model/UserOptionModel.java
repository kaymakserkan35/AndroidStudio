package com.betelgeuse.blockchain.userinterface.Model;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.core.indicator.Period;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;

public class UserOptionModel {

    public UserOptionModel(UserOptionDTO userOptionDTO) {
        this.email = userOptionDTO.email;
        this.fromCurrency = userOptionDTO.fromCurrency;
        this.toCurrency = userOptionDTO.toCurrency;
        this.period =userOptionDTO.period;
        this.history = userOptionDTO.history;
    }
    public String email;
    public String fromCurrency;
    public  String toCurrency;

    public UserOptionModel (String email, String fromCurrency, String toCurrency, @Nullable Period period, int history) {
        this.email = email;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.period = period;
        this.history = history;
    }

    @Nullable
    public  Period period;
    @Nullable
    public  int    history;
}
