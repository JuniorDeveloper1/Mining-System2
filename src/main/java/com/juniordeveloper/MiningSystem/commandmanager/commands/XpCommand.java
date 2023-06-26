package com.juniordeveloper.MiningSystem.commandmanager.commands;

import com.juniordeveloper.MiningSystem.config.ConfigLevel;
import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class XpCommand {

    public  void getXP() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("xp"))
                .executes((sender, args) -> {

                    UUID playerUniqueId = ((Player) sender).getUniqueId();
                    int current_level = ConfigLevel.getLevelingConfig().getInt("player_levels" + playerUniqueId + ".xp", LevelingManager.getCurrent_xpamount());

                    sender.sendMessage("Your current level is:" + current_level);
                }).register();
    }

    public void setXP() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("xp"))
                .withArguments(new LiteralArgument("set"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) ->
                        {
                            int amount = (int) args[2];
                            UUID playerUniqueId = ((Player) sender).getUniqueId();
                            int currentAmount  = ConfigLevel.getLevelingConfig().getInt("player_levels" + playerUniqueId + ".xp");
                            LevelingManager.setCurrent_xpamount(currentAmount +  amount);
                            sender.sendMessage("Your new xp is: " + LevelingManager.getCurrent_xpamount());
                            ConfigLevel.saveLevelingConfig();
                            ConfigLevel.reloadLevelingConfig();

                        }

                ).register();

    }

    public void setPlayerXP() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("xp"))
                .withArguments(new LiteralArgument("set"))
                .withArguments(new PlayerArgument("player"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) ->
                {
                    Player otherPlayer = Bukkit.getPlayer((String) args[0]);
                    int amount = (int) args[1];
                    assert otherPlayer != null;
                    int currentAmount  = ConfigLevel.getLevelingConfig().getInt("player_levels" + otherPlayer.getUniqueId() + ".xp");
                    LevelingManager.setPlayerXP(otherPlayer, currentAmount + amount);
                    sender.sendMessage("You have added " + amount +"xp " + " to " + otherPlayer);
                    otherPlayer.sendMessage(sender.getName() + " has added " + amount + "xp");
                    ConfigLevel.saveLevelingConfig();
                    ConfigLevel.reloadLevelingConfig();
                }
                ).register();

    }

}
