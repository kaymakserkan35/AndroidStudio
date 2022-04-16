package com.betelgeuse.withlivedatab.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<String>  Name = new MutableLiveData<String>();
    private MutableLiveData<Integer> Id   = new MutableLiveData<Integer>();

    public void setName (String name) {
        Name.setValue(name);
    }
    public void setId (Integer id) {
        Id.setValue(id);
    }
    public MutableLiveData<String> getName ( ) {
        return Name;
    }
    public MutableLiveData<Integer> getId ( ) {
        return Id;
    }

    @Override
    protected void onCleared ( ) {
        // Dispose All your Subscriptions to avoid memory leaks
        super.onCleared();
    }
}