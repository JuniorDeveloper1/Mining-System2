package com.juniordeveloper.MiningSystem.commandmanager;

import com.juniordeveloper.MiningSystem.commandmanager.commands.LevelCommand;
import com.juniordeveloper.MiningSystem.commandmanager.commands.XpCommand;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import dev.jorel.commandapi.CommandAPICommand;

public class CommandManager {
    static LevelCommand levelCommand = new LevelCommand();
    static XpCommand xpCommand = new XpCommand();

    public void loadcommands() {
        levelCommand.getLevel();
        levelCommand.setLevel();
        levelCommand.setPlayerLevel();
        xpCommand.getXP();
        xpCommand.setPlayerXP();
        xpCommand.setXP();




    }



}
