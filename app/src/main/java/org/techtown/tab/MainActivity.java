package org.techtown.tab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;


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

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_main).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.tab_main:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_main).commit();
                                return true;
                            case R.id.tab_activty:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_activity).commit();
                                return true;
                            case R.id.tab_graph:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_graph).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );

    }

}