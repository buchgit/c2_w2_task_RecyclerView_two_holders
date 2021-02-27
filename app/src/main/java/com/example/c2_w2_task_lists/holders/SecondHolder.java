package com.example.c2_w2_task_lists.holders;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c2_w2_task_lists.R;
import com.example.c2_w2_task_lists.models.SecondModel;

public class SecondHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private TextView textView2;

    public SecondHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_second_holder);
        textView2 = itemView.findViewById(R.id.tv_second_holder_2);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), textView2.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void bind(SecondModel secondModel) {
        textView.setText(secondModel.getUid());
        textView2.setText(String.valueOf(secondModel.getNumber()));
    }
}
