package com.juniordeveloper.MiningSystem.config;

import com.juniordeveloper.MiningSystem.MineMain;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigAchievement  {

    private static MineMain plugin = MineMain.getInstance();
    private static String achievementConfigName = "achievement.yml";




    private static File achievementConfigFile;
    private static FileConfiguration achievementConfig;

    public static  FileConfiguration getAchievementConfig() {
        return achievementConfig;
    }

    public  static void createAchievementFile() {
        achievementConfigFile = new File(plugin.getDataFolder(), achievementConfigName);
        if(!achievementConfigFile.exists()) {
            achievementConfigFile.getParentFile().mkdirs();
            plugin.saveResource(achievementConfigName, false);
        }

        achievementConfig = new YamlConfiguration();
        try {
            achievementConfig.load(achievementConfigFile);

        }catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveAchievementConfig() {
        try {
            achievementConfig.save(achievementConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
