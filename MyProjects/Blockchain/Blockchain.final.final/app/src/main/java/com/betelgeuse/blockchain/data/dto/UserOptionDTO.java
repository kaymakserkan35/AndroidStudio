package com.betelgeuse.blockchain.data.dto;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.core.indicator.Average;
import com.betelgeuse.blockchain.core.indicator.Period;
import com.betelgeuse.blockchain.userinterface.Model.UserOptionModel;

public class UserOptionDTO {
    public String email;
    public UserOptionDTO(UserOptionModel userOptionModel) {
       this.email = userOptionModel.email;
       this.fromCurrency = userOptionModel.fromCurrency;
       this.toCurrency = userOptionModel.toCurrency;
       this.period =userOptionModel.period;
       this.history = userOptionModel.history;
    }
    public UserOptionDTO (String email, String fromCurrency, String toCurrency, @Nullable Period period, int history) {
       this.email = email;
       this.fromCurrency = fromCurrency;
       this.toCurrency = toCurrency;
       this.period = period;
       this.history = history;
    }

    public        String fromCurrency;
    public        String toCurrency;
    @Nullable
    public        Period period;
    @Nullable
    public        int    history;
    /*---------------------------------------*/
    public static String Email_String="email";
    public static String FromCurrency_String ="fromCurrency";
    public static String ToCurrency_String="toCurrency";
    @Nullable
    public static String Period_INT="period";
    @Nullable
    public static  String History_INT="history";
}
