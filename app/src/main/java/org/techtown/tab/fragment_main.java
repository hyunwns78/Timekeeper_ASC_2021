package org.techtown.tab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class fragment_main extends Fragment {

    PieChart pieChart;
    public RecyclerView recyclerView;
    ArrayList<Item> itemList;
    Context context;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        context = container.getContext();

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
        Item item1 = new Item("JAVA 공부", "2시간", 2);
        Item item2 = new Item("Python 공부", "3시간", 3);
        Item item3 = new Item("C언어 공부", "0시간", 0);
        Item item4 = new Item("안드로이드 공부", "4시간", 4);

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
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

        setData1();
    }

    private void setData1(){
        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(20, "공부"));
        entries.add(new PieEntry(40, "운동"));
        entries.add(new PieEntry(40, "휴식"));

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
    }

}
