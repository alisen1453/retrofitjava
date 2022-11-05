package com.example.retrofitjava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitjava.databinding.ApiRowBinding;
import com.example.retrofitjava.model.Model;

import java.util.ArrayList;

public class apiadapter extends RecyclerView.Adapter<apiadapter.apiviewholder> {
    ArrayList<Model> models;
    public apiadapter(ArrayList<Model> models) {
        this.models = models;
    }
    @NonNull
    @Override
    public apiadapter.apiviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ApiRowBinding apiRowBinding=ApiRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new apiviewholder(apiRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull apiadapter.apiviewholder holder, int position) {
        holder.binding.userId.setText(String.valueOf(models.get(position).userid));
        holder.binding.id.setText(String.valueOf(models.get(position).id));
        holder.binding.title.setText(models.get(position).title);
        holder.binding.body.setText(models.get(position).body);
        //System.out.println(model.get(position).title);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class apiviewholder extends RecyclerView.ViewHolder {
        private ApiRowBinding binding;

        public apiviewholder(ApiRowBinding apiRowBinding) {
            super(apiRowBinding.getRoot());
            this.binding = apiRowBinding;
        }


    }
}
