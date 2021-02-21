package com.example.c2_w2_task_lists;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class FirstAdapter extends RecyclerView.Adapter<FirstHolder> {

    private String[] mass;

    @NonNull
    @Override
    public FirstHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new FirstHolder(inflater.inflate(R.layout.first_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FirstHolder holder, int position) {
        holder.setTextView(mass[position]);
    }

    @Override
    public int getItemCount() {
        return mass.length;
    }

    public void setMass(String[] mass) {
        this.mass = mass;
    }
}
