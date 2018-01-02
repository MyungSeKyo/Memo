package com.segyo.memo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var listViewAdapter:ListViewAdapter = ListViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RxTextView.textChanges(editText)
                .subscribe { keyword->
                    setListView(keyword.toString())
                 }

        editText.setOnTouchListener{view, event->
            true
        }

        listView.adapter = listViewAdapter;
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            intent.putExtra("time", listViewAdapter.getItem(i).time)
            intent.putExtra("content", listViewAdapter.getItem(i).content)
            startActivity(intent)
        }

        floatingButton.setOnClickListener {
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            startActivity(intent)
        }
        searchButton.setOnClickListener {
            editText.setText("");
            editText.requestFocus()
            var inputManager =  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(editText,0)
        }
        menuButton.setOnClickListener {
            AlertDialog.Builder(this@MainActivity)
                    .setTitle("초기화")
                    .setMessage("어플리케이션을 초기화하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("초기화") { dialog, id ->
                        val dbHelper:DBHelper? = DBHelper.getInstance(applicationContext)
                        dbHelper!!.resetDB()
                        setListView()
                    }
                    .setNegativeButton("취소"){ dialog, id -> dialog.cancel()}
                    .create().show() }
    }

    public override fun onResume() {
        super.onResume()
        editText.setText("메모")
        editText.clearFocus()
        setListView()
    }
    private fun setListView(keyword:String){
        val dbHelper:DBHelper? = DBHelper.getInstance(applicationContext)
        listViewAdapter.setItems(dbHelper!!.getMemos(keyword))
    }
    private fun setListView() {
        val dbHelper:DBHelper? = DBHelper.getInstance(applicationContext)
        listViewAdapter.setItems(dbHelper!!.getMemos())

    }
}