package com.betelgeuse.firebase;

public class EntityC {
    public EntityC ( ) {

    }

    public String getId ( ) {
        return Id;
    }

    public EntityC (String id, String text) {
        Id = id;
        Text = text;
    }

    public void setId (String id) {
        Id = id;
    }

    public String getText ( ) {
        return Text;
    }

    public void setText (String text) {
        Text = text;
    }

    private String Id;
    private String Text;
    public  class  InnerClass{
        private  String Id;

        public  InnerClass getInstance(){
            return  new InnerClass();

        }
    }
}
