package org.techtown.tab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    private ArrayList<Item> itemList;
    private Context context;
    private Intent intent;



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
        holder.ing.getLayoutParams().width = itemList.get(position).getItem_min();
        holder.pad.getLayoutParams().width = 1000-itemList.get(position).getItem_min();

    }

    public int getItemCount(){
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView timeView;
        public TextView actView;
        public TextView ing;
        public TextView pad;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            timeView = itemView.findViewById(R.id.time);
            actView = itemView.findViewById(R.id.activity);
            ing = itemView.findViewById(R.id.ing_bar);
            pad = itemView.findViewById(R.id.pad_bar);
        }
    }
}
