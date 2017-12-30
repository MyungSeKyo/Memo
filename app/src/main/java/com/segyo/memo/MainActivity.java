package com.segyo.memo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listView = null;
    private ListViewAdapter listViewAdapter = null;
    private ImageButton menuButton = null;
    private ImageButton searchButton = null;
    private FloatingActionButton newMemoButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        menuButton = findViewById(R.id.menu_button);
        searchButton = findViewById(R.id.search_button);
        newMemoButton = findViewById(R.id.floating_button);

        listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);

        listViewAdapter.addItem("컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");
        listViewAdapter.addItem("컨텐츠","시간");

        newMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
