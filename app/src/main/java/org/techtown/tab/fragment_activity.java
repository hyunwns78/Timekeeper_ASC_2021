package org.techtown.tab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.google.android.material.tabs.TabLayout;

public class fragment_activity extends Fragment {

    ListView studyListView;
    LinearLayout studyLayout;
    ListView hobbyListView;
    LinearLayout hobbyLayout;
    LinearLayout restLayout;
    ListView restListView;
    Button button;

    MainActivity activity;

    RadioGroup rg;
    RadioButton rbtn_study, rbtn_hobby, rbtn_rest;
    EditText dlgEdt_name, dlgEdt_total;
    View dialogView;
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

        context=viewGroup.getContext();

        button = inflate.findViewById(R.id.button_insert);
        //  methods =new Methods(activity);

        this.studyLayout = (LinearLayout) inflate.findViewById(R.id.study);
        this.hobbyLayout = (LinearLayout) inflate.findViewById(R.id.hobby);
        this.studyListView = (ListView) inflate.findViewById(R.id.studyView);
        this.hobbyListView = (ListView) inflate.findViewById(R.id.hobbyView);
        this.restLayout = (LinearLayout) inflate.findViewById(R.id.rest);
        this.restListView = (ListView) inflate.findViewById(R.id.restView);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogView = (View) View.inflate(context,
                        R.layout.dialog_activity,
                        null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                dlg.setTitle("활동입력창");
                dlg.setView(dialogView);
                dlg.setPositiveButton("추가", null);
                dlg.setNegativeButton("취소", null);
                dlg.show();
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

