package org.techtown.tab;

import com.github.mikephil.charting.charts.BarChart;

public class Item {

    private String activity;
    private String time;

    public Item(String activity, String time){
        this.activity = activity;
        this.time = time;
    }

    public String getItem_activity(){
        return activity;
    }
    public String getItem_time(){
        return time;
    }
    public void setItem_activity(String activity){
        this.activity = activity;
    }
    public void setItem_time(String time){
        this.time = time;
    }
}
