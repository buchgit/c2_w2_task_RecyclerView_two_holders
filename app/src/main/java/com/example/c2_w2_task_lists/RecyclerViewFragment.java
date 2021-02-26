package com.example.c2_w2_task_lists;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c2_w2_task_lists.models.AbstractModel;

/*
1. implement LoaderManager.LoaderCallbacks<Т> , где Т - тип таблицы хранения наших данных
2. implement адаптера, который extends RecyclerView.Adapter<RecyclerView.ViewHolder>
 */

public class RecyclerViewFragment extends Fragment implements LoaderManager.LoaderCallbacks<AbstractModel[]>, FirstAdapter.ElementManager {

    private final static int size = 15;

    private RecyclerView recyclerView;
    private final FirstAdapter firstAdapter = new FirstAdapter();
    private ProgressBar progressBar;
    private FirstAdapter.ElementManager elementManager;

    public static RecyclerViewFragment newInstance(){
        return new RecyclerViewFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    //создаем вьюху фрагмента
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rv_fr_layout,container,false);
    }
    //создаем элементы вьюхи фрагмента
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.rv_fragment);
        progressBar = view.findViewById(R.id.pr_bar_fragment);

    }

    //когда активити создана
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //установка типа адептера
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //установка адаптера
        recyclerView.setAdapter(firstAdapter);
        //передадим в лоадер для управления progress bar
        elementManager = this;
        //перезагрузка лоадера
        getLoaderManager().restartLoader(0,null,this);
    }

    @Override
    public void onDestroy() {

    }

    //при запуске лоадера
    @NonNull
    @Override
    public Loader<AbstractModel[]> onCreateLoader(int id, @Nullable Bundle args) {
        FirstLoader loader = new FirstLoader(getContext(), size);
        //для запуска progress bar передаем в лоадер
        loader.setElementManager(elementManager);
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<AbstractModel[]> loader, AbstractModel[] data) {
        firstAdapter.setMass(data);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<AbstractModel[]> loader) {

    }

    @Override
    public void setProgress(int value){
        progressBar.setProgress(value);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
