package com.example.kabino11.dndcharacterbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Actions.PassiveAbility;

public class PassiveView extends AppCompatActivity {

    private PassiveAbility passive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passive_view);

        passive = getIntent().getParcelableExtra("PASSIVE");

        TextView nmView = (TextView) findViewById(R.id.passive_name_view);
        nmView.setText(passive.getName());

        TextView dscView = (TextView) findViewById(R.id.passive_desc_view);
        dscView.setText(passive.getDescription());
    }
}
