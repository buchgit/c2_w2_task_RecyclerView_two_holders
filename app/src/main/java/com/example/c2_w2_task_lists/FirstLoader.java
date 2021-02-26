package com.example.c2_w2_task_lists;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import com.example.c2_w2_task_lists.models.AbstractModel;
import com.example.c2_w2_task_lists.models.FirstModel;
import com.example.c2_w2_task_lists.models.SecondModel;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FirstLoader extends AsyncTaskLoader<AbstractModel[]> {

    private int size;
    private FirstAdapter.ElementManager elementManager;

    public FirstLoader(@NonNull Context context, int size) {
        super(context);
        this.size = size;
    }
    //не в главном потоке
    @Nullable
    @Override
    public AbstractModel[] loadInBackground() {
        try {
            return generateModels(size);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    //в главном потоке
    @Override
    protected void onStartLoading() {
        elementManager.getProgressBar().setVisibility(View.VISIBLE);
        forceLoad();
    }
    //в главном потоке. Не отрабатывает окончание загрузки, не останавливает прогресс бар в этом месте.
    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    AbstractModel[] generateModels(int size) throws InterruptedException {
        Random random = new Random();

        AbstractModel[] mass = new AbstractModel[size];
        for (int i = 0; i < size; i++) {
            TimeUnit.MILLISECONDS.sleep(300);
            int type = random.nextInt(200);
            if (type%2 == 0) {
                FirstModel model = new FirstModel(UUID.randomUUID().toString(), "name" + i);
                mass[i] = model;
            } else {
                SecondModel model2 = new SecondModel(UUID.randomUUID().toString(), i);
                mass[i] = model2;
            }
            //elementManager.setProgress(i * 10);
        }
        return mass;
    }

    public void setElementManager(FirstAdapter.ElementManager elementManager) {
        this.elementManager = elementManager;
    }
}
