package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.ToggleButton;

public class settingsPage extends AppCompatActivity {

    private boolean nightMode = false;

    private boolean Sound = true;
    String SoundVal = "Sound: On";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ACTIONbAR =   getSupportActionBar();
        ACTIONbAR.hide();
        setContentView(R.layout.activity_settings_page);

        System.out.println("NewSettings");

        Spinner spinner = (Spinner) findViewById(R.id.spinner); // gotta work on spinner settings



        ToggleButton switchTheme = findViewById(R.id.theme);
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        switchTheme.setChecked(sharedPreferences.getBoolean("value", true));
        if (switchTheme.isChecked()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        switchTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                boolean clicked = switchTheme.isChecked();
                if(clicked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    switchTheme.setChecked(true);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    switchTheme.setChecked(false);
                }
            }
        });


        Button button3 = findViewById(R.id.button3);
        button3.setText(SoundVal);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                Sound = !Sound;

                if(Sound){

                    //change text
                SoundVal = "Sound: On";
                button3.setText(SoundVal);
                    // turn on sound

                }
                else{

                    //change text
                SoundVal = "Sound: Off";
                button3.setText(SoundVal);
                    // turn off sound
                }

            }

        });



        Button button4 = findViewById(R.id.button4);


        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }

        });

    }
}