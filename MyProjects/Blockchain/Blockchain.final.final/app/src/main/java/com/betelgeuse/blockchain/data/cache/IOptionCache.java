package com.betelgeuse.blockchain.data.cache;

import com.betelgeuse.blockchain.data.dto.UserOptionDTO;

import java.util.List;

public interface IOptionCache {
    public boolean createUserOption(UserOptionDTO userOptionDTO);
    public UserOptionDTO readUserOption (String email);
    public List<UserOptionDTO> readUserOptionsAll ();
    List<UserOptionDTO> readUserOptionsAllByEmail (String email);
    public boolean deleteUserOption (UserOptionDTO userOptionDTO);
    public  boolean updateUserOption (UserOptionDTO userOptionDTO);
}
