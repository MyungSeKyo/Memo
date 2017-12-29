package com.segyo.memo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sekyo on 2017-12-29.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {
    ArrayList<RecyclerViewItem> items;

    public RecyclerViewAdapter(){
        this.items = new ArrayList<RecyclerViewItem>();
    }

    public void addItem(RecyclerViewItem recyclerViewItem){
        items.add(recyclerViewItem);
        notifyItemInserted(items.size() - 1);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.content.setText(items.get(position).getContent());
        holder.time.setText(items.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView content = null;
        private TextView time = null;

        public ItemViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content_text_view);
            content = itemView.findViewById(R.id.time_text_view);
        }
    }
}
