package com.juniordeveloper.MiningSystem.achievements;

import com.juniordeveloper.MiningSystem.MineMain;
import com.juniordeveloper.MiningSystem.config.ConfigAchievement;
import org.bukkit.Material;



public enum AchievementsMaterial {


    /**
     * Materials:
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
    DIAMOND_ORE(Material.DIAMOND_ORE, ConfigAchievement.getAchievementConfig().getInt("ore_XP.DIAMOND_ORE.xp")),
    COAL_ORE(Material.COAL_ORE, ConfigAchievement.getAchievementConfig().getInt("ore_XP.COAL_ORE.xp")),
    IRON_ORE(Material.IRON_ORE, ConfigAchievement.getAchievementConfig().getInt("ore_XP.IRON_ORE.xp")),
    GOLD_ORE(Material.GOLD_ORE, ConfigAchievement.getAchievementConfig().getInt("ore_XP.GOLD_ORE.xp")),
    REDSTONE_ORE(Material.REDSTONE_ORE, ConfigAchievement.getAchievementConfig().getInt("ore_XP.REDSTONE_ORE.xp")),
    LAPIS_ORE(Material.LAPIS_ORE, ConfigAchievement.getAchievementConfig().getInt("ore_XP.LAPIS_ORE.xp")),
    EMERALD_ORE(Material.EMERALD_ORE, ConfigAchievement.getAchievementConfig().getInt("ore_XP.EMERALD_ORE.xp")),
    QUARTZ_ORE(Material.NETHER_QUARTZ_ORE, ConfigAchievement.getAchievementConfig().getInt("ore_XP.QUARTZ_ORE.xp"));


     //  GOLD_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.GOLD_ORE"), "gold_ore"),
     //    REDSTONE_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.REDSTONE_ORE"), "redstone_ore"),
     //    LAPIS_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.LAPIS_ORE"), "lapis_ore"),
     //    EMERALD_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.EMERALD_ORE"), "emerald_ore"),
     //    QUARTZ_ORE(ConfigAchievement.getAchievementConfig().getString("ore_XP.QUARTZ_ORE"), "quartz_ore");



    private static MineMain plugin;
    private Material material;
    private int xpAmountPerBlock;

    AchievementsMaterial(Material material, int xpAmountPerBlock) {
        this.material = material;
        this.xpAmountPerBlock = xpAmountPerBlock;
    }

    public int getXpAmountPerBlock() {return xpAmountPerBlock;}

    public Material getMaterial() {
        return material;
    }

    protected void setMaterial(Material material) {
        this.material = material;
    }

}
