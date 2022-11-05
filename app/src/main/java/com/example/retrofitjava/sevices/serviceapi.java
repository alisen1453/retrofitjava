package com.example.retrofitjava.sevices;

import com.example.retrofitjava.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface serviceapi {
    @GET("posts")
    Call<List<Model>> getData();
}
