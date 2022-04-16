package com.betelgeuse.blockchain.data;

import com.betelgeuse.blockchain.data.dto.UserDTO;

public interface IAuthenticationDB {
    UserDTO getCurrentUser ( );

    void signInWithEmailAndPassword (String email, String password);

    void signIn_signUp_WithEmailAndPassword (String email, String password);

    void signUpWithEmailAndPassword (String email, String password);
}
