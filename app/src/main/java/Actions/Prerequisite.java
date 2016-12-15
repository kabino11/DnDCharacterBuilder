package Actions;

import Player.PlayerCharacter;

/**
 * Created by kabino11 on 12/8/16.
 */

public interface Prerequisite {
    public boolean meets(PlayerCharacter in);
}
