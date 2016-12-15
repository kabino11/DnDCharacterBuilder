package DnDClass;

import android.os.Parcel;
import android.os.Parcelable;

import Player.PlayerCharacter;

/**
 * Created by kabino11 on 12/9/16.
 */

public abstract class PlayerClass implements Parcelable {
    private String name;
    private String description;

    public PlayerClass() {
        name = "";
        description = "";
    }

    public PlayerClass(String name, String des) {
        this.name = name;
        this.description = des;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void commitChanges(PlayerCharacter in) {
        addStats(in);
        addSpecial(in);
    }

    public abstract String[] getSkillOptions();
    public abstract int getNumToSelect();

    protected abstract void addStats(PlayerCharacter in);
    protected abstract void addSpecial(PlayerCharacter in);

    public abstract void undoChanges(PlayerCharacter in);

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
    }

    protected PlayerClass(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
    }
}
