package DnDClass;

import android.os.Parcel;

import Actions.PassiveAbility;
import Actions.PowerAbility;
import Player.PlayerCharacter;

/**
 * Created by kabino11 on 12/9/16.
 */

public class Cleric extends PlayerClass {
    private static final String[] SKILLS = {"Arcana", "Diplomacy", "Heal", "History", "Insight"};
    private static final int NUM_SELECT = 3;

    private static final PassiveAbility channel_divinity = new PassiveAbility("Channel Divinity", "You can invoke the power of your patron diety once per encounter");

    private static final PowerAbility devine_fortune = new PowerAbility("Channel Divinity: Divine Fortune", "Cleric", 1, "Divine", "Encounter", "Free Action", "Personal", "Self", "", "+1 bonus to your next attack roll or saving throw before the end of your next turn.", "", "", "", "");
    private static final PowerAbility turn_undead = new PowerAbility("Channel Divinity: Turn Undead", "Cleric", 1, "Divine, Implement, Radiant", "Encounter", "Standard Action", "Close Burst 2", "Each undead creature in burst", "", "", "Wisdom vs. Will", "1d10 + WIS mod radiant damage and push the target 3 + CHA mod and target immobilized until end of your next turn.", "Half damage, and the target is not pushed or immobilized.", "Increase Range to Close burst 5 at 11th level, Close burst 8 at 21st level");
    private static final PowerAbility healing_word = new PowerAbility("Healing word", "Cleric", 1, "Divine, Healing", "Encounter (Special)", "Minor Action", "Close Burst 5", "You or one ally in burst", "Can be used twice per encounter, but once per round", "Target can spend a healing surge and regain an additional 1d6 hit points", "", "", "", "Increase the amount of additional hit points regained to 2d6 at 6th level, 3d6 at 11th level, 4d6 at 16th level, 5d6 at 21st level, and 6d6 at 26th level.");

    public Cleric() {
        super("Cleric", "Clerics are battle leaters who are invested with divine power.");
    }

    @Override
    public String[] getSkillOptions() { return SKILLS; }

    @Override
    public int getNumToSelect() { return NUM_SELECT; }

    @Override
    protected void addStats(PlayerCharacter in) {
        in.setWILL(in.getWILL() + 2);

        in.setHP(12 + in.getCON());
        in.setSurges(7 + in.getCONMod());

        in.setReligionTrnd(true);
    }

    @Override
    protected void addSpecial(PlayerCharacter in) {
        in.addPassive(channel_divinity);

        in.addPower(devine_fortune);
        in.addPower(turn_undead);
        in.addPower(healing_word);
    }

    @Override
    public void undoChanges(PlayerCharacter in) {
        in.setWILL(in.getWILL() - 2);

        in.setHP(0);
        in.setSurges(0);

        in.setReligionTrnd(false);

        in.removePassive(channel_divinity);

        in.removePower(devine_fortune);
        in.removePower(turn_undead);
        in.removePower(healing_word);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected Cleric(Parcel in) {
        super(in);
    }

    public static final Creator<Cleric> CREATOR = new Creator<Cleric>() {
        @Override
        public Cleric createFromParcel(Parcel source) {
            return new Cleric(source);
        }

        @Override
        public Cleric[] newArray(int size) {
            return new Cleric[size];
        }
    };
}
