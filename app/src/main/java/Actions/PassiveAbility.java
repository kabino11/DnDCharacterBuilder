package Actions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kabino11 on 12/9/16.
 */

public class PassiveAbility implements Parcelable {
    private String name;
    private String description;

    public PassiveAbility() {
        name = "";
        description = "";
    }

    public PassiveAbility(String name, String des) {
        this.name = name;
        this.description = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassiveAbility that = (PassiveAbility) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
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

    protected PassiveAbility(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<PassiveAbility> CREATOR = new Parcelable.Creator<PassiveAbility>() {
        @Override
        public PassiveAbility createFromParcel(Parcel source) {
            return new PassiveAbility(source);
        }

        @Override
        public PassiveAbility[] newArray(int size) {
            return new PassiveAbility[size];
        }
    };
}
