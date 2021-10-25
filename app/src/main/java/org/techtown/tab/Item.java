package org.techtown.tab;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;

public class Item{


    private String activity;
    private String time;
    private int hour;

    public Item(String activity, String time, int hour){
        this.hour = hour;
        this.activity = activity;
        this.time = time;
    }

    public String getItem_activity(){
        return activity;
    }
    public String getItem_time(){
        return time;
    }
    public int getItem_hour(){return hour; }

    public void getItem_hour(int hour) {
        this.hour = hour;
    }

    public void setItem_activity(String activity){
        this.activity = activity;
    }
    public void setItem_time(String time){
        this.time = time;
    }
}
