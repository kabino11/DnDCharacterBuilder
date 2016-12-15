package Actions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kabino11 on 12/12/16.
 */

public class PowerAbility implements Parcelable {
    private String name;
    private String pclass;
    private int level;

    private String dmgType;
    private String pwrType;
    private String acnType;
    private String range;
    private String target;

    private String special;

    private String effect;
    private String rollVS;
    private String hit;
    private String miss;

    private String upgrade;

    public PowerAbility() {
        name = "";
        pclass = "";
        level = 0;

        dmgType = "";
        pwrType = "";
        acnType = "";
        range = "";
        target = "";

        special = "";

        effect = "";
        rollVS = "";
        hit = "";
        miss = "";

        upgrade = "";
    }

    public PowerAbility(String name, String pclass, int level, String dmgType, String pwrType, String acnType, String range, String target, String special, String effect, String rollVS, String hit, String miss, String upgrade) {
        this.name = name;
        this.pclass = pclass;
        this.level = level;
        this.dmgType = dmgType;
        this.pwrType = pwrType;
        this.acnType = acnType;
        this.range = range;
        this.special = special;
        this.target = target;
        this.effect = effect;
        this.rollVS = rollVS;
        this.hit = hit;
        this.miss = miss;
        this.upgrade = upgrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PowerAbility that = (PowerAbility) o;

        if (level != that.level) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pclass != null ? !pclass.equals(that.pclass) : that.pclass != null) return false;
        if (dmgType != null ? !dmgType.equals(that.dmgType) : that.dmgType != null) return false;
        if (pwrType != null ? !pwrType.equals(that.pwrType) : that.pwrType != null) return false;
        if (acnType != null ? !acnType.equals(that.acnType) : that.acnType != null) return false;
        if (range != null ? !range.equals(that.range) : that.range != null) return false;
        if (target != null ? !target.equals(that.target) : that.target != null) return false;
        if (special != null ? !special.equals(that.special) : that.special != null) return false;
        if (effect != null ? !effect.equals(that.effect) : that.effect != null) return false;
        if (rollVS != null ? !rollVS.equals(that.rollVS) : that.rollVS != null) return false;
        if (hit != null ? !hit.equals(that.hit) : that.hit != null) return false;
        if (miss != null ? !miss.equals(that.miss) : that.miss != null) return false;
        return upgrade != null ? upgrade.equals(that.upgrade) : that.upgrade == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pclass != null ? pclass.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + (dmgType != null ? dmgType.hashCode() : 0);
        result = 31 * result + (pwrType != null ? pwrType.hashCode() : 0);
        result = 31 * result + (acnType != null ? acnType.hashCode() : 0);
        result = 31 * result + (range != null ? range.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (special != null ? special.hashCode() : 0);
        result = 31 * result + (effect != null ? effect.hashCode() : 0);
        result = 31 * result + (rollVS != null ? rollVS.hashCode() : 0);
        result = 31 * result + (hit != null ? hit.hashCode() : 0);
        result = 31 * result + (miss != null ? miss.hashCode() : 0);
        result = 31 * result + (upgrade != null ? upgrade.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPclass() {
        return pclass;
    }

    public void setPclass(String pclass) {
        this.pclass = pclass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDmgType() {
        return dmgType;
    }

    public void setDmgType(String dmgType) {
        this.dmgType = dmgType;
    }

    public String getPwrType() {
        return pwrType;
    }

    public void setPwrType(String pwrType) {
        this.pwrType = pwrType;
    }

    public String getAcnType() {
        return acnType;
    }

    public void setAcnType(String acnType) {
        this.acnType = acnType;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getRollVS() {
        return rollVS;
    }

    public void setRollVS(String rollVS) {
        this.rollVS = rollVS;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getMiss() {
        return miss;
    }

    public void setMiss(String miss) {
        this.miss = miss;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.pclass);
        dest.writeInt(this.level);
        dest.writeString(this.dmgType);
        dest.writeString(this.pwrType);
        dest.writeString(this.acnType);
        dest.writeString(this.range);
        dest.writeString(this.target);
        dest.writeString(this.special);
        dest.writeString(this.effect);
        dest.writeString(this.rollVS);
        dest.writeString(this.hit);
        dest.writeString(this.miss);
        dest.writeString(this.upgrade);
    }

    protected PowerAbility(Parcel in) {
        this.name = in.readString();
        this.pclass = in.readString();
        this.level = in.readInt();
        this.dmgType = in.readString();
        this.pwrType = in.readString();
        this.acnType = in.readString();
        this.range = in.readString();
        this.target = in.readString();
        this.special = in.readString();
        this.effect = in.readString();
        this.rollVS = in.readString();
        this.hit = in.readString();
        this.miss = in.readString();
        this.upgrade = in.readString();
    }

    public static final Parcelable.Creator<PowerAbility> CREATOR = new Parcelable.Creator<PowerAbility>() {
        @Override
        public PowerAbility createFromParcel(Parcel source) {
            return new PowerAbility(source);
        }

        @Override
        public PowerAbility[] newArray(int size) {
            return new PowerAbility[size];
        }
    };
}
