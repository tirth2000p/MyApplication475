package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ACTIONbAR =   getSupportActionBar();

        setContentView(R.layout.activity_first_page);

        System.out.println("New Main");

        Button button = findViewById(R.id.settings);

        Button button2 = findViewById(R.id.multipliers);



        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),settingsPage.class);
                startActivity(i);
            }

        });
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),secondPage.class);
                startActivity(i);
            }

        });
    }
}