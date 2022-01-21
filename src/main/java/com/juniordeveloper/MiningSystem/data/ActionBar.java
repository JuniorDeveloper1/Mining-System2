package com.juniordeveloper.MiningSystem.data;


import com.juniordeveloper.MiningSystem.config.ConfigAchievement;
import com.juniordeveloper.MiningSystem.data.level.XpAmount;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ActionBar {

    private final boolean Disabled = ConfigAchievement.getAchievementConfig().getBoolean("System.action-bar");
    public void actionBar(Player player) {

        if(!Disabled) {


        int total = XpAmount.getTotalXPRequirdForLevelUp();
        float progress = (float) 0.1;
        String progressOut = "§6" + "|".repeat((int) (total *  progress)) + "§7" + "|".repeat((int) (total * (1 - progress)));


        player.sendActionBar(new TextComponent(progressOut));



        }
    }
}
