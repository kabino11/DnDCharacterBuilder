package DnDClass;

/**
 * Created by kabino11 on 12/13/16.
 */

public class DnDClassFactory {
    private static final String[] OPTIONS = {"Cleric", "Fighter"};

    public PlayerClass getPlayerClass(String type) {
        if(type == null || type.equals("")) return null;

        PlayerClass toReturn = null;

        switch(type) {
            case "Cleric":
                toReturn = new Cleric();
                break;
            case "Fighter":
                toReturn = new Fighter();
                break;
        }

        return toReturn;
    }

    public String[] getOptions() { return OPTIONS; }
}
