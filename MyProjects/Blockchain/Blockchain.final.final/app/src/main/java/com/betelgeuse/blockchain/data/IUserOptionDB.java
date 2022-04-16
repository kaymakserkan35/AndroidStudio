package com.betelgeuse.blockchain.data;

import com.betelgeuse.blockchain.data.dataListener.UserOptionDTOListListener;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;

public interface IUserOptionDB {
    void readUserOptionsByEmail (String email, UserOptionDTOListListener listener);
    void  createUserOption(UserOptionDTO userOption);
}
