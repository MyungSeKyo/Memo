package com.segyo.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sekyo on 2017-12-30.
 */

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> items = new ArrayList<ListViewItem>() ;

    public void addItem(String content, String time){
        ListViewItem listViewItem = new ListViewItem();
        listViewItem.setContent(content);
        listViewItem.setTime(time);
        items.add(listViewItem);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recycler_view_item_layout, parent, false);
        }

        TextView contentTextView = convertView.findViewById(R.id.content_text_view);
        TextView timeTextView = convertView.findViewById(R.id.time_text_view);

        contentTextView.setText(items.get(position).getContent());
        timeTextView.setText(items.get(position).getTime());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }
}
