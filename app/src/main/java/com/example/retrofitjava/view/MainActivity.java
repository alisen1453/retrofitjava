package com.example.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.retrofitjava.R;
import com.example.retrofitjava.adapter.apiadapter;
import com.example.retrofitjava.constant.constants;
import com.example.retrofitjava.databinding.ActivityMainBinding;
import com.example.retrofitjava.model.Model;
import com.example.retrofitjava.sevices.serviceapi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<Model> models;
    Retrofit retrofit;
    private apiadapter apiadapter;
    private ActivityMainBinding binding;
    CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //models = new ArrayList<>();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }
    private void loadData() {
        serviceapi services = retrofit.create(serviceapi.class);
        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(services.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getDataObservable));
        /*
        serviceapi services = retrofit.create(serviceapi.class);
        Call<List<Model>> call = services.getData();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                models=new ArrayList<>();
                if (response.isSuccessful()) {
                    List<Model> list = response.body();
                    models = new ArrayList<>(list);

                   // for (Model model:models){
                    //    System.out.println(model.title);
                   // }

                    binding.recyvleview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    apiadapter = new apiadapter(models);
                    binding.recyvleview.setAdapter(apiadapter);
                }
            }
         @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                t.printStackTrace();
            }
        });*/
    }
    public void getDataObservable(List<Model> list){
        models = new ArrayList<>(list);

        // for (Model model:models){
        //    System.out.println(model.title);
        // }

        binding.recyvleview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        apiadapter = new apiadapter(models);
        binding.recyvleview.setAdapter(apiadapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}