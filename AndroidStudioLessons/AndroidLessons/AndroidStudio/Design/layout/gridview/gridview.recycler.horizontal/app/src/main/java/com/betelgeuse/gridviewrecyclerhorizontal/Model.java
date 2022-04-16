package com.betelgeuse.gridviewrecyclerhorizontal;

public class Model {
    public Model (String name, int id) {
        Name = name;
        imageId = id;
    }

    public String getName ( ) {
        return Name;
    }

    public void setName (String name) {
        Name = name;
    }

    public int getImageId ( ) {
        return imageId;
    }

    public void setImageId (int imageId) {
        this.imageId = imageId;
    }

    private String Name;
    private int    imageId;
}
