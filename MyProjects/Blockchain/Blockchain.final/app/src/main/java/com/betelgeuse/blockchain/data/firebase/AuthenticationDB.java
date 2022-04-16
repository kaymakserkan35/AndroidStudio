package com.betelgeuse.blockchain.data.firebase;

import com.betelgeuse.blockchain.userinterface.authentication.AuthenticationController;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationDB {
    FirebaseUser currentUser;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    AuthenticationDB() {
        currentUser = auth.getCurrentUser();
    }
    protected void signUpWithEmailAndPassword(String email, String password, AuthenticationController.AuthListener listener){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener((Task<AuthResult> task) -> {
            if (task.isSuccessful())  {

               // listener.onSuccess(task.getResult());
            }
        });
    }
    protected void signInWithEmailAndPassword(String email, String password, AuthenticationController.AuthListener listener){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener((Task<AuthResult> task) -> {
            if (task.isSuccessful())  {
               // listener.onSuccess(task.getResult());
            }
        });
    }
    protected  void  signIn_signUp_WithEmailAndPassword(String email, String password, AuthenticationController.AuthListener listener){
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess (AuthResult authResult) {
               // listener.onSuccess(authResult);
            }

        }).addOnFailureListener((Exception e) -> {
            if (e instanceof FirebaseAuthInvalidUserException) {
                /*  This exception indicates a problem with the email address entered by the user. For example, the account does not exist or has been disabled in the Firebase console. The precise reason for the exception can be identified by accessing the error code as outlined later in this chapter.  */
                auth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener((AuthResult authResult) ->
                               // listener.onSuccess(authResult)).
                        addOnFailureListener((Exception e1) -> listener.onFailure(e1));
            }
            else {
               // listener.onFailure(e);
            }
        });
    }
}
