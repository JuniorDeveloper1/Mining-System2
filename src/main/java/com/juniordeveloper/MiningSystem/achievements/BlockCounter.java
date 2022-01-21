package com.juniordeveloper.MiningSystem.achievements;

import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import org.bukkit.entity.Player;


public class BlockCounter {
    public static void unNamed(Player player) {

        
        LevelingManager.levelCheck(player);
    }


}
