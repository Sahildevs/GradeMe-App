package com.example.loginapp;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public Adapter(Context context, ArrayList<Model> mList){

        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Model model = mList.get(position);
        holder.sname.setText(model.getName());
        holder.smark1.setText(model.getJava());
        holder.smark2.setText(model.getIOT());
        holder.smark3.setText(model.getPython());
        holder.stotal.setText(model.getTotal());
        holder.savg.setText(model.getAvg());
        holder.sgrade.setText(model.getGrade());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //Define the same varabile as in Model class
        TextView sname, smark1, smark2, smark3, stotal, savg, sgrade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sname = itemView.findViewById(R.id.tvname);
            smark1 = itemView.findViewById(R.id.tvmark1);
            smark2 = itemView.findViewById(R.id.tvmark2);
            smark3 = itemView.findViewById(R.id.tvmark3);
            stotal = itemView.findViewById(R.id.tvtotal);
            savg = itemView.findViewById(R.id.tvaverage);
            sgrade = itemView.findViewById(R.id.tvgrade);
        }
    }
}
