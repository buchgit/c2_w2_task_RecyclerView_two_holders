package com.example.c2_w2_task_lists.holders;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c2_w2_task_lists.R;
import com.example.c2_w2_task_lists.models.FirstModel;

/*
в холдере:
1. в конструкторе принимаем вьюху и find элементы
2. в bind принимаем модель и заполняем из нее свойства в элементах
 */

public class FirstHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private TextView textView2;
    private FirstModel model;

    public FirstHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_first_holder);
        textView2 = itemView.findViewById(R.id.tv_first_holder_2);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void bind(FirstModel model) {
        textView.setText(model.getUid());
        textView2.setText(model.getName());
    }


}
