package com.segyo.memo

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ListView

/**
 * Created by sekyo on 2018-01-02.
 */
class MainActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var listViewAdapter: ListViewAdapter? = null
    private var menuButton: ImageButton? = null
    private var searchButton: ImageButton? = null
    private var newMemoButton: FloatingActionButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list_view)
        menuButton = findViewById(R.id.menu_button)
        searchButton = findViewById(R.id.search_button)
        newMemoButton = findViewById(R.id.floating_button)

        listViewAdapter = ListViewAdapter()
        listView!!.adapter = listViewAdapter

        listView!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            intent.putExtra("time", listViewAdapter!!.getItem(i).time)
            intent.putExtra("content", listViewAdapter!!.getItem(i).content)
            startActivity(intent)
        }

        newMemoButton!!.setOnClickListener {
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            startActivity(intent)
        }
        searchButton!!.setOnClickListener { }
        menuButton!!.setOnClickListener { }
    }

    public override fun onResume() {
        super.onResume()
        setListView()
    }

    private fun setListView() {
        val dbHelper = DBHelper.getInstance(applicationContext)
        listViewAdapter!!.setItems(dbHelper!!.memos)
    }
}