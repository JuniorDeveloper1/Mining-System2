package com.juniordeveloper.MiningSystem.data;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.Random;

public class OreRandomizer {
    public static Material  oreRandomizer() {
        Random r = new Random();

        /**
         * Dit is een randomizer voor ores. Dit ga gebruikt worden bij: Als je een Blok mined, en je breekt hem veranderd deze in
         * BEDROCK, en daarna wordt het een random ore.
         */
        //TODO: Make PERCENTAGE system for blockspawning. Ask this via config file if it wants to insert percentages or all equal.


        java.util.List<Material> material =  Arrays.asList(Material.DIAMOND_ORE, Material.IRON_ORE, Material.LAPIS_ORE,
                                                           Material.COAL_ORE, Material.REDSTONE_ORE, Material.EMERALD_ORE,
                                                           Material.COPPER_ORE);



        int randomMaterial = r.nextInt(material.size());
        Material randomElement = material.get(randomMaterial);
        return randomElement;
    }





}
