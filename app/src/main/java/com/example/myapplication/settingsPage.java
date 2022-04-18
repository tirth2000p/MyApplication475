package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.util.Locale;

public class settingsPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean nightMode = false;
    private String lan;
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

        ImageButton button = (ImageButton) findViewById(R.id.imageButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lan = spinner.getSelectedItem().toString();
                //Testing purpose
                if(lan.equalsIgnoreCase("Español")){
                    setLocale("es");
                }
                else if(lan.equalsIgnoreCase("Français")){
                    setLocale("fr");
                }
                else if (lan.equalsIgnoreCase("中文")){
                    setLocale("zh");
                }

                else if (lan.equalsIgnoreCase("English")){
                    setLocale("en");
                }
                else if(lan.equalsIgnoreCase("বাংলা")){
                    setLocale("bn");
                }


            }
        });








        Spinner coloredSpinner =  findViewById((R.id.spinner));
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.list,
                R.layout.custom_spinner
        );
        adapter.setDropDownViewResource(R.layout.custom_spinner);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);


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


        ToggleButton button3 = findViewById(R.id.button3);
        button3.setText(SoundVal);
        SharedPreferences sharedPreferences2 = getSharedPreferences("save2", MODE_PRIVATE);
        button3.setChecked(sharedPreferences2.getBoolean("value2", true));
        if (button3.isChecked()) {
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        }
        else {
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
        }

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                boolean clicked = button3.isChecked();
                if(clicked) {

                    AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);

                    SharedPreferences.Editor editor = getSharedPreferences("save2", MODE_PRIVATE).edit();
                    editor.putBoolean("value2", true);
                    editor.apply();
                    button3.setChecked(true);
                }
                else {
                    AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
                    SharedPreferences.Editor editor = getSharedPreferences("save2", MODE_PRIVATE).edit();
                    editor.putBoolean("value2",false);
                    editor.apply();
                    button3.setChecked(false);
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

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, settingsPage.class);
        finish();
        startActivity(refresh);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}