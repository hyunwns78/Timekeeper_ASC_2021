package org.techtown.tab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class fragment_main extends Fragment {

    PieChart pieChart;
    public RecyclerView recyclerView;
    ArrayList<Item> itemList;
    Context context;
    Bundle bundle;
    ImageView btnrec;
    View dialogView;
    Button button;
    TextView text_category;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        context = container.getContext();
        btnrec= rootView.findViewById(R.id.btnrec);
        text_category = rootView.findViewById(R.id.text_category);


        btnrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog_main dialog = new CustomDialog_main(context);
                dialog.setDialogListener(new CustomDialog_main.CustomDialogListener() {

                    @Override
                    public void onPositiveClicked(String active_name, String starttime, String endtime) {

                        try{
                            FileOutputStream outFs = context.openFileOutput("record.txt", Context.MODE_APPEND);
                            String com = ",";
                            outFs.write(active_name.getBytes());
                            outFs.write(com.getBytes());
                            outFs.write(starttime.getBytes());
                            outFs.write(com.getBytes());
                            outFs.write(endtime.getBytes());
                            outFs.write(com.getBytes());
                            outFs.close();

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

        initUI(rootView);
        initImage(rootView);

        return rootView;


    }

    public void initImage(ViewGroup rootView){
        recyclerView = rootView.findViewById(R.id.recycler);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(layoutManager);
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new Myadapter(insertItem()));
    }

    ArrayList insertItem(){
        itemList = new ArrayList<Item>();

        try{
            FileInputStream inFs = context.openFileInput("record.txt");
            byte[] txt = new byte[600];
            inFs.read(txt);
            String str = new String(txt);
            String[] name = str.split(",");
            Item item = null;
            Item item2 = null;

            for(int i = 0; i*3 < name.length; i++){
                item = new Item(name[0], name[1], Integer.parseInt(name[2])-Integer.parseInt(name[1]));
                item2 = new Item(name[3], name[4], Integer.parseInt(name[5])-Integer.parseInt(name[4]));
            }
            itemList.add(item);
            itemList.add(item2);

            inFs.close();
        } catch (IOException e){
        }

        //Item item1 = new Item("JAVA 공부", "2시간", 2);
        //Item item2 = new Item("Python 공부", "3시간", 3);
        //Item item3 = new Item("C언어 공부", "0시간", 0);
        //Item item4 = new Item("안드로이드 공부", "4시간", 4);

        //itemList.add(item1);
        //itemList.add(item2);
        //itemList.add(item3);
        //itemList.add(item4);

        return itemList;
    }

    private void initUI(ViewGroup rootView){

        pieChart = rootView.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("활동비율");
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(38f);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setDrawCenterText(true);
        pieChart.setHighlightPerTapEnabled(true);

        Legend legend1 = pieChart.getLegend();
        legend1.setEnabled(false);

        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(16f);

        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(20, "학습"));
        entries.add(new PieEntry(40, "취미"));
        entries.add(new PieEntry(40, "여가활동"));

        PieDataSet dataSet = new PieDataSet(entries, "활동 비율");

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.JOYFUL_COLORS){
            colors.add(c);
        }
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(26f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int x = pieChart.getData().getDataSetForEntry(e).getEntryIndex((PieEntry) e);

                String category = entries.get(x).getLabel();
                text_category.setText(category);

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }


}
