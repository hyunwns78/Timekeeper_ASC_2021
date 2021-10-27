package org.techtown.tab;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class fragment_activity extends Fragment {

    ListView studyListView;
    LinearLayout studyLayout;
    ListView hobbyListView;
    LinearLayout hobbyLayout;
    LinearLayout restLayout;
    ListView restListView;
    Button button;
    String category;

    MainActivity activity;

    RadioGroup rg;
    RadioButton rbtn_study, rbtn_hobby, rbtn_rest;
    Context context;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_activity, viewGroup, false);
        setHasOptionsMenu(true);

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
                        button.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_hobby:
                        category = "취미";
                        button.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_rest:
                        category = "여가생활";
                        button.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog(context);
                dialog.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String active_name, String time) {

                        try{
                            BufferedWriter buf = new BufferedWriter(new FileWriter(context.getFilesDir()+"test.txt", true));
                            buf.write(active_name + " ");
                            buf.write(time + " ");
                            buf.write(category);
                            buf.newLine();
                            buf.close();
                        } catch (FileNotFoundException e){
                            e.printStackTrace();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNegativeClicked() {

                    }
                });
                dialog.show();
            }
        });


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
                } else if (tab.getPosition() == 2) {
                    studyLayout.setVisibility(View.GONE);
                    hobbyLayout.setVisibility(View.GONE);
                    restLayout.setVisibility(View.VISIBLE);
                    restListView.setVisibility(View.VISIBLE);
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

