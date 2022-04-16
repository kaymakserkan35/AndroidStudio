package com.betelgeuse.firebase;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private class EntityB   {
        public EntityB(String id, String title) {
            Id = id;
            Title = title;
        }
        public String Id = "";
        public String Title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tabloA = database.getReference("tabloA");
        DatabaseReference tabloB = database.getReference("tabloB");// tablo adı....

        EntityA ea = new EntityA();
        ea.setName("serkan");
        EntityB eb = new EntityB("","");
        eb.Title = "onur";
        tabloA.push().setValue(ea);
        tabloB.push().setValue(eb);

     tabloA.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()) {
                        EntityA ea = d.getValue(EntityA.class);
                        ea.setId(d.getKey())  ;

                    Log.e("entityA",ea.getId());  Log.e("entityA",ea.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Query q = tabloA.child("id").equalTo("serkan");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()) {
                    EntityA ea = d.getValue(EntityA.class);
                    ea.setId(d.getKey())  ;

                    Log.e("entityA",ea.getId());  Log.e("entityA",ea.getName());
                }
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {

            }
        });


        DatabaseReference tabloC = database.getReference("tabloC");
        EntityC ec = new EntityC( "","entitiyC");
        EntityC.InnerClass ecInner;
        //ecInner = new EntityC.InnerClass().getInstance();

        tabloC.push().setValue(ec);
        tabloC.child("TabloC_InnerClass").setValue( new EntityA());

        Query q2 = tabloA.child("name").equalTo("serkan");
        q2.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess (DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    EntityA ea = d.getValue(EntityA.class);
                    ea.setId(d.getKey())  ;

                    Log.e("entityA",ea.getId());  Log.e("entityA",ea.getName());
                }
            }
        });



    }
}