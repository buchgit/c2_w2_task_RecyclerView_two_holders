package com.example.c2_w2_task_lists.holders;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c2_w2_task_lists.FirstAdapter;
import com.example.c2_w2_task_lists.R;
import com.example.c2_w2_task_lists.models.SecondModel;

public class SecondHolder extends RecyclerView.ViewHolder {

    private final TextView textView;
    private final TextView textView2;
    private FirstAdapter.ElementManager elementManager;

    public SecondHolder(@NonNull final View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_second_holder);
        textView2 = itemView.findViewById(R.id.tv_second_holder_2);
    }

    public void bind(SecondModel secondModel, int position) {
        textView.setText(secondModel.getUid());
        textView2.setText(String.valueOf(secondModel.getNumber()));

        itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "deleted position: "+ String.valueOf(position), Toast.LENGTH_SHORT).show();
            elementManager.deleteItem(position);
        });
    }

    public void setElementManager(FirstAdapter.ElementManager elementManager) {
        this.elementManager = elementManager;
    }
}
