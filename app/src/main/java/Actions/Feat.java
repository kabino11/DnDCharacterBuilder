package Actions;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import Player.PlayerCharacter;

/**
 * Created by kabino11 on 12/12/16.
 */

public class Feat implements Parcelable {
    private String name;
    private String benefit;
    private String special;
    private String prereq;

    private ArrayList<PowerAbility> abil_granted;

    public Feat() {
        this.name = "";
        this.benefit = "";
        this.special = "";
        this.prereq = "";

        abil_granted = new ArrayList<>();
    }

    public Feat(String name, String benefit, String special, String prereq) {
        this.name = name;
        this.benefit = benefit;
        this.special = special;
        this.prereq = prereq;

        abil_granted = new ArrayList<>();
    }

    public void addAbility(PowerAbility ab) {
        if(!abil_granted.contains(ab)) {
            abil_granted.add(ab);
        }
    }

    public void removeAbility(PowerAbility ab) {
        abil_granted.remove(ab);
    }

    public void addAbilities(PlayerCharacter in) {
        for(PowerAbility ab : abil_granted) {
            in.addPower(ab);
        }
    }

    public void undoChanges(PlayerCharacter in) {
        for(PowerAbility ab : abil_granted) {
            in.removePower(ab);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getPrereq() {
        return prereq;
    }

    public void setPrereq(String prereq) {
        this.prereq = prereq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feat feat = (Feat) o;

        if (name != null ? !name.equals(feat.name) : feat.name != null) return false;
        if (benefit != null ? !benefit.equals(feat.benefit) : feat.benefit != null) return false;
        if (special != null ? !special.equals(feat.special) : feat.special != null) return false;
        return prereq != null ? prereq.equals(feat.prereq) : feat.prereq == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (benefit != null ? benefit.hashCode() : 0);
        result = 31 * result + (special != null ? special.hashCode() : 0);
        result = 31 * result + (prereq != null ? prereq.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.benefit);
        dest.writeString(this.special);
        dest.writeString(this.prereq);
        dest.writeTypedList(this.abil_granted);
    }

    protected Feat(Parcel in) {
        this.name = in.readString();
        this.benefit = in.readString();
        this.special = in.readString();
        this.prereq = in.readString();
        this.abil_granted = in.createTypedArrayList(PowerAbility.CREATOR);
    }

    public static final Parcelable.Creator<Feat> CREATOR = new Parcelable.Creator<Feat>() {
        @Override
        public Feat createFromParcel(Parcel source) {
            return new Feat(source);
        }

        @Override
        public Feat[] newArray(int size) {
            return new Feat[size];
        }
    };
}
