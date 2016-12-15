package com.example.kabino11.dndcharacterbuilder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Actions.PowerAbility;

public class PowerView extends AppCompatActivity {
    private PowerAbility power;

    private boolean add_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_view);

        power = getIntent().getParcelableExtra("POWER");

        Button pwrBtn = (Button) findViewById(R.id.power_add_btn);
        pwrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add_mode) {
                    Intent returnData = new Intent();
                    returnData.putExtra("ADDED", power);
                    setResult(Activity.RESULT_OK, returnData);
                    finish();
                }
            }
        });

        String add_msg = getIntent().getStringExtra("MSG");
        if(add_msg != null && add_msg.equals("ADD")) {
            add_mode = true;
        }
        else {
            add_mode = false;
            pwrBtn.setVisibility(View.GONE);
            pwrBtn.setClickable(false);
        }

        TextView nmView = (TextView) findViewById(R.id.power_name_view);
        nmView.setText(power.getName());

        TextView clsView = (TextView) findViewById(R.id.power_class_view);
        clsView.setText("lv. " + power.getLevel() + " " + power.getPclass());

        TextView elmView = (TextView) findViewById(R.id.power_elem_type_view);
        elmView.setText(power.getDmgType());

        TextView pwrView = (TextView) findViewById(R.id.power_abil_type_view);
        pwrView.setText(power.getPwrType());

        TextView acnView = (TextView) findViewById(R.id.power_acn_type_view);
        acnView.setText(power.getAcnType());

        TextView rngView = (TextView) findViewById(R.id.power_range_view);
        rngView.setText(power.getRange());

        TextView trgtView = (TextView) findViewById(R.id.power_target_view);
        trgtView.setText(power.getTarget());

        TextView rollView = (TextView) findViewById(R.id.power_roll_view);
        if(power.getRollVS().equals("")) {
            rollView.setText("");
        }
        else {
            rollView.setText("Attack: " + power.getRollVS());
        }

        TextView hitView = (TextView) findViewById(R.id.power_hit_view);
        if(power.getHit().equals("")) {
            hitView.setText("");
        }
        else {
            hitView.setText("Hit: " + power.getHit());
        }

        TextView missView = (TextView) findViewById(R.id.power_miss_view);
        if(power.getMiss().equals("")) {
            missView.setText("");
        }
        else {
            missView.setText("Miss: " + power.getMiss());
        }

        TextView effectView = (TextView)findViewById(R.id.power_effect_view);
        if(power.getEffect().equals("")) {
            effectView.setText("");
        }
        else {
            effectView.setText("Effect: " + power.getEffect());
        }

        TextView spView = (TextView) findViewById(R.id.power_special_view);
        if(power.getSpecial().equals("")) {
            spView.setText("");
        }
        else {
            spView.setText("Special: " + power.getSpecial());
        }

        TextView upView = (TextView) findViewById(R.id.power_upgrade_view);
        if(power.getUpgrade().equals("")) {
            upView.setText("");
        }
        else {
            upView.setText("Upgrade: " + power.getUpgrade());
        }
    }
}
