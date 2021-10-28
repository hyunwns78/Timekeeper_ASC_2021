package org.techtown.tab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {



    fragment_main fragment_main;
    fragment_activity fragment_activity;
    fragment_graph fragment_graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        fragment_main = new fragment_main();
        fragment_activity = new fragment_activity();
        fragment_graph = new fragment_graph();

        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, fragment_main).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.tab_main:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container_main, fragment_main).commit();
                                return true;
                            case R.id.tab_activty:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container_main, fragment_activity).commit();
                                return true;
                            case R.id.tab_graph:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container_main, fragment_graph).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );

    }

}