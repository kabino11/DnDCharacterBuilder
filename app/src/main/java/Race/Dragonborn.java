package Race;

import android.os.Parcel;

import Actions.PassiveAbility;
import Actions.PowerAbility;
import Player.PlayerCharacter;
import Race.Race;

/**
 * Created by kabino11 on 12/8/16.
 */

public class Dragonborn extends Race {
    private static final PassiveAbility fury = new PassiveAbility("Dragonborn Fury", "When you’re bloodied, you gain a +1 racial bonus to attack rolls.");
    private static final PassiveAbility heritage = new PassiveAbility("Dragonborn Heritage", "Your healing surge value is equal to one-quarter of your maximum hit points + your Constitution modifier.");
    private static final PassiveAbility breath = new PassiveAbility("Dragon Breath", "You can use dragon breath as an encounter power.");

    private static final PowerAbility breath_ab = new PowerAbility("Dragon Breath", "Dragonborn Racial Power", 1, "Acid, Cold, Fire, Lightning, or Poison", "Encounter", "Minor Action", "Close Blast 3", "ALL creatures in range", "You choose your stat to roll with and your elemental damage during character creation (Cannot be changed)", "", "Strength + 2 vs. Reflex, Constitution + 2 vs. Reflex, or Dexterity + 2 vs. Reflex", "1d6 + Constitution modifier damage.", "", "Increase damage to +4 bonus and 2d6 + Constitution mod damage at 11th level, and to +6 bonus and 3d6 + Constitution mod damage at 21st level.");

    public Dragonborn() {
        super("Dragonborn", "Dragonborn resemble humanoid dragons.");
    }

    @Override
    protected void addStats(PlayerCharacter in) {
        in.setSTR(in.getSTR() + 2);
        in.setCHA(in.getCHA() + 2);

        in.setSpeed(6);
    }

    @Override
    protected void addSkillScores(PlayerCharacter in) {
        in.setHistory(in.getHistory() + 2);
        in.setIntimidate(in.getIntimidate() + 2);
    }

    @Override
    protected void addSpecial(PlayerCharacter in) {
        in.addPassive(fury);
        in.addPassive(heritage);
        in.addPassive(breath);

        in.addPower(breath_ab);
    }

    @Override
    public void undoChanges(PlayerCharacter in) {
        in.setSTR(in.getSTR() - 2);
        in.setCHA(in.getCHA() - 2);

        in.removePassive(fury);
        in.removePassive(heritage);
        in.removePassive(breath);

        in.removePower(breath_ab);

        super.undoChanges(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected Dragonborn(Parcel in) {
        super(in);
    }

    public static final Creator<Dragonborn> CREATOR = new Creator<Dragonborn>() {
        @Override
        public Dragonborn createFromParcel(Parcel source) {
            return new Dragonborn(source);
        }

        @Override
        public Dragonborn[] newArray(int size) {
            return new Dragonborn[size];
        }
    };
}
