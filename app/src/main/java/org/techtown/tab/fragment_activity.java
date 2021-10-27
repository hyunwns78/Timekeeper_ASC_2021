package org.techtown.tab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

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

    ListView listView1;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItem;


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

        listItem = new ArrayList<String>();
        listItem.add("홍길동");
        listItem.add("이순신");
        listItem.add("강감찬");
        listItem.add("조자룡");

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
                CustomDialog_activity dialog = new CustomDialog_activity(context);
                dialog.setDialogListener(new CustomDialog_activity.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String active_name, String time) {

                        try{
                            BufferedWriter buf = new BufferedWriter(new FileWriter(context.getFilesDir()+"activity.txt", true));
                            buf.write(active_name + " ");
                            buf.write(time + " ");
                            buf.write(category);
                            buf.newLine();
                            buf.close();

                            //listItem.add(.getText().toString()); //파일에서 가저와서 활동명 띄우기
                            adapter.notifyDataSetChanged(); // 변경되었음을 어답터에 알려준다.

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

