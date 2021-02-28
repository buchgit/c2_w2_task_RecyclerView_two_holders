package com.example.c2_w2_task_lists.holders;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c2_w2_task_lists.FirstAdapter;
import com.example.c2_w2_task_lists.R;
import com.example.c2_w2_task_lists.models.FirstModel;

/*
в холдере:
1. в конструкторе принимаем вьюху и find элементы
2. в bind принимаем модель и заполняем из нее свойства в элементах
 */

public class FirstHolder extends RecyclerView.ViewHolder {

    private final TextView textView;
    private final TextView textView2;
    private FirstAdapter.ElementManager elementManager;

    public FirstHolder(@NonNull final View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_first_holder);
        textView2 = itemView.findViewById(R.id.tv_first_holder_2);
    }

    public void bind(FirstModel model, int position) {
        textView.setText(model.getUid());
        textView2.setText(model.getName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "deleted position: " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                elementManager.deleteItem(position);
            }
        });
    }

    public void setElementManager(FirstAdapter.ElementManager elementManager) {
        this.elementManager = elementManager;
    }
}
