package com.example.kabino11.dndcharacterbuilder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import Race.RaceFactory;

public class RaceSelectMenu extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_select_menu);

        RaceFactory factory = new RaceFactory();

        List<String> items = Arrays.asList(factory.getOptions());

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,items);

        ListView raceView = (ListView) findViewById(R.id.race_list);
        raceView.setAdapter(adapter);

        raceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent resultData = new Intent();
                resultData.putExtra("RACE", adapter.getItem(position));
                setResult(Activity.RESULT_OK, resultData);
                finish();
            }
        });
    }


}
