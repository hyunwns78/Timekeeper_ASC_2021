package org.techtown.tab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    private TabLayout tabs;
    fragment_main fragment_main;
    fragment_activity fragment_activity;
    fragment_graph fragment_graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment_main = new fragment_main();
        fragment_activity = new fragment_activity();
        fragment_graph = new fragment_graph();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_main).commit();

        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("MAIN"));
        tabs.addTab(tabs.newTab().setText("ACTIVITY"));
        tabs.addTab(tabs.newTab().setText("GRAPH"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);

                Fragment selected = null;
                if (position == 0){
                    selected = fragment_main;
                }
                else if (position == 1){
                    selected = fragment_activity;
                }
                else if (position == 2){
                    selected = fragment_graph;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}