package com.juniordeveloper.MiningSystem;


import com.juniordeveloper.MiningSystem.config.ConfigAchievement;
import com.juniordeveloper.MiningSystem.config.ConfigLevel;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import com.juniordeveloper.MiningSystem.events.onBreak;
import com.juniordeveloper.MiningSystem.events.onJoin;
import com.juniordeveloper.MiningSystem.messages.MessageManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MineMain extends JavaPlugin {
    public static HikariDataSource hikariDataSource;

    public static MineMain instance;

    public static MineMain getInstance() {return instance;}

    @Override
    public @Nullable PluginCommand getCommand(@NotNull String name) {
        return super.getCommand(name);
    }




    @Override
    public void onDisable() {
        System.out.println(MessageManager.PLUGIN_DISABLE.getMessage());
        for(Player players : Bukkit.getOnlinePlayers()) {
            UUID playersUniqueID = players.getUniqueId();
            LevelingManager.levelingManagerArrayList.remove(playersUniqueID);
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        hikariDataSource = new HikariDataSource(new HikariConfig(){{
            super.setJdbcUrl("sqlite://G:\\Coding\\SQLLITE\\DATABASE.db");
        }});

        Listeners();
        Configs();
        System.out.println(MessageManager.PLUGIN_ENABLE.getMessage());

    }

    public void Listeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new onBreak(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onJoin(), this);

    }
    public void Configs() {
        ConfigAchievement.createAchievementFile();
        ConfigLevel.createLevelingFile();
    }
}
