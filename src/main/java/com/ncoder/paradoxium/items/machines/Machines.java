package com.ncoder.paradoxium.items.machines;

import com.ncoder.paradoxium.Paradoxium;
import com.ncoder.paradoxium.items.multiblock.GiftWrapper;
import com.ncoder.paradoxium.utils.Categories;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Machines {

    public static final SlimefunItemStack WARP_CONNECTOR = new SlimefunItemStack(
            "WARP_CONNECTOR",
            Material.SMOOTH_STONE,
            "&6Warp Connector",
            "",
            "&7Used to connect warp plate."
    );

    public static void setup(Paradoxium plugin) {
        new WarpConnector(
                Categories.machinesIG,
                WARP_CONNECTOR,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.SMOOTH_STONE), new ItemStack(Material.ENDER_EYE, 2), new ItemStack(Material.SMOOTH_STONE),
                        new ItemStack(Material.ENDER_PEARL, 2), new ItemStack(Material.REDSTONE, 8), new ItemStack(Material.ENDER_PEARL, 2),
                        new ItemStack(Material.SMOOTH_STONE), new ItemStack(Material.ENDER_EYE, 2), new ItemStack(Material.SMOOTH_STONE)
                }
        ).register(plugin);

    }

}
