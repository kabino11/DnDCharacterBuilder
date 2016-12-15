package DnDClass;

import android.os.Parcel;

import Actions.PassiveAbility;
import Player.PlayerCharacter;

/**
 * Created by kabino11 on 12/9/16.
 */

public class Fighter extends PlayerClass {
    private static final String[] SKILL_OPTIONS = {"Athletics", "Endurance", "Heal", "Intimidate", "Streetwise"};
    private static final int NUM_OPTIONS = 3;

    private PassiveAbility cmbt_challenge = new PassiveAbility("Combat Challenge", "Each time you attempt to attack an enemy you can mark it. A marked enemy takes -2 to attack rolls made that don't include you as a target.  New marks override old marks");
    private PassiveAbility cmbt_superiority = new PassiveAbility("Combat Superiority", "You gain a bonus to oppritunity attacks equal to your WIS mod.  Your attacks also stop enemy movement (although the enemy can use another action to start moving again)");
    private PassiveAbility weapon_talent = new PassiveAbility("Fighter Weapon Talent", "Choose one-handed or two-handed weapons (one-time choice at character creation).  You gain a +1 bonus to attack rolls when you use that kind of weapon");

    public Fighter() {
        super("Fighter", "Fighers are determined combat adepts trained to protect the other members of their adventuring groups");
    }

    @Override
    public String[] getSkillOptions() { return SKILL_OPTIONS; }

    @Override
    public int getNumToSelect() { return NUM_OPTIONS; }

    @Override
    protected void addStats(PlayerCharacter in) {
        in.setFORT(in.getFORT() + 2);

        in.setHP(15 + in.getCON());
        in.setSurges(9 + in.getCONMod());
    }

    @Override
    protected void addSpecial(PlayerCharacter in) {
        in.addPassive(cmbt_challenge);
        in.addPassive(cmbt_superiority);
        in.addPassive(weapon_talent);
    }

    @Override
    public void undoChanges(PlayerCharacter in) {
        in.setFORT(in.getFORT() - 2);

        in.setHP(0);
        in.setSurges(0);

        in.removePassive(cmbt_challenge);
        in.removePassive(cmbt_superiority);
        in.removePassive(weapon_talent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.cmbt_challenge, flags);
        dest.writeParcelable(this.cmbt_superiority, flags);
        dest.writeParcelable(this.weapon_talent, flags);
    }

    protected Fighter(Parcel in) {
        super(in);
        this.cmbt_challenge = in.readParcelable(PassiveAbility.class.getClassLoader());
        this.cmbt_superiority = in.readParcelable(PassiveAbility.class.getClassLoader());
        this.weapon_talent = in.readParcelable(PassiveAbility.class.getClassLoader());
    }

    public static final Creator<Fighter> CREATOR = new Creator<Fighter>() {
        @Override
        public Fighter createFromParcel(Parcel source) {
            return new Fighter(source);
        }

        @Override
        public Fighter[] newArray(int size) {
            return new Fighter[size];
        }
    };
}
