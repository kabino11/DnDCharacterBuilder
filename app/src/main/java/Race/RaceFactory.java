package Race;

/**
 * Created by kabino11 on 12/13/16.
 */

public class RaceFactory {
    private static final String[] OPTIONS = {"Dragonborn", "Dwarf"};

    public Race getRace(String type) {
        if(type == null || type.equals("")) return null;

        Race race = null;

        switch(type) {
            case "Dragonborn":
                race = new Dragonborn();
                break;
            case "Dwarf":
                race = new Dwarf();
                break;
        }

        return race;
    }

    public String[] getOptions() { return OPTIONS; }
}
