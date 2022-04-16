package com.betelgeuse.firebasecloud2;

import java.io.Serializable;

public class Book implements Serializable {
 public   Book(){}
    public Book (String id, String title, String authorId) {
        Id = id;
        Title = title;
        AuthorId = authorId;
    }

    public String getId ( ) {
        return Id;
    }

    public void setId (String id) {
        Id = id;
    }

    public String getTitle ( ) {
        return Title;
    }

    public void setTitle (String title) {
        Title = title;
    }

    public String getAuthorId ( ) {
        return AuthorId;
    }

    public void setAuthorId (String authorId) {
        AuthorId = authorId;
    }

    String Id;
    String Title;
    String AuthorId;
}
