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
    private Item[] items;
    public int result;

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

            for(int i = 0; i<20; i++){
                items = new Item[i];
            }

            for(int i = 0; i*3 < name.length-1; i++){
                int result_hour = ((Integer.parseInt(name[2+i*3])-Integer.parseInt(name[1+i*3]))/100)*60;
                int result_min = (Integer.parseInt(name[2+i*3])-Integer.parseInt(name[1+i*3]))%100;
                items[i] = new Item(name[0+i*3], (String.valueOf((result_hour+result_min)/60)+"시간"+String.valueOf((result_hour+result_min)%60) +"분"), result_hour+result_min);
                itemList.add(items[i]);
            }

            inFs.close();
        } catch (IOException e){
        }

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

        try{
            FileInputStream inFs = context.openFileInput("record.txt");
            byte[] txt = new byte[600];
            inFs.read(txt);
            String str = new String(txt);
            String[] name = str.split(",");

            for(int i = 0; i*3 < name.length-1; i++){
                int result_hour = ((Integer.parseInt(name[2+i*3])-Integer.parseInt(name[1+i*3]))/100)*60;
                int result_min = (Integer.parseInt(name[2+i*3])-Integer.parseInt(name[1+i*3]))%100;
                int cal = result_hour+result_min;
                entries.add(new PieEntry(cal, name[0+i*3]));
            }
            inFs.close();
        } catch (IOException e){
        }

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
