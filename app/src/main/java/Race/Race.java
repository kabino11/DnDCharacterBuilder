package Race;

import android.os.Parcel;
import android.os.Parcelable;

import Player.PlayerCharacter;

/**
 * Created by kabino11 on 12/8/16.
 */

public abstract class Race implements Parcelable {
    private String name;
    private String description;

    public Race() {
        name = "";
        description = "";
    }

    public Race(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public final String getName() { return name; }
    public final String getDescription() { return description; };

    public final void commitChanges(PlayerCharacter in) {
        addStats(in);

        in.calcDefStats();
        in.calcSkills();

        addSkillScores(in);
        addSpecial(in);
    }

    protected abstract void addStats(PlayerCharacter in);
    protected abstract void addSkillScores(PlayerCharacter in);
    protected abstract void addSpecial(PlayerCharacter in);

    public void undoChanges(PlayerCharacter in) {
        in.calcDefStats();
        in.calcSkills();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
    }

    protected Race(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
    }
}
