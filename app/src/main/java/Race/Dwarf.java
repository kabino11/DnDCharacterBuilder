package Race;

import android.os.Parcel;

import Actions.PassiveAbility;
import Player.PlayerCharacter;
import Race.Race;

/**
 * Created by kabino11 on 12/8/16.
 */

public class Dwarf extends Race {
    private static final PassiveAbility stomach = new PassiveAbility("Cast-Iron Stomach", "+5 racial bonus to saving throws against poison.");
    private static final PassiveAbility resilience = new PassiveAbility("Dwarven Resilience", "You can use your second wind as a minor action instead of a standard action.");
    private static final PassiveAbility proficency = new PassiveAbility("Dwarven Weapon Proficiency", "You gain proficiency with the throwing hammer and the warhammer.");
    private static final PassiveAbility enc_speed = new PassiveAbility("Encumbered Speed", "You move at your normal speed even when it would normally be reduced by armor or a heavy load. Other effects that limit speed (such as difficult terrain or magical effects) affect you normally.");
    private static final PassiveAbility stand = new PassiveAbility("Stand Your Ground", "When an effect forces you to move—through a pull, a push, or a slide—you can move 1 square less than the effect specifies. This means an effect that normally pulls, pushes, or slides a target 1 square does not force you to move unless you want to. In addition, when an attack would knock you prone, you can immediately make a saving throw to avoid falling prone.");

    public Dwarf() {
        super("Dwarf", "Dwarves average about 41⁄2 feet in height and are very broad, weighing as much as an adult human.");
    }

    @Override
    protected void addStats(PlayerCharacter in) {
        in.setCON(in.getCON() + 2);
        in.setWIS(in.getWIS() + 2);

        in.setSpeed(5);
    }

    @Override
    protected void addSkillScores(PlayerCharacter in) {
        in.setDungeoneering(in.getDungeoneering() + 2);
        in.setEndurance(in.getEndurance() + 2);
    }

    @Override
    protected void addSpecial(PlayerCharacter in) {
        in.addPassive(stomach);
        in.addPassive(resilience);
        in.addPassive(proficency);
        in.addPassive(enc_speed);
        in.addPassive(stand);
    }

    @Override
    public void undoChanges(PlayerCharacter in) {
        in.setCON(in.getCON() - 2);
        in.setWIS(in.getWIS() - 2);

        in.removePassive(stomach);
        in.removePassive(resilience);
        in.removePassive(proficency);
        in.removePassive(enc_speed);
        in.removePassive(stand);

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

    protected Dwarf(Parcel in) {
        super(in);
    }

    public static final Creator<Dwarf> CREATOR = new Creator<Dwarf>() {
        @Override
        public Dwarf createFromParcel(Parcel source) {
            return new Dwarf(source);
        }

        @Override
        public Dwarf[] newArray(int size) {
            return new Dwarf[size];
        }
    };
}
