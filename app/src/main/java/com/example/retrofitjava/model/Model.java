package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("userId")
    public int userid;
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("body")
    public String body;
}
