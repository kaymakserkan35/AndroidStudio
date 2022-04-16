package com.betelgeuse.blockchain.data.firebase;

import com.betelgeuse.blockchain.data.ADatabase;
import com.betelgeuse.blockchain.data.IUserOptionDB;
import com.betelgeuse.blockchain.data.dataListener.UserOptionDTOListListener;
import com.betelgeuse.blockchain.data.dto.UserOptionDTO;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserOptionDB extends ADatabase implements IUserOptionDB {
    private    FirebaseFirestore   db;
    private CollectionReference userOptionCollection;

    public UserOptionDB (FirebaseFirestore db) {
        super();
        this.db = db;
        userOptionCollection = db.collection(UserOptionDTO.class.getSimpleName());
    }

    @Override
    public void readUserOptionsByEmail (String email, UserOptionDTOListListener listener) {

    }

    @Override
    public void createUserOption (UserOptionDTO userOption) {

    }
}
