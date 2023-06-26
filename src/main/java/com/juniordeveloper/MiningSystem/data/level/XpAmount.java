package com.juniordeveloper.MiningSystem.data.level;


import com.juniordeveloper.MiningSystem.config.ConfigLevels;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static com.juniordeveloper.MiningSystem.data.level.LevelingManager.getLevel;
import static com.juniordeveloper.MiningSystem.data.level.LevelingManager.getMultiplier;


public class XpAmount {
    static Player player;
    /**
     * @Link{ https://www.spigotmc.org/threads/level-system.356440/ }
     *
     * TODO: Fix better LEVELING system
     */

   private  static int totalXPRequirdForLevelUp;

    public static int  getTotalXPRequirdForLevelUp() {

        FileConfiguration configLevels = ConfigLevels.getLevelConfig();

        int level = getLevel();



        if(level >= LevelingManager.getMaxmimum()) {
            totalXPRequirdForLevelUp = (int) (getMultiplier() + Math.pow(level, 1.5) + 10 * level);
        }


        for (int i = 0; i < LevelingManager.getMaxmimum(); i++) {
            totalXPRequirdForLevelUp = (int) (getMultiplier() + Math.pow(i, 1.5) + 10 * i);

            configLevels.set("levels." + i + ".amount-of-xp", totalXPRequirdForLevelUp);

        }
        ConfigLevels.saveLevelConfig();

        return totalXPRequirdForLevelUp;
    }
}
