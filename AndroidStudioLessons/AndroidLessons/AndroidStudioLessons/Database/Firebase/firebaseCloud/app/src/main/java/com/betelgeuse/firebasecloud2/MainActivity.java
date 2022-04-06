package com.betelgeuse.firebasecloud2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.betelgeuse.firebasecloud2.databinding.ActivityMainBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private  Repository  repository;
    private  ActivityMainBinding binding;
    private  FirebaseFirestore firebaseFirestore;

    public void writeData ( ) {
        Log.e("TAG", "writeData: methodun içindeyim");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("first", "seko");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference ->
                        {
                            Toast.makeText(MainActivity.this, "success!!", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                )
                .addOnFailureListener(e ->
                {
                    Toast.makeText(MainActivity.this, "success!!", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "Error adding document");
                });

    }
    public void Instances(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        repository = new Repository(MainActivity.this,firebaseFirestore);
    }
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Instances();
        binding.buttonSaveToDatabaseID.setOnClickListener(v -> {
            MyHelper.IsNullDebug(v);
          //  MyDebug.IsNullDebug(binding.buttonSaveToDatabaseID);
          // repository.seedDatabases();
         //  repository.getBooksWithQuery();
            repository.getSıngleBookDocumentByDoccumentId("043a51639504010652");
        });
    }
}