package com.example.c2_w2_task_lists;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c2_w2_task_lists.holders.FirstHolder;
import com.example.c2_w2_task_lists.holders.SecondHolder;
import com.example.c2_w2_task_lists.models.AbstractModel;
import com.example.c2_w2_task_lists.models.FirstModel;
import com.example.c2_w2_task_lists.models.SecondModel;


public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AbstractModel[] mass;
    public static final int FIRST_HOLDER_TYPE = 0;
    public static final int SECOND_HOLDER_TYPE = 1;

    //создает все виды холдеров из их вьюх
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == FIRST_HOLDER_TYPE) {
            return new FirstHolder(inflater.inflate(R.layout.first_holder, parent, false));
        } else if (viewType == SECOND_HOLDER_TYPE) {
            return new SecondHolder(inflater.inflate(R.layout.second_holder,parent,false));
        } else throw new IllegalArgumentException();
    }

    //как забирать холдеры из хранилища моделей и заполнять из модели каждый холдер
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FirstHolder) {
            FirstHolder fholder = (FirstHolder) holder;
            fholder.bind((FirstModel) mass[position]);
        } else if (holder instanceof SecondHolder) {
            SecondHolder sholder = (SecondHolder) holder;
            sholder.bind((SecondModel) mass[position]);
        } else throw new IllegalArgumentException();
    }

    @Override
    public int getItemCount() {
        if (mass != null) {
            return mass.length;
        } else return 0;
    }

    //метод нужен, чтобы определялся параметр int viewType в методе
    //onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    @Override
    public int getItemViewType(int position) {
        if(mass[position] instanceof FirstModel){
            return FIRST_HOLDER_TYPE;
        }else if (mass[position] instanceof SecondModel){
            return SECOND_HOLDER_TYPE;
        }else throw new IllegalArgumentException();
    }

    public void setMass(AbstractModel[] mass) {
        this.mass = (AbstractModel[]) mass;
        //сигнал адаптеру, что данные следует обновить
        notifyDataSetChanged();
    }

    //интерфейс для управления прогресс баром, //не доделал
    public interface ElementManager{
        void setProgress(int value);
        ProgressBar getProgressBar();
    }
}
