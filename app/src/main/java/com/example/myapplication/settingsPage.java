package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class settingsPage extends AppCompatActivity {

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

        Button buttonTheme = findViewById(R.id.theme);



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