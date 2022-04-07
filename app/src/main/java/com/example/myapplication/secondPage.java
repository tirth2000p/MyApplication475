package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class secondPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        Spinner coloredSpinner =  findViewById((R.id.spinner2));
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
               this,
               R.array.integer_array_name,
               R.layout.custom_spinner
      );
       adapter.setDropDownViewResource(R.layout.custom_spinner);
      coloredSpinner.setAdapter(adapter);
      coloredSpinner.setOnItemSelectedListener(this);


//        spinner.setOnItemSelectedListener(this);
//        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),R.);


//        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.custom_spinner_dropdown,
//                R.id.spinner2);
//        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
//        spinner.setAdapter(adapter);
//

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}