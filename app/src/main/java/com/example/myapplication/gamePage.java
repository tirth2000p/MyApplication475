package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DABLayoutView.DABLayout;

public class gamePage extends AppCompatActivity {

    private DABLayout DAB;
    private int grid_size, number_of_players;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ACTIONbAR =   getSupportActionBar();
        ACTIONbAR.hide();
        setContentView(R.layout.activity_game_page);
        DAB = findViewById(R.id.dab);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        if (getIntent().getExtras() != null) {
            grid_size = getIntent().getExtras().getInt("grid");
            number_of_players = getIntent().getExtras().getInt("players");
            names = getIntent().getExtras().getStringArray("names");
        }
        DAB.initN(grid_size, number_of_players, names);
    }


}
