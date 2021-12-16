package Achievements;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Rewards {
    public static void giveReward(Player player, String message, Material material, int amount) {
        Player name = player.getPlayer();
        player.sendMessage(name.getName() + message);
        ItemStack itemstack  = new ItemStack(material, amount);
        player.getInventory().addItem(itemstack);

        /**
         * Geeft de rewards aan de persoon.
         */

    }
}
