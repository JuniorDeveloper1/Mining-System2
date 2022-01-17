package com.juniordeveloper.MiningSystem.data.level;

import com.juniordeveloper.MiningSystem.config.ConfigSystem;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.UUID;

public class LevelingManager {

    public static HashMap<UUID, LevelingManager> levelingManagerArrayList = new HashMap<>();

    private  static int level = 0;
     private static int multiplier = ConfigSystem.getSystemConfig().getInt("System.multiplier"); // Multiplier has to be changed to config.
    private  static int maxmimum = ConfigSystem.getSystemConfig().getInt("System.maximum-xp") + 1; // Set this in a config.
    private static int current_xpamount = 0;


    public LevelingManager(Integer level, Integer current_xpamount) {
        LevelingManager.level = level;
        LevelingManager.current_xpamount = current_xpamount;
    }


  //  public static void init() {
  //     try(Connection c = MineMain.hikariDataSource.getConnection()) {
  //} catch (SQLException e) {
  //         e.printStackTrace();
    //
    //}

   // }

    public static void firstJoin() {LevelingManager.setLevel(1);}

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        LevelingManager.level = level;
    }

    public static int getMultiplier() {
        return multiplier;
    }

    public static void setMultiplier(int multiplier) {
        LevelingManager.multiplier = multiplier;
    }

    public static int getMaxmimum() {
        return maxmimum;
    }

    public static void setMaxmimum(int maxmimum) {
        LevelingManager.maxmimum = maxmimum;
    }

    @Contract(pure = true)
    public static int getCurrent_xpamount() {
        return current_xpamount;
    }

    public static void setCurrent_xpamount(int current_xpamount) {
        LevelingManager.current_xpamount = current_xpamount;
    }

    public static void levelCheck() {
        if(LevelingManager.getCurrent_xpamount() <= XpAmount.getTotalXPRequirdForLevelUp())
            LevelingManager.setLevel(LevelingManager.getLevel()+1);
    }

    public static void setPlayerLevel(Player player, int amount) {
        levelingManagerArrayList.put(player.getUniqueId(), new LevelingManager(amount, null));
    }
    public static void setPlayerXP(Player player, int current_xpamount) {
        levelingManagerArrayList.put(player.getUniqueId(), new LevelingManager(null, current_xpamount));
    }


}
