package com.betelgeuse.blockchainindicators.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("Name")  @Expose
    String Name;
    @SerializedName("role")  @Expose
    String role;

    public String getName ( ) {
        return Name;
    }

    public void setName (String name) {
        Name = name;
    }

    public String getRole ( ) {
        return role;
    }

    public void setRole (String role) {
        this.role = role;
    }
}
