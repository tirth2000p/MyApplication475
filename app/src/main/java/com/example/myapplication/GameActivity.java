package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity {



    int number_of_players =2;
    String [] names = {"P 1", "P 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        int grid_size = getIntent().getExtras().getInt("var");



        /*  Game start code */

        Intent i = new Intent(getApplicationContext(), gamePage.class);
        i.putExtra("names", names);
        i.putExtra("grid", grid_size);
        i.putExtra("players", number_of_players);
        startActivity(i);
        finish();


    }




}
