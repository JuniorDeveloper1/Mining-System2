package com.juniordeveloper.MiningSystem.achievements;

import com.juniordeveloper.MiningSystem.config.ConfigAchievement;
import org.bukkit.Material;

import java.io.ObjectInputFilter;

public enum AchievementsMaterial {

    /**
     * DIAMOND
     * COAL
     * IRON
     * GOLD
     * REDSTONE
     * LAPIS
     * EMERALD
     * QUARTZ
     */


    //Verander dit naar Config file?
    DIAMOND_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.DIAMOND_ORE"), "diamond_ore"),
    COAL_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.COAL_ORE"), "coal_ore"),
    IRON_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.IRON_ORE"), "iron_ore"),
    GOLD_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.GOLD_ORE"), "gold_ore"),
    REDSTONE_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.REDSTONE_ORE"), "redstone_ore"),
    LAPIS_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.LAPIS_ORE"), "lapis_ore"),
    EMERALD_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.EMERALD_ORE"), "emerald_ore"),
    QUARTZ_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.QUARTZ_ORE"), "quartz_ore");



    private String material;
    private String name;

    AchievementsMaterial(String material, String name) {
        this.material = material;
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    protected void setMaterial(String material) {
        this.material = material;
    }

}
