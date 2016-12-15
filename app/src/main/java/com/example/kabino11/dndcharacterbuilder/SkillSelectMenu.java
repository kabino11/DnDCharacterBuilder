package com.example.kabino11.dndcharacterbuilder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SkillSelectMenu extends AppCompatActivity {

    private int num_to_select;
    private int num_selected;

    private ListView skillList;
    private List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_select);

        num_to_select = getIntent().getIntExtra("NUM_TO_SELECT", 0);
        num_selected = 0;

        Toast.makeText(this, "Please select " + num_to_select + " skills.", Toast.LENGTH_SHORT).show();

        items = getIntent().getStringArrayListExtra("VALID_SKILLS");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items);

        skillList = (ListView) findViewById(R.id.skill_list);
        skillList.setAdapter(adapter);

        skillList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray checked = skillList.getCheckedItemPositions();

                if(checked.valueAt(checked.keyAt(position))) {
                    if(num_selected < num_to_select) {
                        num_selected++;
                    }
                    else {
                        Toast.makeText(SkillSelectMenu.this, "Too many skills picked", Toast.LENGTH_SHORT).show();
                        skillList.setItemChecked(position, false);
                    }
                }
                else {
                    num_selected--;
                }
            }
        });

        Button doneBtn = (Button) findViewById(R.id.skills_done_btn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultData = new Intent();
                setResult(Activity.RESULT_OK, resultData);

                ArrayList<String> selected = new ArrayList<>();

                SparseBooleanArray checked = skillList.getCheckedItemPositions();
                for(int i = 0; i < checked.size(); i++) {
                    int key = checked.keyAt(i);

                    if(checked.get(key)) {
                        selected.add(items.get(key));
                    }
                }

                resultData.putExtra("SELECTED", selected);
                finish();
            }
        });
    }
}
