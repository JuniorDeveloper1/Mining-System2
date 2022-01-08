
package com.juniordeveloper.MiningSystem.events;

import com.juniordeveloper.MiningSystem.achievements.AchievementsMaterial;
import com.juniordeveloper.MiningSystem.config.ConfigAchievement;
import com.juniordeveloper.MiningSystem.data.OreRandomizer;
import com.juniordeveloper.MiningSystem.MineMain;
import com.juniordeveloper.MiningSystem.data.StaticMaps;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class onBreak implements Listener {


    /**
     * Voor mij zelf;
     *  <-- Mining System  -->
     *  - Check welke block dat er gebroken is, Als het in het systeem zit, verander deze naar een nieuwe block.
     *                      </-->
     *
     * <-- Leveling System -->
     *     Pak de Current XP amount + De XP per block.
     *     </-->
     */
    MineMain plugin = MineMain.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {

        FileConfiguration achievementConfig = ConfigAchievement.getAchievementConfig();

        //Quick level check with every block break?
        LevelingManager.levelCheck();



        Player player = event.getPlayer();
        UUID playerUniqueId = player.getUniqueId(); //Player UUID

        Block block = event.getBlock();
        @NotNull Material blockType = event.getBlock().getType();







        Material bedrock = Material.BEDROCK;

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

        if(Arrays.stream(new Material[] {Material.DIAMOND_ORE, Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE,
                Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.LAPIS_ORE,
                Material.EMERALD_ORE, Material.NETHER_QUARTZ_ORE} ).anyMatch(t -> blockType == t)) {
            block.setType(bedrock);
            Bukkit.getScheduler().runTaskLater(MineMain.getInstance(), () -> block.setType(OreRandomizer.oreRandomizer()), 1200);
        }




        if(blockType.equals(AchievementsMaterial.DIAMOND_ORE.getMaterial())) {
        LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.DIAMOND_ORE.getXpAmountPerBlock());

        }

        if(blockType.equals(AchievementsMaterial.COAL_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.COAL_ORE.getXpAmountPerBlock());

        //Give xp
        }

        if(blockType.equals(AchievementsMaterial.IRON_ORE.getMaterial())){
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.IRON_ORE.getXpAmountPerBlock());

        }

        if(blockType.equals(AchievementsMaterial.GOLD_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.GOLD_ORE.getXpAmountPerBlock());

        }

        if(blockType.equals(AchievementsMaterial.REDSTONE_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.REDSTONE_ORE.getXpAmountPerBlock());

        }

        if(blockType.equals(AchievementsMaterial.LAPIS_ORE.getMaterial())){
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.LAPIS_ORE.getXpAmountPerBlock());
        }

        if(blockType.equals(AchievementsMaterial.EMERALD_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.EMERALD_ORE.getXpAmountPerBlock());
        }

        if(blockType.equals(AchievementsMaterial.QUARTZ_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.QUARTZ_ORE.getXpAmountPerBlock());
        }




    }

}
