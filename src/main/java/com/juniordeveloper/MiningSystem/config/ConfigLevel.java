package com.juniordeveloper.MiningSystem.config;

import com.juniordeveloper.MiningSystem.MineMain;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class ConfigLevel {
    private static MineMain plugin = MineMain.getInstance();
    private static String levelingConfigName = "leveling.yml";




    private static File levelingConfigFile;
    private static FileConfiguration levelingConfig;

    public static  FileConfiguration getLevelingConfig() {
        return levelingConfig;
    }

    public  static void createLevelingFile() {

        levelingConfigFile = new File(plugin.getDataFolder(), levelingConfigName);
        if(!levelingConfigFile.exists()) {
            levelingConfigFile.getParentFile().mkdirs();
            plugin.saveResource(levelingConfigName, false);
        }

        levelingConfig = new YamlConfiguration();
        try {
            levelingConfig.load(levelingConfigFile);

        }catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            }
    }

    public static void saveLevelingConfig() {
        try {
            levelingConfig.save(levelingConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
