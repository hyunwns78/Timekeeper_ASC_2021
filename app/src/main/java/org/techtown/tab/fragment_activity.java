package org.techtown.tab;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

public class fragment_activity extends Fragment {

    private BarChart caloriesBarChart;
    ListView caloriesListView;
    //CaloriesStatsAdapter caloriesStatsAdapter;
    LinearLayout caloriesStatsLayout;
    //List<DaysFoodModel> daysFoodList;
    private BarChart weightBarChart;
    ListView weightListView;
    //WeightStatsAdapter weightStatsAdapter;
    LinearLayout weightStatsLayout;

    MainActivity activity;
    //Methods methods;

    public fragment_activity() {
        //add code
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_activity, viewGroup, false);
        setHasOptionsMenu(true);
      //  methods =new Methods(activity);

        ImageView imageView = (ImageView) inflate.findViewById(R.id.back_arrow);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

        this.caloriesStatsLayout = (LinearLayout) inflate.findViewById(R.id.caloriesStatsLayout);
        this.weightStatsLayout = (LinearLayout) inflate.findViewById(R.id.weightStatsLayout);
        this.caloriesListView = (ListView) inflate.findViewById(R.id.caloriesListView);
        this.weightListView = (ListView) inflate.findViewById(R.id.weightListView);

        TabLayout tlselect = (TabLayout) inflate.findViewById(R.id.tlselect);
        tlselect.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() == 0){
                    caloriesStatsLayout.setVisibility(View.VISIBLE);
                    weightStatsLayout.setVisibility(View.GONE);
                }
                else if(tab.getPosition()==1)
                {
                    caloriesStatsLayout.setVisibility(View.GONE);
                    weightStatsLayout.setVisibility(View.VISIBLE);
                }
                else if(tab.getPosition()==2)
                {
                    caloriesStatsLayout.setVisibility(View.GONE);
                    weightStatsLayout.setVisibility(View.VISIBLE);
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

       /* this.daysFoodList = DaysFoodLab.get(getActivity()).getDaysFoodList();
        this.caloriesStatsAdapter = new CaloriesStatsAdapter(getActivity(), this.daysFoodList);
        this.caloriesListView.setAdapter(this.caloriesStatsAdapter);
        this.caloriesStatsAdapter.notifyDataSetChanged();
        updateCaloriesBarChart();
        this.weightStatsAdapter = new WeightStatsAdapter(getActivity(), this.daysFoodList);
        this.weightListView.setAdapter(this.weightStatsAdapter);
        this.weightStatsAdapter.notifyDataSetChanged();
        updateWeightBarChart();
        return inflate;*/
    /*List<BarEntry> arrayList;

    public void updateCaloriesBarChart() {
        arrayList = new ArrayList<>();
        final List<String> arrayList2 = new ArrayList<>();
        List<DaysFoodModel> daysFoodList2 = DaysFoodLab.get(getActivity()).getDaysFoodList();
        for (int i = 0; i < daysFoodList2.size(); i++) {
            arrayList.add(new BarEntry((float) i, (float) ((DaysFoodModel) daysFoodList2.get(i)).getTotalCalories()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM");
            Calendar instance = Calendar.getInstance();
            instance.setTime(((DaysFoodModel) daysFoodList2.get(i)).getDate());
            arrayList2.add(simpleDateFormat.format(instance.getTime()));
        }
        BarData barData = new BarData(new BarDataSet(arrayList, "Calories"));
        barData.setBarWidth(0.9f);
        this.caloriesBarChart.setData(barData);
        this.caloriesBarChart.setFitBars(true);
        this.caloriesBarChart.invalidate();
        this.caloriesBarChart.moveViewToX((float) daysFoodList2.size());
        XAxis xAxis = this.caloriesBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float f, AxisBase axisBase) {
                return (f < 0.0f || f > ((float) (arrayList2.size() + -1))) ? "" : arrayList2.get((int) f);
            }
        });
        BarDataSet barDataSet = new BarDataSet(arrayList, "cals");
        this.caloriesBarChart.getAxisRight().setDrawLabels(false);
        this.caloriesBarChart.getDescription().setText("");
        BarData barData2 = new BarData(barDataSet);
        final DecimalFormat decimalFormat = new DecimalFormat("#.#");
        barData2.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return decimalFormat.format((double) f);
            }
        });
        this.caloriesBarChart.setData(barData);
        this.caloriesBarChart.setVisibleXRangeMaximum(15.0f);
    }
    public void updateWeightBarChart() {
        arrayList = new ArrayList<>();
        final List<String> arrayList2 = new ArrayList<>();
        List<DaysFoodModel> daysFoodList2 = DaysFoodLab.get(getActivity()).getDaysFoodList();
        for (int i = 0; i < daysFoodList2.size(); i++) {
            arrayList.add(new BarEntry((float) i, (float) ((DaysFoodModel) daysFoodList2.get(i)).getWeight()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM");
            Calendar instance = Calendar.getInstance();
            instance.setTime(((DaysFoodModel) daysFoodList2.get(i)).getDate());
            arrayList2.add(simpleDateFormat.format(instance.getTime()));
        }
        BarData barData = new BarData(new BarDataSet(arrayList, "kg / lbs"));
        barData.setBarWidth(0.9f);
        this.weightBarChart.setData(barData);
        this.weightBarChart.setFitBars(true);
        this.weightBarChart.invalidate();
        this.weightBarChart.moveViewToX((float) daysFoodList2.size());
        XAxis xAxis = this.weightBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float f, AxisBase axisBase) {
                return (f < 0.0f || f > ((float) (arrayList2.size() + -1))) ? "" : arrayList2.get((int) f);
            }
        });
        BarDataSet barDataSet = new BarDataSet(arrayList, "weight");
        this.weightBarChart.getAxisRight().setDrawLabels(false);
        this.weightBarChart.getDescription().setText("");
        BarData barData2 = new BarData(barDataSet);
        final DecimalFormat decimalFormat = new DecimalFormat("#.#");
        barData2.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return decimalFormat.format((double) f);
            }
        });
        this.weightBarChart.setData(barData);
        this.weightBarChart.setVisibleXRangeMaximum(15.0f);
    }
*/
