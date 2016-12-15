package com.example.kabino11.dndcharacterbuilder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Actions.Feat;

public class FeatView extends AppCompatActivity {
    private Feat feat;
    private boolean add_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feat_view);

        feat = getIntent().getParcelableExtra("FEAT");

        Button addBtn = (Button) findViewById(R.id.feat_add_btn);

        String add_msg = getIntent().getStringExtra("MSG");
        if(add_msg == null || add_msg.equals("DISPLAY")) {
            add_mode = false;
            addBtn.setClickable(false);
            addBtn.setVisibility(View.GONE);
        }
        else {
            add_mode = true;
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add_mode) {
                    Intent returnData = new Intent();
                    returnData.putExtra("ADDED", feat);
                    setResult(Activity.RESULT_OK, returnData);
                    finish();
                }
            }
        });

        TextView nameView = (TextView) findViewById(R.id.feat_name_view);
        nameView.setText(feat.getName());

        TextView bnftView = (TextView) findViewById(R.id.feat_benefit_view);
        bnftView.setText("Benefit: " + feat.getBenefit());

        TextView spclView = (TextView) findViewById(R.id.feat_special_view);
        if(feat.getSpecial().equals("")) {
            spclView.setText("");
        }
        else {
            spclView.setText("Special: " + feat.getSpecial());
        }

        TextView prqView = (TextView) findViewById(R.id.feat_prereq_view);
        if(feat.getPrereq().equals("")) {
            prqView.setText("");
        }
        else {
            prqView.setText("Prerequisites: " + feat.getPrereq());
        }
    }
}
