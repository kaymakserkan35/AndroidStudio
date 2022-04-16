package com.betelgeuse.recyclerviewcomplexdata;

public class Item {
    private String id;
    private String Name;
    private String Url;

    public Item (String id, String name,String url) {
        this.Url=url;
        this.id = id;
        this.Name = name;
    }

    public String getId ( ) {
        return id;
    }
    public void setId (String id) {
        this.id = id;
    }
    public String getName ( ) {
        return Name;
    }
    public void setName (String name) {
        Name = name;
    }

    public String getUrl ( ) {
        return Url;
    }
    public void setUrl (String url) {
        Url = url;
    }


}
