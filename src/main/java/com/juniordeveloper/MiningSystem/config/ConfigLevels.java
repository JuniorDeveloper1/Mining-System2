package com.juniordeveloper.MiningSystem.config;

import com.juniordeveloper.MiningSystem.MineMain;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigLevels {

    //This is a current Config might be deleted later

    private static MineMain plugin = MineMain.getInstance();
    private static String levelConfigName = "levels.yml";




    private static File levelConfigFile;
    private static FileConfiguration levelConfig;

    public static  FileConfiguration getLevelConfig() {
        return levelConfig;
    }

    public  static void createLevelingFile() {

        levelConfigFile = new File(plugin.getDataFolder(), levelConfigName);
        if(!levelConfigFile.exists()) {
            levelConfigFile.getParentFile().mkdirs();
            plugin.saveResource(levelConfigName, false);
        }

        levelConfig = new YamlConfiguration();
        try {
            levelConfig.load(levelConfigFile);

        }catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveLevelConfig() {
        try {
            levelConfig.save(levelConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
