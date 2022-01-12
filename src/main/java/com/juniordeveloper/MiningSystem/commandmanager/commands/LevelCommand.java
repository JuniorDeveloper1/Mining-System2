package com.juniordeveloper.MiningSystem.commandmanager.commands;

import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class LevelCommand {



    public void getLevel() {
        new CommandAPICommand("mining")
                .withArguments(new StringArgument("level"))
                .executes((sender, args) ->
                        {
                            String getlevel = String.valueOf(args[2]);

                            sender.sendMessage("Your current level is:" + getlevel + LevelingManager.getLevel());


                        }

                        ).register();
    }

    public void setLevel() {
        new CommandAPICommand("mining")
                .withArguments(new StringArgument("setlevel"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) -> {
                    int amount = (int) args[2];
                    LevelingManager.setLevel(amount);
                })
                .register();
    }

    public void setPlayerLevel() {
        new CommandAPICommand("mining")
                .withArguments(new StringArgument("set"))
                .withArguments(new PlayerArgument("player"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) -> {
                    Player otherPlayer = Bukkit.getPlayer((String) args[2]);
                    int amount = Integer.parseInt((String) args[3]);
                    assert otherPlayer != null;
                    LevelingManager.setPlayerLevel(otherPlayer, amount);
                    sender.sendMessage(otherPlayer + "'s," + "level is now " + LevelingManager.getLevel());




        }).register();
    }


}
