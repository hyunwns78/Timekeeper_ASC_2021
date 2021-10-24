package org.techtown.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class fragment_main extends Fragment {

    PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView){
        pieChart = rootView.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("활동비율");
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawCenterText(true);
        pieChart.setHighlightPerTapEnabled(true);
        Legend legend1 = pieChart.getLegend();
        legend1.setEnabled(false);
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);

        setData1();
    }

    private void setData1(){
        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(20, "JAVA"));
        entries.add(new PieEntry(40, "PYTHON"));
        entries.add(new PieEntry(40, "C"));

        PieDataSet dataSet = new PieDataSet(entries, "활동 비율");

        PieData data = new PieData(dataSet);
        data.setValueTextSize(22f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);
        pieChart.invalidate();
    }
}
