package com.betelgeuse.firebasecloud2;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Repository {

    public void Inıt ( ) {
        this.authorsCollection = firebaseFirestore.collection(Author.class.getSimpleName());
        this.booksCollection = firebaseFirestore.collection(Book.class.getSimpleName());
    }

    public Repository (AppCompatActivity context, FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = MyHelper.IsNullDebug(firebaseFirestore);
        this.context = MyHelper.IsNullDebug(context);

        this.Inıt();

    }

    private AppCompatActivity   context;
    private FirebaseFirestore   firebaseFirestore;
    private CollectionReference authorsCollection;
    private CollectionReference booksCollection;

    public void seedDatabases ( ) {

        for (int i = 0; i < 5; i++) {
            String Si = String.valueOf(i);
            Book book = (new Book("", Book.class.getSimpleName() + Si, ""));
            Author author = new Author("", Author.class.getClass().getSimpleName() + Si);

            DocumentReference books = booksCollection.document(MyHelper.GenerateId());  // Document oluştur ve ıd sini sen ver!!
            DocumentReference authors = authorsCollection.document(MyHelper.GenerateId());
            book.Id = books.getId();
            book.AuthorId = authors.getId();
            author.Id = authors.getId();
            authors.set(author);
            books.set(book);
        }

    }

    public void getBooksWithQuery ( ) {
        Task<QuerySnapshot> task = booksCollection.whereEqualTo("title", "Book0").get();
        task.addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot q : queryDocumentSnapshots) {
                Book book = q.toObject(Book.class);
                Log.e("TAG", book.Title);
            }
            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(this.context, " error!!", Toast.LENGTH_SHORT).show();
        });
    }

    public void getAllBookDocuments ( ) {
        booksCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot q : queryDocumentSnapshots) {
                Book book = q.toObject(Book.class);
                Log.e("TAG", book.Id);
            }
            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(this.context, " error!!", Toast.LENGTH_SHORT).show();
        });
    }

    public void getSıngleBookDocumentByDoccumentId (String ıd) {
        booksCollection.document(ıd).get().addOnSuccessListener(documentSnapshot -> {
            Book book = documentSnapshot.toObject(Book.class);
            Log.e("TAG", book.AuthorId);
            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show();
        });
    }

    public  void  sabahattinAlininButunKitapları(String authorId){
        Task<QuerySnapshot> task = booksCollection.whereEqualTo( "AuthorId", authorId).get();
        task.addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot q : queryDocumentSnapshots) {
                Book book = q.toObject(Book.class);
                Log.e("TAG", book.Title);
            }
            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(this.context, " error!!", Toast.LENGTH_SHORT).show();
        });
    }

}
