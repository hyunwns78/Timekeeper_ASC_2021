package org.techtown.tab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    private ArrayList<Item> itemList;
    private Context context;
    private Intent intent;
    private BarChart barChart;
    ImageView imageView;

    public Myadapter(ArrayList<Item> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int position) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.actView.setText(itemList.get(position).getItem_activity());
        holder.timeView.setText(itemList.get(position).getItem_time());
    }

    public int getItemCount(){
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView timeView;
        public TextView actView;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            timeView = itemView.findViewById(R.id.time);
            actView = itemView.findViewById(R.id.activity);
        }
    }
}
