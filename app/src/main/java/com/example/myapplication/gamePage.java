package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DABLayoutView.DABLayout;

public class gamePage extends AppCompatActivity {

    private DABLayout DAB;
    private int grid_size, number_of_players;
    private String[] names;
    public Button GameMenu;
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


        GameMenu= findViewById(R.id.GameMenu);

        GameMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), GameMenu);
                //popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());
                popupMenu.getMenu().add(Menu.NONE,R.id.Reset,1,getString(R.string.Reset));
                popupMenu.getMenu().add(Menu.NONE,R.id.Resize,2,getString(R.string.Resize));
                popupMenu.getMenu().add(Menu.NONE,R.id.Quit,3,getString(R.string.Quit));
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.Reset:
                                System.out.println("reset");
                                Intent i = new Intent(getApplicationContext(),GameActivity.class);
                                i.putExtra("var", grid_size);
                                startActivity(i);
                                return true;
                            case R.id.Resize:
                                System.out.println("resize");
                                i = new Intent(getApplicationContext(),secondPage.class);
                                startActivity(i);
                                return true;
                            case R.id.Quit:
                                System.out.println("quit");
                                i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                                return true;
                            default:
                                return false;
                        }
// commit check

                    }
                });
                popupMenu.show();
                //return false;
            }
        });


    }


}
