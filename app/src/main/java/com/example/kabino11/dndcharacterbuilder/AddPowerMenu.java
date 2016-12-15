package com.example.kabino11.dndcharacterbuilder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import Actions.PowerAbility;
import Player.PlayerCharacter;

public class AddPowerMenu extends AppCompatActivity {
    private static final int POWER_ADD_REQUEST = 3000;

    private ArrayList<PowerAbility> powers;
    private ArrayList<PowerAbility> displayList;
    private PlayerCharacter player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_power_menu);

        displayList = new ArrayList<>();

        player = getIntent().getParcelableExtra("PLAYER");
        ArrayList<PowerAbility> playerPowers = player.getPowers();

        powers = null;
        InputStream is = getResources().openRawResource(R.raw.powers);
        Gson gson = new Gson();
        Type pType = new TypeToken<ArrayList<PowerAbility>>(){}.getType();
        Reader reader = new InputStreamReader(is);
        powers = gson.fromJson(reader, pType);

        for(PowerAbility power : playerPowers) {
            powers.remove(power);
        }

        for(PowerAbility power : powers) {
            if(player.getMyClass().getName().equals(power.getPclass())) {
                displayList.add(power);
            }
        }

        ArrayList<String> names = new ArrayList<>();
        for(PowerAbility power : displayList) {
            names.add(power.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,names);

        ListView powerList = (ListView) findViewById(R.id.power_add_list);
        powerList.setAdapter(adapter);

        powerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AddPowerMenu.this, PowerView.class);
                intent.putExtra("POWER", displayList.get(position));
                intent.putExtra("MSG", "ADD");
                startActivityForResult(intent, POWER_ADD_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == POWER_ADD_REQUEST) {
            Intent returnData = new Intent();
            returnData.putExtra("ADDED", data.getParcelableExtra("ADDED"));
            setResult(Activity.RESULT_OK, returnData);
            finish();
        }
    }
}
