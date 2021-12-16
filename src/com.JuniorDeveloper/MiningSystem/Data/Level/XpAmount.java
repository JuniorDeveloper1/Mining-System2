package Data.Level;

import Messages.MessageManager;
import org.bukkit.entity.Player;

import java.util.logging.Level;

import static Data.Level.LevelingManager.*;

public class XpAmount {
    static Player player;
    /**
     * @Link{ https://www.spigotmc.org/threads/level-system.356440/ }
     *
     * TODO: Fix better LEVELING system
     */

   static  int totalXPRequirdForLevelUp;

    public static int  getTotalXPRequirdForLevelUp() {
        if(level <= 50)
            totalXPRequirdForLevelUp = (int) (getMultiplier() + Math.pow(level, 1.5) + 10*level);

        if(level >= 50 || level <= 100 )
            totalXPRequirdForLevelUp = (int) (getMultiplier() + Math.pow(level, 1.5) + 10*level);
        if(level >= 100) {
            totalXPRequirdForLevelUp = (int) ( getMultiplier() + Math.pow(level, 1.5) + 10*level);
        }
        if(level >= maxmimum) {
            totalXPRequirdForLevelUp = Integer.MAX_VALUE;
            player.sendMessage(MessageManager.WHY.getMessage());

        }
        return totalXPRequirdForLevelUp;
    }
}
