package com.example.kabino11.dndcharacterbuilder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import Player.PlayerCharacter;
import Race.Race;

public class StatsConfigMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private PlayerCharacter player;
    private int item_array[];

    private Spinner strSpin, conSpin, dexSpin, intSpin, wisSpin, chaSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_config_menu);

        strSpin = (Spinner) findViewById(R.id.stats_str_spin);
        conSpin = (Spinner) findViewById(R.id.stats_con_spin);
        dexSpin = (Spinner) findViewById(R.id.stats_dex_spin);
        intSpin = (Spinner) findViewById(R.id.stats_int_spin);
        wisSpin = (Spinner) findViewById(R.id.stats_wis_spin);
        chaSpin = (Spinner) findViewById(R.id.stats_cha_spin);

        strSpin.setOnItemSelectedListener(this);
        conSpin.setOnItemSelectedListener(this);
        dexSpin.setOnItemSelectedListener(this);
        intSpin.setOnItemSelectedListener(this);
        wisSpin.setOnItemSelectedListener(this);
        chaSpin.setOnItemSelectedListener(this);


        player = getIntent().getParcelableExtra("PLAYER");
        if(player.getRace() != null) {
            player.getRace().undoChanges(player);
        }

        item_array = getResources().getIntArray(R.array.stats);

        Button doneButton = (Button) findViewById(R.id.stats_done_button);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent resultData = new Intent();
                resultData.putExtra("STR", (Integer) strSpin.getSelectedItem());
                resultData.putExtra("CON", (Integer) conSpin.getSelectedItem());
                resultData.putExtra("DEX", (Integer) dexSpin.getSelectedItem());
                resultData.putExtra("INT", (Integer) intSpin.getSelectedItem());
                resultData.putExtra("WIS", (Integer) wisSpin.getSelectedItem());
                resultData.putExtra("CHA", (Integer) chaSpin.getSelectedItem());
                setResult(Activity.RESULT_OK, resultData);
                finish();
            }
        });

        spinSetup();
    }

    private void spinSetup() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0);
        for(int item : item_array) {
            items.add(item);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        strSpin.setAdapter(adapter);
        if(player.getSTR() != 0) {
            strSpin.setSelection(getIndex(strSpin, player.getSTR()));
        }

        conSpin.setAdapter(adapter);
        if(player.getCON() != 0) {
            conSpin.setSelection(getIndex(conSpin, player.getCON()));
        }

        dexSpin.setAdapter(adapter);
        if(player.getDEX() != 0) {
            dexSpin.setSelection(getIndex(dexSpin, player.getDEX()));
        }

        intSpin.setAdapter(adapter);
        if(player.getINT() != 0) {
            intSpin.setSelection(getIndex(intSpin, player.getINT()));
        }

        wisSpin.setAdapter(adapter);
        if(player.getWIS() != 0) {
            wisSpin.setSelection(getIndex(wisSpin, player.getWIS()));
        }

        chaSpin.setAdapter(adapter);
        if(player.getCHA() != 0) {
            chaSpin.setSelection(getIndex(chaSpin, player.getCHA()));
        }
    }

    private int getIndex(Spinner spin, int value) {
        int index = 0;

        for (int i=1;i<spin.getCount();i++){
            if (spin.getItemAtPosition(i).equals(value)){
                index = i;
            }
        }

        return index;
    }

    private boolean checkUnique(Spinner spin, int value) {
        if(value == 0) return true;

        ArrayList<Spinner> spinners = new ArrayList<>();
        spinners.add(strSpin);
        spinners.add(conSpin);
        spinners.add(dexSpin);
        spinners.add(intSpin);
        spinners.add(wisSpin);
        spinners.add(chaSpin);

        for(Spinner spinner : spinners) {
            if(spinner.equals(spin)) continue;

            if((Integer) spinner.getSelectedItem() == value) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent instanceof Spinner) {
            if(!checkUnique((Spinner) parent, (Integer) parent.getItemAtPosition(position))) {
                parent.setSelection(0);
                Toast.makeText(this, "You need to pick a unique value for each item", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
