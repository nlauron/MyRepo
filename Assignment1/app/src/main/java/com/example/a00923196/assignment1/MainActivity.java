package com.example.a00923196.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] colors = getResources().getStringArray(R.array.color);
        ListView lv = (ListView) findViewById(R.id.color_list);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.listview, colors);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent color = new Intent(MainActivity.this, ImageActivity.class);
                color.putExtra("color", position);
                startActivity(color);
            }
        });
    }
}
