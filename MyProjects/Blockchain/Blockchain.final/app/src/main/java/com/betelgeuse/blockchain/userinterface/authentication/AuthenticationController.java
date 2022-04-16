package com.betelgeuse.blockchain.userinterface.authentication;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationController extends ViewModel {
    FirebaseUser currentUser;
     private FirebaseAuth auth = FirebaseAuth.getInstance();
     AuthenticationController(){
         currentUser = auth.getCurrentUser();
     }
     interface AuthListener{
        void onSuccess (AuthResult authResult);
        void  onFailure (Exception e);
    }

    public FirebaseUser  getCurrentUser(){
         return currentUser;
    }
    protected void signUpWithEmailAndPassword(String email,String password,AuthListener listener){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener((Task<AuthResult> task) -> {
            if (task.isSuccessful())  {
                    listener.onSuccess(task.getResult());
            }
        });
    }
    protected void signInWithEmailAndPassword(String email,String password,AuthListener listener){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener((Task<AuthResult> task) -> {
            if (task.isSuccessful())  {
                    listener.onSuccess(task.getResult());
            }
        });
    }
    protected  void  signIn_signUp_WithEmailAndPassword(String email,String password,AuthListener listener){
         auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
             @Override
             public void onSuccess (AuthResult authResult) {
                    listener.onSuccess(authResult);
             }

         }).addOnFailureListener((Exception e) -> {
             if (e instanceof FirebaseAuthInvalidUserException) {
                 /*  This exception indicates a problem with the email address entered by the user. For example, the account does not exist or has been disabled in the Firebase console. The precise reason for the exception can be identified by accessing the error code as outlined later in this chapter.  */
                    auth.createUserWithEmailAndPassword(email,password)
                            .addOnSuccessListener((AuthResult authResult) ->
                                    listener.onSuccess(authResult)).
                            addOnFailureListener((Exception e1) -> listener.onFailure(e1));
             }
             else {
                 listener.onFailure(e);
             }
         });
    }
    private boolean isUserNameValid (String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }
    }
    private boolean isPasswordValid (String password) {
        return password != null && password.trim().length() > 5;
    }
}
