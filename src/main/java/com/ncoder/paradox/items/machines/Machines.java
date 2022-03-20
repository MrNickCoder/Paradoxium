package com.ncoder.paradox.items.machines;

import com.ncoder.paradox.Paradoxium;
import com.ncoder.paradox.items.materials.Shards;
import com.ncoder.paradox.utils.Categories;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Machines {

    public static final SlimefunItemStack GIFT_WRAPPER = new SlimefunItemStack(
            "GIFT_WRAPPER",
            Material.RED_MUSHROOM_BLOCK,
            "&4Gift Wrapper",
            "",
            "&7Make your own present for other players."
    );

    public static final SlimefunItemStack SHARD_TABLE = new SlimefunItemStack(
            "SHARD_TABLE",
            Material.SMITHING_TABLE,
            "&aShard Table",
            "",
            "&7Used to apply shards to your gear."
    );

    public static final SlimefunItemStack WARP_CONNECTOR = new SlimefunItemStack(
            "WARP_CONNECTOR",
            Material.SMOOTH_STONE,
            "&6Warp Connector",
            "",
            "&7Used to connect warp plate."
    );

    public static void setup(Paradoxium plugin) {
        new GiftWrapper(
                Categories.machinesIG,
                GIFT_WRAPPER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.RAINBOW_WOOL, new CustomItemStack(SlimefunItems.CLOTH, 64), SlimefunItems.RAINBOW_WOOL,
                        SlimefunItems.STEEL_PLATE, SlimefunItems.BACKPACK_SMALL, SlimefunItems.STEEL_PLATE,
                        SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.HARDENED_METAL_INGOT
                }
        ).register(plugin);

        new ShardTable(
                Categories.machinesIG,
                SHARD_TABLE,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        Shards.EARTH_SHARD, null, Shards.FIRE_SHARD,
                        null, new ItemStack(Material.SMITHING_TABLE), null,
                        Shards.AIR_SHARD, null, Shards.WATER_SHARD,
                }
        ).register(plugin);

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
