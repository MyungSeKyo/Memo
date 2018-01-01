package com.segyo.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList

/**
 * Created by sekyo on 2018-01-02.
 */
class ListViewAdapter : BaseAdapter() {
    private var items = ArrayList<ListViewItem>()

    fun setItems(items: ArrayList<ListViewItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val pos = position
        val context = parent.context

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.recycler_view_item_layout, parent, false)
        }

        val contentTextView = convertView!!.findViewById<TextView>(R.id.content_text_view)
        val timeTextView = convertView.findViewById<TextView>(R.id.time_text_view)

        contentTextView.text = items[position].content
        timeTextView.text = items[position].time

        return convertView
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): ListViewItem {
        return items[position]
    }
}