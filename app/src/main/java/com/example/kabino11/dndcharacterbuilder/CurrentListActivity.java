package com.example.kabino11.dndcharacterbuilder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Actions.Feat;
import Actions.PassiveAbility;
import Actions.PowerAbility;
import Player.PlayerCharacter;

public class CurrentListActivity extends AppCompatActivity {
    private static final int ADD_ID = 2000;

    private Feat selectedFeat;
    private PowerAbility selectedPower;

    private PlayerCharacter player;

    private String listType;
    private boolean add_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_list);

        player = getIntent().getParcelableExtra("PLAYER");

        listType = getIntent().getStringExtra("LIST");
        String mode_msg = getIntent().getStringExtra("MSG");

        Button addBtn = (Button) findViewById(R.id.cur_list_add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add_mode) {
                    Intent intent = null;

                    switch(listType) {
                        case "FEATS":
                            intent = new Intent(CurrentListActivity.this, AddFeatMenu.class);

                            break;
                        case "POWERS":
                            if(player.getMyClass() == null) {
                                Toast.makeText(CurrentListActivity.this, "Please select a class first.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            intent = new Intent(CurrentListActivity.this, AddPowerMenu.class);
                            break;
                    }

                    if(intent != null) {
                        intent.putExtra("PLAYER", player);
                        startActivityForResult(intent, ADD_ID);
                    }
                }
            }
        });

        if(mode_msg != null && mode_msg.equals("ADD")) {
            add_mode = true;
        }
        else {
            add_mode = false;
            addBtn.setClickable(false);
            addBtn.setVisibility(View.GONE);
        }

        final ArrayList<String> items = new ArrayList<>();

        switch(listType) {
            case "PASSIVES":
                ArrayList<PassiveAbility> ab = player.getPassives();
                for(PassiveAbility passive : ab) {
                    items.add(passive.getName());
                }
                break;
            case "POWERS":
                ArrayList<PowerAbility> powers = player.getPowers();
                for(PowerAbility power : powers) {
                    items.add(power.getName());
                }
                break;
            case "FEATS":
                ArrayList<Feat> feats = player.getFeats();
                for(Feat feat : feats) {
                    items.add(feat.getName());
                }
                break;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,items);
        ListView lv = (ListView) findViewById(R.id.cur_list);

        lv.setAdapter(adapter);

        final Context activityContext = this;

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = items.get(position);

                Intent intent = null;

                switch(listType) {
                    case "PASSIVES":
                        PassiveAbility selected_passive = null;
                        ArrayList<PassiveAbility> passives = player.getPassives();

                        for(PassiveAbility passive : passives) {
                            if(passive.getName().equals(name)) {
                                selected_passive = passive;
                                break;
                            }
                        }

                        if(selected_passive != null) {
                            intent = new Intent(activityContext, PassiveView.class);
                            intent.putExtra("PASSIVE", selected_passive);
                        }

                        break;
                    case "POWERS":
                        selectedPower = null;
                        ArrayList<PowerAbility> powers = player.getPowers();

                        for(PowerAbility power : powers) {
                            if(power.getName().equals(name)) {
                                selectedPower = power;
                                break;
                            }
                        }

                        if(selectedPower != null) {
                            intent = new Intent(activityContext, PowerView.class);
                            intent.putExtra("POWER", selectedPower);
                        }

                        break;
                    case "FEATS":
                        selectedFeat = null;
                        ArrayList<Feat> feats = player.getFeats();

                        for(Feat feat : feats) {
                            if(feat.getName().equals(name)) {
                                selectedFeat = feat;
                                break;
                            }
                        }

                        if(selectedFeat != null) {
                            intent = new Intent(activityContext, FeatView.class);
                            intent.putExtra("FEAT", selectedFeat);
                        }

                        break;
                }

                if(intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == ADD_ID) {
            Intent returnData = new Intent();
            returnData.putExtra("ADDED", data.getParcelableExtra("ADDED"));
            setResult(Activity.RESULT_OK, returnData);
            finish();
        }
    }
}
