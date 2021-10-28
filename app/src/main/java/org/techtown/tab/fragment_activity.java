package org.techtown.tab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class fragment_activity extends Fragment {

    ListView studyListView;
    LinearLayout studyLayout;
    ListView hobbyListView;
    LinearLayout hobbyLayout;
    LinearLayout restLayout;
    ListView restListView;
    Button button;
    String category;
    String text_name;

    MainActivity activity;

    RadioGroup rg;
    RadioButton rbtn_study, rbtn_hobby, rbtn_rest;
    Context context;

    private ListViewAdapter adapter, adapter1, adapter2;



    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_activity, viewGroup, false);
        setHasOptionsMenu(true);

        ListView listView2 = (ListView) inflate.findViewById(R.id.restView);
        ListView listView1 = (ListView) inflate.findViewById(R.id.hobbyView);
        ListView listView = (ListView) inflate.findViewById(R.id.studyView);
        adapter = new ListViewAdapter(getActivity());

        listView.setAdapter(adapter);


        rg = inflate.findViewById(R.id.rg);
        context= viewGroup.getContext();

        button = inflate.findViewById(R.id.button_insert);
        rbtn_study = inflate.findViewById(R.id.rb_study);
        rbtn_hobby = inflate.findViewById(R.id.rb_hobby);
        rbtn_rest = inflate.findViewById(R.id.rb_rest);


        this.studyLayout = (LinearLayout) inflate.findViewById(R.id.study);
        this.hobbyLayout = (LinearLayout) inflate.findViewById(R.id.hobby);
        this.studyListView = (ListView) inflate.findViewById(R.id.studyView);
        this.hobbyListView = (ListView) inflate.findViewById(R.id.hobbyView);
        this.restLayout = (LinearLayout) inflate.findViewById(R.id.rest);
        this.restListView = (ListView) inflate.findViewById(R.id.restView);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_study:
                        category = "학습";
                        text_name = "study.txt";
                        button.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_hobby:
                        category = "취미";
                        text_name = "hobby.txt";
                        button.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_rest:
                        category = "여가생활";
                        text_name = "rest.txt";
                        button.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog_activity dialog = new CustomDialog_activity(context);
                dialog.setDialogListener(new CustomDialog_activity.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String active_name, String time) {

                        try{
                            FileOutputStream outFs = context.openFileOutput(text_name, Context.MODE_APPEND);
                            String com = ",";
                            outFs.write(active_name.getBytes());
                            outFs.write(com.getBytes());
                            outFs.write(time.getBytes());
                            outFs.write(com.getBytes());
                            outFs.write(category.getBytes());
                            outFs.write(com.getBytes());
                            outFs.close();

                        } catch (FileNotFoundException e){
                            e.printStackTrace();
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                        if(text_name == "study.txt"){
                            adapter.addItem(active_name);
                            adapter.notifyDataSetChanged();
                        } else if (text_name == "hobby.txt"){
                            adapter1.addItem(active_name);
                            adapter1.notifyDataSetChanged();
                        } else if (text_name == "rest.txt"){
                            adapter2.addItem(active_name);
                            adapter2.notifyDataSetChanged();
                        }

                    }
                    @Override
                    public void onNegativeClicked() {

                    }
                });
                dialog.show();
            }
        });

        try{
            FileInputStream inFs = context.openFileInput("study.txt");
            byte[] txt = new byte[600];
            inFs.read(txt);
            String str = new String(txt);
            String[] name = str.split(",");

            for(int i = 0; i*3 < name.length; i++){
                adapter.addItem(name[0+i*3]);
            }
            adapter.notifyDataSetChanged();
            inFs.close();
        } catch (IOException e){
        }


        TabLayout tlselect = (TabLayout) inflate.findViewById(R.id.tlselect);
        tlselect.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    studyLayout.setVisibility(View.VISIBLE);
                    hobbyLayout.setVisibility(View.GONE);
                    restLayout.setVisibility(View.GONE);
                } else if (tab.getPosition() == 1) {
                    studyLayout.setVisibility(View.GONE);
                    hobbyLayout.setVisibility(View.VISIBLE);
                    restLayout.setVisibility(View.GONE);
                    adapter1 = new ListViewAdapter(getActivity());
                    listView1.setAdapter(adapter1);
                    try{
                        FileInputStream inFs = context.openFileInput("hobby.txt");
                        byte[] txt = new byte[600];
                        inFs.read(txt);
                        String str = new String(txt);
                        String[] name = str.split(",");

                        for(int i = 0; i*3 < name.length; i++){
                            adapter1.addItem(name[0+i*3]);
                        }
                        adapter1.notifyDataSetChanged();
                        inFs.close();
                    } catch (IOException e){
                    }
                } else if (tab.getPosition() == 2) {
                    studyLayout.setVisibility(View.GONE);
                    hobbyLayout.setVisibility(View.GONE);
                    restLayout.setVisibility(View.VISIBLE);
                    adapter2 = new ListViewAdapter(getActivity());
                    listView2.setAdapter(adapter2);
                    try{
                        FileInputStream inFs = context.openFileInput("rest.txt");
                        byte[] txt = new byte[600];
                        inFs.read(txt);
                        String str = new String(txt);
                        String[] name = str.split(",");

                        for(int i = 0; i*3 < name.length; i++){
                            adapter2.addItem(name[0+i*3]);
                        }
                        adapter2.notifyDataSetChanged();
                        inFs.close();
                    } catch (IOException e){
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //add code
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //add code
            }
        });
        return inflate;
    }

}

