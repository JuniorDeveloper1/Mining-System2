package com.juniordeveloper.MiningSystem.commandmanager.commands;

import com.juniordeveloper.MiningSystem.data.level.LevelingManager;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class XpCommand {

    public  void getXP() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("xp"))
                .executes((sender, args) -> {
                    sender.sendMessage("Your xp is: " + LevelingManager.getCurrent_xpamount());
                }).register();
    }

    public void setXP() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("set"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) ->
                        {
                            int amount = (int) args[2];
                            LevelingManager.setCurrent_xpamount(LevelingManager.getCurrent_xpamount() + amount);
                            sender.sendMessage("Your new xp is: " + LevelingManager.getCurrent_xpamount());

                        }

                ).register();

    }

    public void setPlayerXP() {
        new CommandAPICommand("mining")
                .withArguments(new LiteralArgument("set"))
                .withArguments(new PlayerArgument("player"))
                .withArguments(new IntegerArgument("amount"))
                .executes((sender, args) ->
                {
                    Player otherPlayer = Bukkit.getPlayer((String) args[2]);
                    int amount = (int) args[3];
                    LevelingManager.setPlayerXP(otherPlayer, amount);
                    sender.sendMessage("You have added " + amount +"xp " + " to " + otherPlayer);
                    otherPlayer.sendMessage(sender.getName() + " has added " + amount + "xp");
                }
                ).register();

    }

}
