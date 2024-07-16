package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.api.MovieController;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.SearchItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     MovieAdapter adapter;
     MovieController controller;

     Toolbar toolbar;
     RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        rvList = findViewById(R.id.rv_movie);

        initConstructor();
        initData();
    }

    private void initConstructor() {
        controller = new MovieController(MainActivity.this);
        adapter = new MovieAdapter(MainActivity.this);
    }

    private void initData() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("Movie Apps");
        toolbar.setTitleTextColor(R.color.white);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvList.setLayoutManager(layoutManager);
        rvList.setHasFixedSize(true);
        rvList.setAdapter(adapter);

        controller.retrieveMovie(new MovieController.movielistener() {
            @Override
            public void retrieveData(ArrayList<SearchItem> item) {
                adapter.setList(item);

            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        return super.onCreateOptionsMenu(menu);
    }*/
}