package Data;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.Random;

public class StaticList {
    public static void  OreRandomizer() {

        /**
         * Dit is een randomizer voor ores. Dit ga gebruikt worden bij: Als je een Blok mined, en je breekt hem veranderd deze in
         * BEDROCK, en daarna wordt het een random ore.
         */
        java.util.List<Material> material =  Arrays.asList(Material.DIAMOND_ORE, Material.IRON_ORE, Material.LAPIS_ORE,
                                                           Material.COAL_ORE, Material.REDSTONE_ORE, Material.EMERALD_ORE,
                                                           Material.COPPER_ORE);

        Random r = new Random();
        int randomMaterial = r.nextInt(material.size());
        Material randomElement = material.get(randomMaterial);


    }





}
