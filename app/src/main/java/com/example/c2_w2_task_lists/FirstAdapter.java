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

import java.util.ArrayList;

public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<AbstractModel> mass;
    public static final int FIRST_HOLDER_TYPE = 0;
    public static final int SECOND_HOLDER_TYPE = 1;
    private FirstAdapter.ElementManager elementManager;

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
            fholder.bind((FirstModel) mass.get(position),position);
            fholder.setElementManager(elementManager);
        } else if (holder instanceof SecondHolder) {
            SecondHolder sholder = (SecondHolder) holder;
            sholder.bind((SecondModel) mass.get(position),position);
            sholder.setElementManager(elementManager);
        } else throw new IllegalArgumentException();
    }

    @Override
    public int getItemCount() {
        if (mass != null) {
            return mass.size();
        } else return 0;
    }

    //метод нужен, чтобы определялся параметр int viewType в методе
    //onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    @Override
    public int getItemViewType(int position) {
        if(mass.get(position) instanceof FirstModel){
            return FIRST_HOLDER_TYPE;
        }else if (mass.get(position) instanceof SecondModel){
            return SECOND_HOLDER_TYPE;
        }else throw new IllegalArgumentException();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setMass(ArrayList<AbstractModel> mass) {
        this.mass = mass;
        //сигнал адаптеру, что данные следует обновить
        notifyDataSetChanged();
    }

    public ArrayList<AbstractModel> getMass(){
        return mass;
    }

    //интерфейс для управления прогресс баром, //не доделал
    public interface ElementManager{
        void setProgress(int value);
        ProgressBar getProgressBar();
        void deleteItem(int position);
    }

    public void setElementManager(FirstAdapter.ElementManager elementManager) {
        this.elementManager = elementManager;
    }

    public void addElement(AbstractModel model){
        mass.add(model);
        notifyDataSetChanged();
    }

}
