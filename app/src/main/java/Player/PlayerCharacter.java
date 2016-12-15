package Player;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import Actions.Feat;
import Actions.PassiveAbility;
import Actions.PowerAbility;
import DnDClass.PlayerClass;
import Race.Race;

/**
 * Created by kabino11 on 12/8/16.
 */

public class PlayerCharacter implements Parcelable {
    private Race race;
    private PlayerClass myClass;

    private ArrayList<PassiveAbility> abilities;
    private ArrayList<PowerAbility> powers;
    private ArrayList<Feat> feats;

    private int STR;
    private int CON;
    private int DEX;
    private int INT;
    private int WIS;
    private int CHA;

    private int HP;
    private int surges;

    private int AC;
    private int FORT;
    private int REF;
    private int WILL;

    private int speed;

    private int acrobatics;
    private int arcana;
    private int athletics;
    private int bluff;
    private int diplomacy;
    private int dungeoneering;
    private int endurance;
    private int heal;
    private int history;
    private int insight;
    private int intimidate;
    private int nature;
    private int perception;
    private int religion;
    private int stealth;
    private int streetwise;
    private int thievery;

    private boolean acrobaticsTrnd;
    private boolean arcanaTrnd;
    private boolean athleticsTrnd;
    private boolean bluffTrnd;
    private boolean diplomacyTrnd;
    private boolean dungeoneeringTrnd;
    private boolean enduranceTrnd;
    private boolean healTrnd;
    private boolean historyTrnd;
    private boolean insightTrnd;
    private boolean intimidateTrnd;
    private boolean natureTrnd;
    private boolean perceptionTrnd;
    private boolean religionTrnd;
    private boolean stealthTrnd;
    private boolean streetwiseTrnd;
    private boolean thieveryTrnd;

    public PlayerCharacter() {
        race = null;
        myClass = null;

        abilities = new ArrayList<>();
        powers = new ArrayList<>();
        feats = new ArrayList<>();

        STR = 0;
        CON = 0;
        DEX = 0;
        INT = 0;
        WIS = 0;
        CHA = 0;

        HP = 0;
        surges = 0;

        AC = 0;
        FORT = 0;
        REF = 0;
        WILL = 0;

        speed = 0;

        acrobatics = 0;
        arcana = 0;
        athletics = 0;
        bluff = 0;
        diplomacy = 0;
        dungeoneering = 0;
        endurance = 0;
        heal = 0;
        history = 0;
        insight = 0;
        intimidate = 0;
        nature = 0;
        perception = 0;
        religion = 0;
        stealth = 0;
        streetwise = 0;
        thievery = 0;

        acrobaticsTrnd = false;
        arcanaTrnd = false;
        athleticsTrnd = false;
        bluffTrnd = false;
        diplomacyTrnd = false;
        dungeoneeringTrnd = false;
        enduranceTrnd = false;
        healTrnd = false;
        historyTrnd = false;
        insightTrnd = false;
        intimidateTrnd = false;
        natureTrnd = false;
        perceptionTrnd = false;
        religionTrnd = false;
        stealthTrnd = false;
        streetwiseTrnd = false;
        thieveryTrnd = false;
    }

    public void calcDefStats() {
        if(STR == 0 || CON == 0 || DEX == 0 || INT == 0 || WIS == 0 || CHA == 0) return;

        AC = 10 + (DEX >= INT ? getMod(DEX) : getMod(INT));
        FORT = 10 + (STR >= CON ? getMod(STR) : getMod(CON));
        REF = 10 + (DEX >= INT ? getMod(DEX) : getMod(INT));
        WILL = 10 + (WIS >= CHA ? getMod(WIS) : getMod(CHA));
    }

    public void calcSkills() {
        if (STR == 0 || CON == 0 || DEX == 0 || INT == 0 || WIS == 0 || CHA == 0) return;

        acrobatics = getMod(DEX) + trndVal(acrobaticsTrnd);
        arcana = getMod(INT) + trndVal(arcanaTrnd);
        athletics = getMod(STR) + trndVal(athleticsTrnd);
        bluff = getMod(CHA) + trndVal(bluffTrnd);
        diplomacy = getMod(CHA) + trndVal(diplomacyTrnd);
        dungeoneering = getMod(WIS) + trndVal(dungeoneeringTrnd);
        endurance = getMod(CON) + trndVal(enduranceTrnd);
        heal = getMod(WIS) + trndVal(healTrnd);
        history = getMod(INT) + trndVal(historyTrnd);
        insight = getMod(WIS) + trndVal(insightTrnd);
        intimidate = getMod(CHA) + trndVal(intimidateTrnd);
        nature = getMod(WIS) + trndVal(natureTrnd);
        perception = getMod(WIS) + trndVal(perceptionTrnd);
        religion = getMod(INT) + trndVal(religionTrnd);
        stealth = getMod(DEX) + trndVal(stealthTrnd);
        streetwise = getMod(CHA) + trndVal(streetwiseTrnd);
        thievery = getMod(DEX) + trndVal(thieveryTrnd);
    }

    public boolean checkStatsNotSet() {
        return STR == 0 || CON == 0 || DEX == 0 || INT == 0 || WIS == 0 || CHA == 0;
    }

    private int trndVal(boolean trained) { return (trained ? 5 : 0); }

    private int getMod(int value) {
        return value / 2 - 5;
    }

    public int getSTRMod() {
        return getMod(STR);
    }

    public int getCONMod() {
        return getMod(CON);
    }

    public int getDEXMod() {
        return getMod(DEX);
    }

    public int getINTMod() {
        return getMod(INT);
    }

    public int getWISMod() {
        return getMod(WIS);
    }

    public int getCHAMod() {
        return getMod(CHA);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        if(race == null) return;

        if(this.race != null) {
            this.race.undoChanges(this);
        }

        this.race = race;
        race.commitChanges(this);

        if(myClass != null) {
            myClass.commitChanges(this);
        }
    }

    public PlayerClass getMyClass() {
        return myClass;
    }

    public void setMyClass(PlayerClass myClass) {
        if(myClass == null) return;

        if(this.myClass != null) {
            this.myClass.undoChanges(this);
        }

        this.myClass = myClass;

        myClass.commitChanges(this);
    }

    public void UndoSpecial() {
        if(this.race != null) {
            this.race.undoChanges(this);
        }

        if(this.myClass != null) {
            this.myClass.undoChanges(this);
        }
    }

    public void ReApplySpecial() {
        if(this.race != null) {
            this.race.commitChanges(this);
        }

        if(this.myClass != null) {
            this.myClass.commitChanges(this);
        }
    }

    public void ClearSkills() {
        setAcrobaticsTrnd(false);
        setArcanaTrnd(false);
        setAthleticsTrnd(false);
        setBluffTrnd(false);
        setDiplomacyTrnd(false);
        setDungeoneeringTrnd(false);
        setEnduranceTrnd(false);
        setHealTrnd(false);
        setHistoryTrnd(false);
        setInsightTrnd(false);
        setIntimidateTrnd(false);
        setNatureTrnd(false);
        setPerceptionTrnd(false);
        setReligionTrnd(false);
        setStealthTrnd(false);
        setStreetwiseTrnd(false);
        setThieveryTrnd(false);
    }

    public void addPassive(PassiveAbility passive) {
        if(!abilities.contains(passive)) {
            abilities.add(passive);
        }
    }

    public void removePassive(PassiveAbility passive) {
        abilities.remove(passive);
    }

    public ArrayList<PassiveAbility> getPassives() {
        return abilities;
    }

    public void addPower(PowerAbility ab) {
        if(!powers.contains(ab)) {
            powers.add(ab);
        }
    }

    public void removePower(PowerAbility ab) {
        powers.remove(ab);
    }

    public ArrayList<PowerAbility> getPowers() { return powers; }

    public boolean canUse(Feat in) {
        String prereqs = in.getPrereq();

        String req_list[] = prereqs.split(",");

        for(String req : req_list) {
            String req_args[] = req.split(":");

            switch(req_args[0]) {
                case "STR":
                    if(Integer.parseInt(req_args[1]) > STR) return false;
                    break;
                case "CON":
                    if(Integer.parseInt(req_args[1]) > CON) return false;
                    break;
                case "DEX":
                    if(Integer.parseInt(req_args[1]) > DEX) return false;
                    break;
                case "INT":
                    if(Integer.parseInt(req_args[1]) > INT) return false;
                    break;
                case "WIS":
                    if(Integer.parseInt(req_args[1]) > WIS) return false;
                    break;
                case "CHA":
                    if(Integer.parseInt(req_args[1]) > CHA) return false;
                    break;
                case "Race":
                    if(race == null || !race.getName().equals(req_args[1])) return false;
                    break;
                case "Class":
                    if(myClass == null || !myClass.getName().equals(req_args[1])) return false;
                    break;
                case "Passive":
                    boolean result = false;

                    for(PassiveAbility ab : abilities) {
                        if(ab.getName().equals(req_args[1])) {
                            result = true;
                            break;
                        }
                    }

                    if(result == false) return false;

                    break;
            }
        }

        return true;
    }

    public void addFeat(Feat in) {
        if(!feats.contains(in)) {
            feats.add(in);

            in.addAbilities(this);
        }
    }

    public void removeFeat(Feat in) {
        if(feats.remove(in)) {
            in.undoChanges(this);
        }
    }

    public ArrayList<Feat> getFeats() {
        return feats;
    }

    public int getSTR() {
        return STR;
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    public int getCON() {
        return CON;
    }

    public void setCON(int CON) {
        this.CON = CON;
    }

    public int getDEX() {
        return DEX;
    }

    public void setDEX(int DEX) {
        this.DEX = DEX;
    }

    public int getINT() {
        return INT;
    }

    public void setINT(int INT) {
        this.INT = INT;
    }

    public int getWIS() {
        return WIS;
    }

    public void setWIS(int WIS) {
        this.WIS = WIS;
    }

    public int getCHA() {
        return CHA;
    }

    public void setCHA(int CHA) {
        this.CHA = CHA;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getSurges() {
        return surges;
    }

    public void setSurges(int surges) {
        this.surges = surges;
    }

    public int getAC() {
        return AC;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }

    public int getFORT() {
        return FORT;
    }

    public void setFORT(int FORT) {
        this.FORT = FORT;
    }

    public int getREF() {
        return REF;
    }

    public void setREF(int REF) {
        this.REF = REF;
    }

    public int getWILL() {
        return WILL;
    }

    public void setWILL(int WILL) {
        this.WILL = WILL;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAcrobatics() {
        return acrobatics;
    }

    public void setAcrobatics(int acrobatics) {
        this.acrobatics = acrobatics;
    }

    public int getArcana() {
        return arcana;
    }

    public void setArcana(int arcana) {
        this.arcana = arcana;
    }

    public int getAthletics() {
        return athletics;
    }

    public void setAthletics(int athletics) {
        this.athletics = athletics;
    }

    public int getBluff() {
        return bluff;
    }

    public void setBluff(int bluff) {
        this.bluff = bluff;
    }

    public int getDiplomacy() {
        return diplomacy;
    }

    public void setDiplomacy(int diplomacy) {
        this.diplomacy = diplomacy;
    }

    public int getDungeoneering() {
        return dungeoneering;
    }

    public void setDungeoneering(int dungeoneering) {
        this.dungeoneering = dungeoneering;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) { this.history = history; }

    public int getInsight() {
        return insight;
    }

    public void setInsight(int insight) {
        this.insight = insight;
    }

    public int getIntimidate() {
        return intimidate;
    }

    public void setIntimidate(int intimidate) {
        this.intimidate = intimidate;
    }

    public int getNature() {
        return nature;
    }

    public void setNature(int nature) {
        this.nature = nature;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }

    public int getStealth() {
        return stealth;
    }

    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    public int getStreetwise() {
        return streetwise;
    }

    public void setStreetwise(int streetwise) {
        this.streetwise = streetwise;
    }

    public int getThievery() {
        return thievery;
    }

    public void setThievery(int thievery) {
        this.thievery = thievery;
    }

    public boolean isAcrobaticsTrnd() {
        return acrobaticsTrnd;
    }

    public void setAcrobaticsTrnd(boolean acrobaticsTrnd) {
        if(acrobaticsTrnd != this.acrobaticsTrnd) {
            if(acrobaticsTrnd) {
                acrobatics += 5;
            }
            else {
                acrobatics -= 5;
            }
        }

        this.acrobaticsTrnd = acrobaticsTrnd;
    }

    public boolean isArcanaTrnd() {
        return arcanaTrnd;
    }

    public void setArcanaTrnd(boolean arcanaTrnd) {
        if(arcanaTrnd != this.arcanaTrnd) {
            if(arcanaTrnd) {
                arcana += 5;
            }
            else {
                arcana -= 5;
            }
        }

        this.arcanaTrnd = arcanaTrnd;
    }

    public boolean isAthleticsTrnd() {
        return athleticsTrnd;
    }

    public void setAthleticsTrnd(boolean athleticsTrnd) {
        if(athleticsTrnd != this.athleticsTrnd) {
            if(athleticsTrnd) {
                athletics += 5;
            }
            else {
                athletics -= 5;
            }
        }

        this.athleticsTrnd = athleticsTrnd;
    }

    public boolean isBluffTrnd() {
        return bluffTrnd;
    }

    public void setBluffTrnd(boolean bluffTrnd) {
        if(bluffTrnd != this.bluffTrnd) {
            if(bluffTrnd) {
                bluff += 5;
            }
            else {
                bluff -= 5;
            }
        }

        this.bluffTrnd = bluffTrnd;
    }

    public boolean isDiplomacyTrnd() {
        return diplomacyTrnd;
    }

    public void setDiplomacyTrnd(boolean diplomacyTrnd) {
        if(diplomacyTrnd != this.diplomacyTrnd) {
            if(diplomacyTrnd) {
                diplomacy += 5;
            }
            else {
                diplomacy -= 5;
            }
        }

        this.diplomacyTrnd = diplomacyTrnd;
    }

    public boolean isDungeoneeringTrnd() {
        return dungeoneeringTrnd;
    }

    public void setDungeoneeringTrnd(boolean dungeoneeringTrnd) {
        if(dungeoneeringTrnd != this.dungeoneeringTrnd) {
            if(dungeoneeringTrnd) {
                dungeoneering += 5;
            }
            else {
                dungeoneering -= 5;
            }
        }

        this.dungeoneeringTrnd = dungeoneeringTrnd;
    }

    public boolean isEnduranceTrnd() {
        return enduranceTrnd;
    }

    public void setEnduranceTrnd(boolean enduranceTrnd) {
        if(enduranceTrnd != this.enduranceTrnd) {
            if(enduranceTrnd) {
                endurance += 5;
            }
            else {
                endurance -= 5;
            }
        }

        this.enduranceTrnd = enduranceTrnd;
    }

    public boolean isHealTrnd() {
        return healTrnd;
    }

    public void setHealTrnd(boolean healTrnd) {
        if(healTrnd != this.healTrnd) {
            if(healTrnd) {
                heal += 5;
            }
            else {
                heal -= 5;
            }
        }

        this.healTrnd = healTrnd;
    }

    public boolean isHistoryTrnd() {
        return historyTrnd;
    }

    public void setHistoryTrnd(boolean historyTrnd) {
        if(historyTrnd != this.historyTrnd) {
            if(historyTrnd) {
                history += 5;
            }
            else {
                history -= 5;
            }
        }

        this.historyTrnd = historyTrnd;
    }

    public boolean isInsightTrnd() {
        return insightTrnd;
    }

    public void setInsightTrnd(boolean insightTrnd) {
        if(insightTrnd != this.insightTrnd) {
            if(insightTrnd) {
                insight += 5;
            }
            else {
                insight -= 5;
            }
        }

        this.insightTrnd = insightTrnd;
    }

    public boolean isIntimidateTrnd() {
        return intimidateTrnd;
    }

    public void setIntimidateTrnd(boolean intimidateTrnd) {
        if(intimidateTrnd != this.intimidateTrnd) {
            if(intimidateTrnd) {
                intimidate += 5;
            }
            else {
                intimidate -= 5;
            }
        }

        this.intimidateTrnd = intimidateTrnd;
    }

    public boolean isNatureTrnd() {
        return natureTrnd;
    }

    public void setNatureTrnd(boolean natureTrnd) {
        if(natureTrnd != this.natureTrnd) {
            if(natureTrnd) {
                nature += 5;
            }
            else {
                nature -= 5;
            }
        }

        this.natureTrnd = natureTrnd;
    }

    public boolean isPerceptionTrnd() {
        return perceptionTrnd;
    }

    public void setPerceptionTrnd(boolean perceptionTrnd) {
        if(perceptionTrnd != this.perceptionTrnd) {
            if(perceptionTrnd) {
                perception += 5;
            }
            else {
                perception -= 5;
            }
        }

        this.perceptionTrnd = perceptionTrnd;
    }

    public boolean isReligionTrnd() {
        return religionTrnd;
    }

    public void setReligionTrnd(boolean religionTrnd) {
        if(religionTrnd != this.religionTrnd) {
            if(religionTrnd) {
                religion += 5;
            }
            else {
                religion -= 5;
            }
        }

        this.religionTrnd = religionTrnd;
    }

    public boolean isStealthTrnd() {
        return stealthTrnd;
    }

    public void setStealthTrnd(boolean stealthTrnd) {
        if(stealthTrnd != this.stealthTrnd) {
            if(stealthTrnd) {
                stealth += 5;
            }
            else {
                stealth -= 5;
            }
        }

        this.stealthTrnd = stealthTrnd;
    }

    public boolean isStreetwiseTrnd() {
        return streetwiseTrnd;
    }

    public void setStreetwiseTrnd(boolean streetwiseTrnd) {
        if(streetwiseTrnd != this.streetwiseTrnd) {
            if(streetwiseTrnd) {
                streetwise += 5;
            }
            else {
                streetwise -= 5;
            }
        }

        this.streetwiseTrnd = streetwiseTrnd;
    }

    public boolean isThieveryTrnd() {
        return thieveryTrnd;
    }

    public void setThieveryTrnd(boolean thieveryTrnd) {
        if(thieveryTrnd != this.thieveryTrnd) {
            if(thieveryTrnd) {
                thievery += 5;
            }
            else {
                thievery -= 5;
            }
        }

        this.thieveryTrnd = thieveryTrnd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.race, flags);
        dest.writeParcelable(this.myClass, flags);
        dest.writeList(this.abilities);
        dest.writeList(this.powers);
        dest.writeList(this.feats);
        dest.writeInt(this.STR);
        dest.writeInt(this.CON);
        dest.writeInt(this.DEX);
        dest.writeInt(this.INT);
        dest.writeInt(this.WIS);
        dest.writeInt(this.CHA);
        dest.writeInt(this.HP);
        dest.writeInt(this.surges);
        dest.writeInt(this.AC);
        dest.writeInt(this.FORT);
        dest.writeInt(this.REF);
        dest.writeInt(this.WILL);
        dest.writeInt(this.speed);
        dest.writeInt(this.acrobatics);
        dest.writeInt(this.arcana);
        dest.writeInt(this.athletics);
        dest.writeInt(this.bluff);
        dest.writeInt(this.diplomacy);
        dest.writeInt(this.dungeoneering);
        dest.writeInt(this.endurance);
        dest.writeInt(this.heal);
        dest.writeInt(this.history);
        dest.writeInt(this.insight);
        dest.writeInt(this.intimidate);
        dest.writeInt(this.nature);
        dest.writeInt(this.perception);
        dest.writeInt(this.religion);
        dest.writeInt(this.stealth);
        dest.writeInt(this.streetwise);
        dest.writeInt(this.thievery);
        dest.writeByte(this.acrobaticsTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.arcanaTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.athleticsTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.bluffTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.diplomacyTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.dungeoneeringTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enduranceTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.healTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.historyTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.insightTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.intimidateTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.natureTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.perceptionTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.religionTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.stealthTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.streetwiseTrnd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.thieveryTrnd ? (byte) 1 : (byte) 0);
    }

    protected PlayerCharacter(Parcel in) {
        this.race = in.readParcelable(Race.class.getClassLoader());
        this.myClass = in.readParcelable(PlayerClass.class.getClassLoader());
        this.abilities = new ArrayList<PassiveAbility>();
        in.readList(this.abilities, PassiveAbility.class.getClassLoader());
        this.powers = new ArrayList<PowerAbility>();
        in.readList(this.powers, PowerAbility.class.getClassLoader());
        this.feats = new ArrayList<Feat>();
        in.readList(this.feats, Feat.class.getClassLoader());
        this.STR = in.readInt();
        this.CON = in.readInt();
        this.DEX = in.readInt();
        this.INT = in.readInt();
        this.WIS = in.readInt();
        this.CHA = in.readInt();
        this.HP = in.readInt();
        this.surges = in.readInt();
        this.AC = in.readInt();
        this.FORT = in.readInt();
        this.REF = in.readInt();
        this.WILL = in.readInt();
        this.speed = in.readInt();
        this.acrobatics = in.readInt();
        this.arcana = in.readInt();
        this.athletics = in.readInt();
        this.bluff = in.readInt();
        this.diplomacy = in.readInt();
        this.dungeoneering = in.readInt();
        this.endurance = in.readInt();
        this.heal = in.readInt();
        this.history = in.readInt();
        this.insight = in.readInt();
        this.intimidate = in.readInt();
        this.nature = in.readInt();
        this.perception = in.readInt();
        this.religion = in.readInt();
        this.stealth = in.readInt();
        this.streetwise = in.readInt();
        this.thievery = in.readInt();
        this.acrobaticsTrnd = in.readByte() != 0;
        this.arcanaTrnd = in.readByte() != 0;
        this.athleticsTrnd = in.readByte() != 0;
        this.bluffTrnd = in.readByte() != 0;
        this.diplomacyTrnd = in.readByte() != 0;
        this.dungeoneeringTrnd = in.readByte() != 0;
        this.enduranceTrnd = in.readByte() != 0;
        this.healTrnd = in.readByte() != 0;
        this.historyTrnd = in.readByte() != 0;
        this.insightTrnd = in.readByte() != 0;
        this.intimidateTrnd = in.readByte() != 0;
        this.natureTrnd = in.readByte() != 0;
        this.perceptionTrnd = in.readByte() != 0;
        this.religionTrnd = in.readByte() != 0;
        this.stealthTrnd = in.readByte() != 0;
        this.streetwiseTrnd = in.readByte() != 0;
        this.thieveryTrnd = in.readByte() != 0;
    }

    public static final Parcelable.Creator<PlayerCharacter> CREATOR = new Parcelable.Creator<PlayerCharacter>() {
        @Override
        public PlayerCharacter createFromParcel(Parcel source) {
            return new PlayerCharacter(source);
        }

        @Override
        public PlayerCharacter[] newArray(int size) {
            return new PlayerCharacter[size];
        }
    };
}
