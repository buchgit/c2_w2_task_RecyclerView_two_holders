package com.example.c2_w2_task_lists;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.c2_w2_task_lists.models.AbstractModel;

import java.util.ArrayList;

/*
1. implement LoaderManager.LoaderCallbacks<Т> , где Т - тип таблицы хранения наших данных
2. implement адаптера, который extends RecyclerView.Adapter<RecyclerView.ViewHolder>
3/ implement SwipeRefreshLayout для обновления данных в активити
 */

public class RecyclerViewFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<AbstractModel>>, FirstAdapter.ElementManager, SwipeRefreshLayout.OnRefreshListener {

    private final static int size = 7;

    private RecyclerView recyclerView;
    private final FirstAdapter firstAdapter = new FirstAdapter();
    private ProgressBar progressBar;
    private FirstAdapter.ElementManager elementManager;
    private SwipeRefreshLayout refreshLayout;

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
        refreshLayout = view.findViewById(R.id.sw_ref_layout);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        firstAdapter.setElementManager(elementManager);
        //перезагрузка лоадера
        //getLoaderManager().restartLoader(0,null,this);
        //устанавливает листенер обновлений активити
        refreshLayout.setOnRefreshListener(this);
        //передаем адаптер в главную активити
        ;


    }

    @Override
    public void onDestroy() {

    }

    //при запуске лоадера
    @NonNull
    @Override
    public Loader<ArrayList<AbstractModel>> onCreateLoader(int id, @Nullable Bundle args) {
        FirstLoader loader = new FirstLoader(getContext(), size);
        //для запуска progress bar передаем в лоадер
        loader.setElementManager(elementManager);
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<AbstractModel>> loader, ArrayList<AbstractModel> data) {
        firstAdapter.setMass(data);
        progressBar.setVisibility(View.INVISIBLE);
        if (refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<AbstractModel>> loader) {

    }

    @Override
    public void setProgress(int value){
        progressBar.setProgress(value);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void deleteItem (final int position){
        ArrayList<AbstractModel> mass = new ArrayList<>();
        final ArrayList<AbstractModel> currentMass = firstAdapter.getMass();
        //mass = (AbstractModel[]) Arrays.stream(currentMass).filter((e)->e!=currentMass[position]).toArray();
        for (int i=0;i<currentMass.size()-1;i++){
            if (i!=position){
                mass.add(currentMass.get(i));
            }
        }
        firstAdapter.setMass(mass);
    }

    public FirstAdapter getFirstAdapter() {
        return firstAdapter;
    }

    @Override
    public void onRefresh() {
        getLoaderManager().restartLoader(0,null,this);
    }
}
