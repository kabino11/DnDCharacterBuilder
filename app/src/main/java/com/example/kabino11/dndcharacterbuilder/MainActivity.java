package com.example.kabino11.dndcharacterbuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import Actions.Feat;
import Actions.PowerAbility;
import DnDClass.DnDClassFactory;
import Player.PlayerCharacter;
import Race.RaceFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PASSIVE_REQUEST = 1331;
    private static final int POWER_REQUEST = 1332;
    private static final int FEAT_REQUEST = 1333;
    private static final int SKILL_REQUEST = 1334;
    private static final int CLASS_REQUEST = 1335;
    private static final int RACE_REQUEST = 1336;
    private static final int STAT_REQUEST = 1337;

    private int skills_selected;

    private PlayerCharacter player;
    private RaceFactory raceFactory;
    private DnDClassFactory dndClassFactory;

    private Feat selectedFeat;

    private ArrayList<PowerAbility> atWillsSelected;
    private PowerAbility encounterSelected;
    private PowerAbility dailySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        raceFactory = new RaceFactory();
        dndClassFactory = new DnDClassFactory();

        player = new PlayerCharacter();

        selectedFeat = null;
        atWillsSelected = new ArrayList<>();
        encounterSelected = null;
        dailySelected = null;

        FillTextDisplay();

        Button passiveBtn = (Button) findViewById(R.id.passive_btn);
        passiveBtn.setOnClickListener(this);

        Button pwrBtn = (Button) findViewById(R.id.pwr_btn);
        pwrBtn.setOnClickListener(this);

        Button ftBtn = (Button) findViewById(R.id.feat_btn);
        ftBtn.setOnClickListener(this);

        Button stBtn = (Button) findViewById(R.id.stats_btn);
        stBtn.setOnClickListener(this);

        Button raceBtn = (Button) findViewById(R.id.race_btn);
        raceBtn.setOnClickListener(this);

        Button classBtn = (Button) findViewById(R.id.class_btn);
        classBtn.setOnClickListener(this);

        Button skillsBtn = (Button) findViewById(R.id.skills_btn);
        skillsBtn.setOnClickListener(this);
    }

    private void setPlayerSkills(List<String> selected) {
        player.UndoSpecial();
        player.ClearSkills();

        for(String skill : selected) {
            switch (skill) {
                case "Acrobatics":
                    player.setAcrobaticsTrnd(true);
                    break;
                case "Arcana":
                    player.setArcanaTrnd(true);
                    break;
                case "Athletics":
                    player.setAthleticsTrnd(true);
                    break;
                case "Bluff":
                    player.setBluffTrnd(true);
                    break;
                case "Diplomacy":
                    player.setDiplomacyTrnd(true);
                    break;
                case "Dungeoneering":
                    player.setDungeoneeringTrnd(true);
                    break;
                case "Endurance":
                    player.setEnduranceTrnd(true);
                    break;
                case "Heal":
                    player.setHealTrnd(true);
                    break;
                case "History":
                    player.setHistoryTrnd(true);
                    break;
                case "Insight":
                    player.setInsightTrnd(true);
                    break;
                case "Intimidate":
                    player.setIntimidateTrnd(true);
                    break;
                case "Nature":
                    player.setNatureTrnd(true);
                    break;
                case "Perception":
                    player.setPerceptionTrnd(true);
                    break;
                case "Religion":
                    player.setReligionTrnd(true);
                    break;
                case "Stealth":
                    player.setStealthTrnd(true);
                    break;
                case "Streetwise":
                    player.setStreetwiseTrnd(true);
                    break;
                case "Thievery":
                    player.setThieveryTrnd(true);
                    break;
            }
        }

        player.calcSkills();
        player.calcDefStats();
        player.ReApplySpecial();
    }

    private void FillTextDisplay() {
        TextView view = (TextView) findViewById(R.id.race_view);
        if(player.getRace() != null) {
            view.setText("Race: " + player.getRace().getName());
        }
        else {
            view.setText("Race: none");
        }

        view = (TextView) findViewById(R.id.player_class_view);
        if(player.getMyClass() != null) {
            view.setText("Class: " + player.getMyClass().getName());
        }
        else {
            view.setText("Class: none");
        }

        view = (TextView) findViewById(R.id.str_view);
        view.setText("STR: " + player.getSTR());

        view = (TextView) findViewById(R.id.con_view);
        view.setText("CON: " + player.getCON());

        view = (TextView) findViewById(R.id.dex_view);
        view.setText("DEX: " + player.getDEX());

        view = (TextView) findViewById(R.id.int_view);
        view.setText("INT: " + player.getINT());

        view = (TextView) findViewById(R.id.wis_view);
        view.setText("WIS: " + player.getWIS());

        view = (TextView) findViewById(R.id.cha_view);
        view.setText("CHA: " + player.getCHA());

        view = (TextView) findViewById(R.id.ac_view);
        view.setText("AC: " + player.getAC());

        view = (TextView) findViewById(R.id.fort_view);
        view.setText("FORT: " + player.getFORT());

        view = (TextView) findViewById(R.id.ref_view);
        view.setText("REF: " + player.getREF());

        view = (TextView) findViewById(R.id.will_view);
        view.setText("WILL: " + player.getWILL());

        view = (TextView) findViewById(R.id.acrobatics_view);
        view.setText("Acrobatics: " + player.getAcrobatics());

        view = (TextView) findViewById(R.id.arcana_view);
        view.setText("Arcana: " + player.getArcana());

        view = (TextView) findViewById(R.id.athletics_view);
        view.setText("Athletics: " + player.getAthletics());

        view = (TextView) findViewById(R.id.bluff_view);
        view.setText("Bluff: " + player.getBluff());

        view = (TextView) findViewById(R.id.diplomacy_view);
        view.setText("Diplomacy: " + player.getDiplomacy());

        view = (TextView) findViewById(R.id.dungeoneering_view);
        view.setText("Dungeoneering: " + player.getDungeoneering());

        view = (TextView) findViewById(R.id.endurance_view);
        view.setText("Endurance: " + player.getEndurance());

        view = (TextView) findViewById(R.id.heal_view);
        view.setText("Heal: " + player.getHeal());

        view = (TextView) findViewById(R.id.history_view);
        view.setText("History: " + player.getHistory());

        view = (TextView) findViewById(R.id.insight_view);
        view.setText("Insight: " + player.getInsight());

        view = (TextView) findViewById(R.id.intimidate_view);
        view.setText("Intimidate: " + player.getIntimidate());

        view = (TextView) findViewById(R.id.nature_view);
        view.setText("Nature: " + player.getNature());

        view = (TextView) findViewById(R.id.perception_view);
        view.setText("Perception: " + player.getPerception());

        view = (TextView) findViewById(R.id.religion_view);
        view.setText("Religion: " + player.getReligion());

        view = (TextView) findViewById(R.id.stealth_view);
        view.setText("Stealth: " + player.getStealth());

        view = (TextView) findViewById(R.id.streetwise_view);
        view.setText("Streetwise: " + player.getStreetwise());

        view = (TextView) findViewById(R.id.thievery_view);
        view.setText("Thievery: " + player.getThievery());

        view = (TextView) findViewById(R.id.acrobatics_trnd);
        if(player.isAcrobaticsTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.arcana_trnd);
        if(player.isArcanaTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.athletics_trnd);
        if(player.isAthleticsTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.bluff_trnd);
        if(player.isBluffTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.diplomacy_trnd);
        if(player.isDiplomacyTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.dungeoneering_trnd);
        if(player.isDungeoneeringTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.endurance_trnd);
        if(player.isEnduranceTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.heal_trnd);
        if(player.isHealTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.history_trnd);
        if(player.isHistoryTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.insight_trnd);
        if(player.isInsightTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.intimidate_trnd);
        if(player.isIntimidateTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.nature_trnd);
        if(player.isNatureTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.perception_trnd);
        if(player.isPerceptionTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.religion_trnd);
        if(player.isReligionTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.stealth_trnd);
        if(player.isStealthTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.streetwise_trnd);
        if(player.isStreetwiseTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }

        view = (TextView) findViewById(R.id.thievery_trnd);
        if(player.isThieveryTrnd()) {
            view.setText("TRAINED");
        }
        else {
            view.setText("UNTRAINED");
        }


    }

    @Override
    public void onClick(View v) {
        int id = 0;

        Intent intent = null;

        switch(v.getId()) {
            case R.id.stats_btn:
                intent = new Intent(MainActivity.this, StatsConfigMenu.class);
                intent.putExtra("PLAYER", player);
                id = STAT_REQUEST;
                break;
            case R.id.race_btn:
                intent = new Intent(MainActivity.this, RaceSelectMenu.class);
                id = RACE_REQUEST;
                break;
            case R.id.class_btn:
                intent = new Intent(MainActivity.this, MyClassSelectMenu.class);
                id = CLASS_REQUEST;
                break;
            case R.id.skills_btn:
                if(player.getMyClass() == null) {
                    Toast.makeText(MainActivity.this, "You need to pick a class first", Toast.LENGTH_SHORT).show();
                    return;
                }

                intent = new Intent(MainActivity.this, SkillSelectMenu.class);
                intent.putExtra("VALID_SKILLS", new ArrayList<String>(Arrays.asList(player.getMyClass().getSkillOptions())));
                intent.putExtra("NUM_TO_SELECT", player.getMyClass().getNumToSelect());
                id = SKILL_REQUEST;
                break;
            case R.id.passive_btn:
                intent = new Intent(MainActivity.this, CurrentListActivity.class);
                intent.putExtra("PLAYER", player);
                intent.putExtra("LIST", "PASSIVES");
                id = PASSIVE_REQUEST;
                break;
            case R.id.pwr_btn:
                intent = new Intent(MainActivity.this, CurrentListActivity.class);
                intent.putExtra("PLAYER", player);
                intent.putExtra("LIST", "POWERS");
                intent.putExtra("MSG", "ADD");
                id = POWER_REQUEST;
                break;
            case R.id.feat_btn:
                intent = new Intent(MainActivity.this, CurrentListActivity.class);
                intent.putExtra("PLAYER", player);
                intent.putExtra("LIST", "FEATS");
                intent.putExtra("MSG", "ADD");
                id = FEAT_REQUEST;
                break;
        }

        if(intent != null) startActivityForResult(intent, id);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) return;

        switch(requestCode) {
            case SKILL_REQUEST:
                List<String> selected = data.getStringArrayListExtra("SELECTED");
                if(selected.size() > player.getMyClass().getNumToSelect()) {
                    selected = selected.subList(0, player.getMyClass().getNumToSelect());
                }
                setPlayerSkills(selected);
                break;
            case CLASS_REQUEST:
                player.ClearSkills();
                player.setMyClass(dndClassFactory.getPlayerClass(data.getStringExtra("CLASS")));
                break;
            case RACE_REQUEST:
                player.setRace(raceFactory.getRace(data.getStringExtra("RACE")));
                break;
            case POWER_REQUEST:
                PowerAbility power = data.getParcelableExtra("ADDED");

                switch(power.getPwrType()) {
                    case "At-Will":
                        if(atWillsSelected.size() >= 2) {
                            player.removePower(atWillsSelected.get(0));
                            atWillsSelected.remove(0);
                        }

                        atWillsSelected.add(power);
                        break;
                    case "Encounter":
                        if(encounterSelected != null) {
                            player.removePower(encounterSelected);
                        }

                        encounterSelected = power;
                        break;
                    case "Daily":
                        if(dailySelected != null) {
                            player.removePower(dailySelected);
                        }

                        dailySelected = power;
                        break;
                }

                player.addPower(power);

                break;
            case FEAT_REQUEST:
                if(selectedFeat != null) {
                    player.removeFeat(selectedFeat);
                }

                selectedFeat = data.getParcelableExtra("ADDED");

                if(selectedFeat != null) {
                    player.addFeat(selectedFeat);
                }
            case STAT_REQUEST:
                player.setSTR(data.getIntExtra("STR", 0));
                player.setCON(data.getIntExtra("CON", 0));
                player.setDEX(data.getIntExtra("DEX", 0));
                player.setINT(data.getIntExtra("INT", 0));
                player.setWIS(data.getIntExtra("WIS", 0));
                player.setCHA(data.getIntExtra("CHA", 0));

                player.calcSkills();
                player.calcDefStats();
                player.ReApplySpecial();
                break;
        }

        FillTextDisplay();
    }
}
