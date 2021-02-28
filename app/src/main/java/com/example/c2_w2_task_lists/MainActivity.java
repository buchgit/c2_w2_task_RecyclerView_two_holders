package com.example.c2_w2_task_lists;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c2_w2_task_lists.models.FirstModel;
import com.example.c2_w2_task_lists.models.SecondModel;

import java.util.Random;
import java.util.UUID;

/*
Курс 2, Многопоточность и сетевое взаимодействие, неделя 2 , задание "Списки"
 */

public class MainActivity extends AppCompatActivity {

    private FirstAdapter firstAdapter;
    private RecyclerView recyclerView;
    private RecyclerViewFragment fragment;
    final Random random = new Random(100);
    final String FRAGMENT_TAG = "fragment tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = (RecyclerViewFragment) getSupportFragmentManager().findFragmentByTag("RECYCLER_VIEW_FRAGMENT");

        if (fragment == null) {
            fragment = RecyclerViewFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fr_container, fragment, RecyclerViewFragment.TAG).replace(R.id.fr_container, fragment).commit();
        } else {
            fragment.getFirstAdapter().reloadAdapter();
        }

        //getSupportFragmentManager().beginTransaction().replace(R.id.fr_container,fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //fragment = (RecyclerViewFragment) getSupportFragmentManager().getFragments().get(0);
        fragment = (RecyclerViewFragment) getSupportFragmentManager().findFragmentByTag(RecyclerViewFragment.TAG);
        assert fragment != null;
        firstAdapter = fragment.getFirstAdapter();
        recyclerView = fragment.getRecyclerView();//нужен для установки курсора на добавленный элемент
        int randomInt = random.nextInt();
        switch (item.getItemId()) {
            case R.id.add_fh:
                firstAdapter.addElement(new FirstModel(UUID.randomUUID().toString(), "name" + String.valueOf(randomInt)));
                break;
            case R.id.add_sh:
                firstAdapter.addElement(new SecondModel(UUID.randomUUID().toString(), randomInt));
                break;
            default:
                break;
        }
        //устанавливаем курсор на последний элемент
        int count = firstAdapter.getItemCount();
        recyclerView.scrollToPosition(count - 1);

        return true;
    }

}