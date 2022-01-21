package com.juniordeveloper.MiningSystem;


import com.juniordeveloper.MiningSystem.commandmanager.CommandManager;
import com.juniordeveloper.MiningSystem.config.ConfigAchievement;
import com.juniordeveloper.MiningSystem.config.ConfigLevel;
import com.juniordeveloper.MiningSystem.config.ConfigLevels;
import com.juniordeveloper.MiningSystem.config.ConfigSystem;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import com.juniordeveloper.MiningSystem.events.OnBreak;
import com.juniordeveloper.MiningSystem.events.OnJoin;
import com.juniordeveloper.MiningSystem.events.PlayerQuit;
import com.juniordeveloper.MiningSystem.messages.MessageManager;
import com.zaxxer.hikari.HikariDataSource;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
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
    public static CommandManager commands = new CommandManager();


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
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIConfig().verboseOutput(true));






    }

    @Override
    public void onEnable() {
        instance = this;
        CommandAPI.onEnable(this);
        configs();

      //  hikariDataSource = new HikariDataSource(new HikariConfig(){{
      //      super.setJdbcUrl("sqlite://G:\\Coding\\SQLLITE\\DATABASE.db");
      //  }});


        Bukkit.getServer().getPluginManager().registerEvents(new OnBreak(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);


        commands.loadcommands();
        System.out.println(MessageManager.PLUGIN_ENABLE.getMessage());


    }
    public void configs() {
        ConfigAchievement.createAchievementFile();
        ConfigLevel.createLevelingFile();
        ConfigSystem.createConfigFile();
        ConfigLevels.createLevelingFile();


        ConfigSystem.saveSystemConfig();

    }

}
