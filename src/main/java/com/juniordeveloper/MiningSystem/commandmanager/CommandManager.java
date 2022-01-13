package com.juniordeveloper.MiningSystem.commandmanager;

import com.juniordeveloper.MiningSystem.commandmanager.commands.LevelCommand;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import dev.jorel.commandapi.CommandAPICommand;

public class CommandManager {
    static LevelCommand levelCommand = new LevelCommand();

    public void loadcommands() {
        levelCommand.setLevel();
        levelCommand.getLevel();
        levelCommand.setPlayerLevel();



    }



}
