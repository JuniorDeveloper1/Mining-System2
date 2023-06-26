package com.juniordeveloper.MiningSystem.commandmanager.commands;

import com.juniordeveloper.MiningSystem.config.ConfigLevel;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LevelCommand {


    public void getLevel() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("level"))
                .executes((sender, args) ->
                        {
                            UUID playerUniqueId = ((Player) sender).getUniqueId();
                            int current_xpamount = ConfigLevel.getLevelingConfig().getInt("player_levels" + playerUniqueId + ".level", LevelingManager.getLevel());

                            sender.sendMessage("Your current level is:" + current_xpamount);

                        }

                ).register();
    }


    public void setLevel() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("level"))
                .withArguments(new StringArgument("set"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) -> {
                    UUID playerUniqueId = ((Player) sender).getUniqueId();
                    int amount = (int) args[1];

                    int currentAmount = ConfigLevel.getLevelingConfig().getInt("player_levels" + playerUniqueId + ".level");
                    LevelingManager.setLevel(currentAmount + amount);
                    ConfigLevel.saveLevelingConfig();
                    ConfigLevel.reloadLevelingConfig();

                })
                .register();
    }


    public void setPlayerLevel() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("level"))
                .withArguments(new StringArgument("set"))
                .withArguments(new PlayerArgument("player"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) -> {
                    Player otherPlayer = Bukkit.getPlayer((String) args[1]);
                    int amount = Integer.parseInt((String) args[2]);
                    assert otherPlayer != null;
                    int currentAmount = ConfigLevel.getLevelingConfig().getInt("player_levels" + otherPlayer.getUniqueId() + ".xp");
                    LevelingManager.setPlayerLevel(otherPlayer, currentAmount + amount);
                    ConfigLevel.saveLevelingConfig();
                    ConfigLevel.reloadLevelingConfig();


                    //Message
                    sender.sendMessage("You have added " + amount + "levels " + " to " + otherPlayer);
                    otherPlayer.sendMessage(sender.getName() + " has added " + amount + "level's");
                }).register();
    }


}
