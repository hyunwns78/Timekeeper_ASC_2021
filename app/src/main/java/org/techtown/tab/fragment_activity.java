package org.techtown.tab;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
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

    public fragment_activity() {
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

            }
        });

        TabLayout tlselect = (TabLayout) inflate.findViewById(R.id.tlselect);
        tlselect.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() == 0){
                    studyLayout.setVisibility(View.VISIBLE);
                    hobbyLayout.setVisibility(View.GONE);
                    restLayout.setVisibility(View.GONE);
                }
                else if(tab.getPosition()==1)
                {
                    studyLayout.setVisibility(View.GONE);
                    hobbyLayout.setVisibility(View.VISIBLE);
                    restLayout.setVisibility(View.GONE);
                }
                else if(tab.getPosition()==2)
                {
                    studyLayout.setVisibility(View.GONE);
                    hobbyLayout.setVisibility(View.GONE);
                    restLayout.setVisibility(View.VISIBLE);
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
