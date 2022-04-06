package com.betelgeuse.recyclewiewdraggablecell;

public class Item {
    int ImageSourceId;
    String text;

    public Item (int resourceImage, String txt){
        resourceImage=resourceImage;
        text=txt;

    }
    public int getresourceImage (){
        return ImageSourceId;
    }


    public String getText(){
        return text;
    }


}