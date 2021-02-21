package com.example.c2_w2_task_lists;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirstHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public FirstHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_first_holder);
    }

    public void setTextView(String text){
        textView.setText(text);
    }


}
