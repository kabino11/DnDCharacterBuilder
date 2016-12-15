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

import Actions.Feat;
import Player.PlayerCharacter;

public class AddFeatMenu extends AppCompatActivity {
    private static final int FEAT_ADD_REQUEST = 3000;

    private ArrayList<Feat> feats;
    private ArrayList<Feat> displayList;
    private PlayerCharacter player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feat_menu);

        displayList = new ArrayList<>();

        player = getIntent().getParcelableExtra("PLAYER");
        ArrayList<Feat> playerFeats = player.getFeats();

        feats = null;
        InputStream is = getResources().openRawResource(R.raw.feats);
        Gson gson = new Gson();
        Type fType = new TypeToken<ArrayList<Feat>>(){}.getType();
        Reader reader = new InputStreamReader(is);
        feats = gson.fromJson(reader, fType);

        for(Feat feat : playerFeats) {
            feats.remove(feat);
        }

        for(Feat feat : feats) {
            if(player.canUse(feat)) {
                displayList.add(feat);
            }
        }

        ArrayList<String> names = new ArrayList<>();
        for(Feat feat : displayList) {
            names.add(feat.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,names);

        ListView featList = (ListView) findViewById(R.id.feat_add_list);
        featList.setAdapter(adapter);

        featList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AddFeatMenu.this, FeatView.class);
                intent.putExtra("FEAT", displayList.get(position));
                intent.putExtra("MSG", "ADD");
                startActivityForResult(intent, FEAT_ADD_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == FEAT_ADD_REQUEST) {
            Intent returnData = new Intent();
            returnData.putExtra("ADDED", data.getParcelableExtra("ADDED"));
            setResult(Activity.RESULT_OK, returnData);
            finish();
        }
    }
}
