package com.androindian.volleyretro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androindian.volleyretro.databinding.AppviewBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecAdp extends RecyclerView.Adapter<RecAdp.MyViewHolder> {

    ArrayList<String> Rid;
    ArrayList<String> Rpic;
    ArrayList<String> Rjul;
    ArrayList<String> Rapp;
    Context context;
    AppviewBinding binding;
    public RecAdp(MainActivity mainActivity,
                  ArrayList id,
                  ArrayList pic,
                  ArrayList jul,
                  ArrayList app) {
        Rid=id;
        Rjul=jul;
        Rpic=pic;
        Rapp=app;
        context=mainActivity;
    }

    @NonNull
    @Override
    public RecAdp.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding= DataBindingUtil.inflate(inflater,
                R.layout.appview,null,false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdp.MyViewHolder holder, int position) {

        holder.binding.textView.setText(Rapp.get(position));
        holder.binding.textView2.setText(Rid.get(position));
        holder.binding.textView3.setText(Rjul.get(position));

        Picasso.get().load(Rpic.get(position)).into(holder.binding.imageView);


    }

    @Override
    public int getItemCount() {
        return Rpic.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppviewBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=DataBindingUtil.getBinding(itemView);
        }
    }
}
