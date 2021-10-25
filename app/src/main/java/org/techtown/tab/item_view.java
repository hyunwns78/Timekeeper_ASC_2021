package org.techtown.tab;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class item_view extends fragment_main{

    ArrayList<Item> itemList;
    Context context;
    TextView ing;
    TextView pad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.item_view, container, false);
        ing = view.findViewById(R.id.ing_bar);
        pad = view.findViewById(R.id.pad_bar);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
