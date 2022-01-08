package com.juniordeveloper.MiningSystem.events;

import com.juniordeveloper.MiningSystem.config.ConfigLevel;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuit implements Listener {

    FileConfiguration configLevel = ConfigLevel.getLevelingConfig();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UUID playerUniqueId = player.getUniqueId();

        if(LevelingManager.levelingManagerArrayList.containsKey(playerUniqueId)) {
            configLevel.set("player_levels." + playerUniqueId + ".level", LevelingManager.getLevel());
            configLevel.set("player_levels." + playerUniqueId + ".level", LevelingManager.getCurrent_xpamount());
            ConfigLevel.saveLevelingConfig();
            LevelingManager.levelingManagerArrayList.remove(playerUniqueId);
        }
    }
}
