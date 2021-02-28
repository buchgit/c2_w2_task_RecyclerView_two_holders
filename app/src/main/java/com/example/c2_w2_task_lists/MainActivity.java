package com.example.c2_w2_task_lists;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c2_w2_task_lists.models.FirstModel;
import com.example.c2_w2_task_lists.models.SecondModel;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private FirstAdapter firstAdapter;
    private RecyclerViewFragment fragment;
    final Random random = new Random(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction().replace(R.id.fr_container,RecyclerViewFragment.newInstance()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        fragment = (RecyclerViewFragment) getSupportFragmentManager().getFragments().get(0);
        firstAdapter = fragment.getFirstAdapter();
        int randomInt = random.nextInt();
        switch (item.getItemId()){
            case R.id.add_fh:
                firstAdapter.addElement(new FirstModel(UUID.randomUUID().toString(),"name"+String.valueOf(randomInt)));
                break;
            case R.id.add_sh:
                firstAdapter.addElement(new SecondModel(UUID.randomUUID().toString(),randomInt));
                break;
            default:
                break;
        }
        return true;
    }

}