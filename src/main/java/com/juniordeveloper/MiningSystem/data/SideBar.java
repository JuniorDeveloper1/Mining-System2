package com.juniordeveloper.MiningSystem.data;

import com.juniordeveloper.MiningSystem.config.ConfigAchievement;
import com.juniordeveloper.MiningSystem.config.ConfigLevel;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.util.UUID;

public class SideBar {

    boolean Disabled = ConfigAchievement.getAchievementConfig().getBoolean("System.scoreboard");



    public void ScoreBoard(Player player) {
        UUID playerUniqueId = player.getUniqueId();

        int configLevel = ConfigLevel.getLevelingConfig().getInt("player_levels." + playerUniqueId + ".level", LevelingManager.getLevel());
        int configCurrent_xpamount = ConfigLevel.getLevelingConfig().getInt("player_levels." + playerUniqueId + ".xp", LevelingManager.getCurrent_xpamount());
        if(!Disabled) {
            org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

            Objective obj = board.registerNewObjective("MineSystem", "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName( ChatColor.AQUA.toString() +  ChatColor.BOLD + "Mine System");





            Score level = obj.getScore("Level: " + String.valueOf(configLevel));
                  level.setScore(1);

            Score space = obj.getScore("   ");
                  space.setScore(2);

            Score xpAmount = obj.getScore("XP: " + String.valueOf(configCurrent_xpamount));
                  xpAmount.setScore(3);


            player.setScoreboard(board);
        }
    }
}
