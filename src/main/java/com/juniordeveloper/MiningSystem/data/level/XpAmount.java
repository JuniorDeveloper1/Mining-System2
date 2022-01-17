package com.juniordeveloper.MiningSystem.data.level;


import org.bukkit.entity.Player;

import java.util.logging.Level;

import static com.juniordeveloper.MiningSystem.data.level.LevelingManager.*;


public class XpAmount {
    static Player player;
    /**
     * @Link{ https://www.spigotmc.org/threads/level-system.356440/ }
     *
     * TODO: Fix better LEVELING system
     */

   private  static int totalXPRequirdForLevelUp;

    public static int  getTotalXPRequirdForLevelUp() {

        int level = getLevel();



        if(level >= LevelingManager.getMaxmimum()) {
            totalXPRequirdForLevelUp = (int) (getMultiplier() + Math.pow(level, 1.5) + 10 * level);
        }


        for (int i = 0; i < LevelingManager.getMaxmimum(); i++) {
            totalXPRequirdForLevelUp = (int) (getMultiplier() + Math.pow(i, 1.5) + 10 * i);
            System.out.println("Level " + i + " Amount of xp  " + totalXPRequirdForLevelUp);
        }

        return totalXPRequirdForLevelUp;
    }
}
