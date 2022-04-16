package com.betelgeuse.blockchain.userinterface.Model;

import com.betelgeuse.blockchain.data.dto.TickerDTO;

import java.util.List;

public interface InformationModelListListener {
    public  void onSuccess(List<InformationModel> infos);
}
