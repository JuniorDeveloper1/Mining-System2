package com.juniordeveloper.MiningSystem.commandmanager.commands;

import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class LevelCommand {



    public void getLevel() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("level"))
                .executes((sender, args) ->
                        {
                            sender.sendMessage("Your current level is:" + LevelingManager.getLevel());
                        }

                        ).register();
    }


    public void setLevel() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("setlevel"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) -> {
                    int amount = (int) args[0];
                    LevelingManager.setLevel(amount);
                })
                .register();
    }

    public void setPlayerLevel() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("set"))
                .withArguments(new PlayerArgument("player"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) -> {
                    Player otherPlayer = Bukkit.getPlayer((String) args[0]);
                    int amount = Integer.parseInt((String) args[1]);
                    assert otherPlayer != null;
                    LevelingManager.setPlayerLevel(otherPlayer, amount);
                    sender.sendMessage("You have added " + amount +"levels " + " to " + otherPlayer);
                    otherPlayer.sendMessage(sender.getName() + " has added " + amount + "level's");
        }).register();
    }




}
