package com.betelgeuse.blockchain.data.dataListener;

import com.betelgeuse.blockchain.data.dto.TickerDTO;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;

import java.util.List;

public interface UserOptionDTOListListener {
    public  void onSuccess(List<UserOptionDTO> userOptionDTOList);
}
