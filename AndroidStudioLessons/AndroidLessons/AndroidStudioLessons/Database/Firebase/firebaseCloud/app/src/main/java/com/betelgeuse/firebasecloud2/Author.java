package com.betelgeuse.firebasecloud2;

public class Author {
    public  Author(){}
    public Author (String id, String name) {
        Id = id;
        Name = name;
    }

    public String getId ( ) {
        return Id;
    }

    public void setId (String id) {
        Id = id;
    }

    public String getName ( ) {
        return Name;
    }

    public void setName (String name) {
        Name = name;
    }

    String Id;
    String Name;
}
