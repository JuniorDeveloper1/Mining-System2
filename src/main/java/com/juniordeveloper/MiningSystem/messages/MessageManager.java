package com.juniordeveloper.MiningSystem.messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public enum MessageManager {


    PLUGIN_ENABLE("plugin_enabled", ChatColor.GREEN + "The MiningSystem has activated, Version: " + Bukkit.getVersion() ), //<-- LET GOED OP DIT
    PLUGIN_DISABLE("plugin_disabled", ChatColor.RED + "The Mining System has deactivated, Version: " + Bukkit.getVersion()),
    ERROR("error", "&4 [ERROR]"),
    LEVELED_UP("level_up", ChatColor.GREEN + "You have LEVELED UP!"),
    XP_AMOUNT("xp_amount", "&4You recieved xp:");






    //Rest
    private final String name;
    private String message;
    private boolean updated;


    MessageManager(String name, String message) {
        this.name = name;
        this.message = message;
        this.updated = false;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getUpdated() {
        return updated;
    }

    protected void update() {
        this.updated = true;
    }
}

