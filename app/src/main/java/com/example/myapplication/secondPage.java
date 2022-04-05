package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class secondPage extends AppCompatActivity {

    private String spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ACTIONbAR =   getSupportActionBar();
        ACTIONbAR.hide();
        setContentView(R.layout.activity_second_page);

        System.out.println("New Second");


        Button button = findViewById(R.id.button);

        Button second = findViewById(R.id.button_second);

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);




        // directs to gameactivity (blank fragsment is empty
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin = spinner.getSelectedItem().toString();

                spin = spin.substring(0,1);
                //Testing purpose
                System.out.println(spin);
                int var = Integer.parseInt(spin);
                System.out.println(var);

                //String spin = spinner.getSelectedItem().toString();

                //System.out.println(spin);

                // good from here
                //var = 2;
                //Intent i = new Intent(getActivity(), GameActivity.class);
                Intent i = new Intent(getApplicationContext(),GameActivity.class);
                i.putExtra("var", var);
                startActivity(i);

            }
        });

        second.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }

    });




    }
}