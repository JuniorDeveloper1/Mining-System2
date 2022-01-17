package com.juniordeveloper.MiningSystem.config;

import com.juniordeveloper.MiningSystem.MineMain;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigSystem {


    private static MineMain plugin = MineMain.getInstance();
    private static String systemConfigName = "miningsystem.yml";




    private static File systemConfigFile;
    private static FileConfiguration systemConfig;

    public static  FileConfiguration getSystemConfig() {
        return systemConfig;
    }

    public  static void createConfigFile() {

        systemConfigFile = new File(plugin.getDataFolder(), systemConfigName);
        if(!systemConfigFile.exists()) {
            systemConfigFile.getParentFile().mkdirs();
            plugin.saveResource(systemConfigName, false);
        }

        systemConfig = new YamlConfiguration();
        try {
            systemConfig.load(systemConfigFile);

        }catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveSystemConfig() {
        try {
            systemConfig.save(systemConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
