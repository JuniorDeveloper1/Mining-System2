
package com.juniordeveloper.MiningSystem.events;

import com.juniordeveloper.MiningSystem.MineMain;
import com.juniordeveloper.MiningSystem.achievements.AchievementsMaterial;
import com.juniordeveloper.MiningSystem.config.ConfigAchievement;
import com.juniordeveloper.MiningSystem.data.OreRandomizer;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import com.juniordeveloper.MiningSystem.messages.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;

public class OnBreak implements Listener {


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

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        FileConfiguration achievementConfig = ConfigAchievement.getAchievementConfig();

        //Quick level check with every block break?




        Player player = event.getPlayer();
        UUID playerUniqueId = player.getUniqueId(); //Player UUID

        Block block = event.getBlock();
        @NotNull Material blockType = event.getBlock().getType();
        LevelingManager.levelCheck(player);










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

        Material[] materials = new Material[] {Material.DIAMOND_ORE, Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE,
                Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.LAPIS_ORE,
                Material.EMERALD_ORE, Material.NETHER_QUARTZ_ORE, Material.COPPER_ORE};

        if(Arrays.stream(materials).anyMatch(t -> blockType.equals(t))) {

            Bukkit.getScheduler().runTaskLater(MineMain.getInstance(), () -> block.setType(Material.BEDROCK), 1);
            Bukkit.getScheduler().runTaskLater(MineMain.getInstance(), () -> block.setType(OreRandomizer.oreRandomizer()), 20);
        }




        if(blockType.equals(AchievementsMaterial.DIAMOND_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.DIAMOND_ORE.getXpAmountPerBlock());
            player.sendMessage(MessageManager.XP_AMOUNT.getMessage()  + AchievementsMaterial.DIAMOND_ORE.getXpAmountPerBlock());
        }

        if(blockType.equals(AchievementsMaterial.COAL_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.COAL_ORE.getXpAmountPerBlock());

            player.sendMessage(MessageManager.XP_AMOUNT.getMessage() + AchievementsMaterial.COAL_ORE.getXpAmountPerBlock());

            //Give xp
        }

        if(blockType.equals(AchievementsMaterial.IRON_ORE.getMaterial())){
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.IRON_ORE.getXpAmountPerBlock());
            player.sendMessage(MessageManager.XP_AMOUNT.getMessage() + AchievementsMaterial.IRON_ORE.getXpAmountPerBlock());

        }

        if(blockType.equals(AchievementsMaterial.GOLD_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.GOLD_ORE.getXpAmountPerBlock());
            player.sendMessage(MessageManager.XP_AMOUNT.getMessage() + AchievementsMaterial.GOLD_ORE.getXpAmountPerBlock());

        }

        if(blockType.equals(AchievementsMaterial.REDSTONE_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.REDSTONE_ORE.getXpAmountPerBlock());
            player.sendMessage(MessageManager.XP_AMOUNT.getMessage() + AchievementsMaterial.REDSTONE_ORE.getXpAmountPerBlock());

        }

        if(blockType.equals(AchievementsMaterial.LAPIS_ORE.getMaterial())){
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.LAPIS_ORE.getXpAmountPerBlock());
            player.sendMessage(MessageManager.XP_AMOUNT.getMessage() + AchievementsMaterial.LAPIS_ORE.getXpAmountPerBlock());
        }

        if(blockType.equals(AchievementsMaterial.EMERALD_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.EMERALD_ORE.getXpAmountPerBlock());
            player.sendMessage(MessageManager.XP_AMOUNT.getMessage() + AchievementsMaterial.EMERALD_ORE.getXpAmountPerBlock());
        }

        if(blockType.equals(AchievementsMaterial.QUARTZ_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.QUARTZ_ORE.getXpAmountPerBlock());
            player.sendMessage(MessageManager.XP_AMOUNT.getMessage() + AchievementsMaterial.QUARTZ_ORE.getXpAmountPerBlock());
        }

        if(blockType.equals(AchievementsMaterial.COPPER_ORE.getMaterial())) {
            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + AchievementsMaterial.COPPER_ORE.getXpAmountPerBlock());
            player.sendMessage(MessageManager.XP_AMOUNT.getMessage() + AchievementsMaterial.COPPER_ORE.getXpAmountPerBlock());
        }




    }

}
