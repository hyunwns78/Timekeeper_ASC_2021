package org.techtown.tab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Listitem> listItems = new ArrayList<Listitem>();

    public ListViewAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }

        TextView txt_title = (TextView) convertView.findViewById(R.id.txt_title);
        Button btn_delete = (Button) convertView.findViewById(R.id.btn_delete);

        Listitem listitem = listItems.get(position);

        txt_title.setText(listitem.getname());

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItems.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public void addItem(String act_name){
        Listitem listitem = new Listitem();

        listitem.setname(act_name);

        listItems.add(listitem);
    }
}
