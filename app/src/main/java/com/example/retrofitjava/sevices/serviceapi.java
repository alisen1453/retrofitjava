package com.example.retrofitjava.sevices;

import com.example.retrofitjava.model.Model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface serviceapi {
    @GET("posts")
    Observable <List<Model>> getData();
    //Call<List<Model>> getData();
}
