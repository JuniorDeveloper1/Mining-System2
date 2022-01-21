package com.juniordeveloper.MiningSystem.events;


import com.juniordeveloper.MiningSystem.config.ConfigLevel;
import com.juniordeveloper.MiningSystem.data.ActionBar;
import com.juniordeveloper.MiningSystem.data.SideBar;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class OnJoin implements Listener {

    private SideBar scoreboardManager = new SideBar();
    private ActionBar actionBar = new ActionBar();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUniqueId = player.getUniqueId();



        scoreboardManager.ScoreBoard(player);
        actionBar.actionBar(player);




        //Leveling Side
        if(!player.hasPlayedBefore()) {
            LevelingManager.levelingManagerArrayList.put(playerUniqueId, new LevelingManager(0, 0));

            ConfigLevel.getLevelingConfig().set("player_levels." + playerUniqueId + ".level", 0);
            ConfigLevel.getLevelingConfig().set("player_levels." + playerUniqueId + ".xp", 0 );
            ConfigLevel.saveLevelingConfig();
            LevelingManager.firstJoin();
        } else {
            int level = ConfigLevel.getLevelingConfig().getInt("player_levels." + playerUniqueId + ".xp", LevelingManager.getLevel());
            int current_xpamount = ConfigLevel.getLevelingConfig().getInt("player_levels" + playerUniqueId + ".xp", LevelingManager.getCurrent_xpamount());
            LevelingManager.levelingManagerArrayList.put(playerUniqueId, new LevelingManager(level, current_xpamount));
            ConfigLevel.saveLevelingConfig();



        }
        //ENDLeveling Side
    }

    //



}
