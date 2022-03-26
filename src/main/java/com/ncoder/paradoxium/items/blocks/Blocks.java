package com.ncoder.paradoxium.items.blocks;

import com.ncoder.paradoxium.Paradoxium;
import com.ncoder.paradoxium.utils.Categories;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Blocks {

    public static final SlimefunItemStack WARP_PLATE_100 = new SlimefunItemStack(
            "WARP_PLATE_100",
            Material.WHITE_CARPET,
            "&6Warp Plate &7&l[&4&l100&7&l]",
            "",
            "&6&lCrouch &7on the plate to teleport",
            "&7to the other destination.",
            "",
            "&a&lDetection&7: &6100 &7Blocks"
    );

    public static final SlimefunItemStack WARP_PLATE_500 = new SlimefunItemStack(
            "WARP_PLATE_500",
            Material.WHITE_CARPET,
            "&6Warp Plate &7&l[&4&l500&7&l]",
            "",
            "&6&lCrouch &7on the plate to teleport",
            "&7to the other destination.",
            "",
            "&a&lDetection&7: &6500 &7Blocks"
    );

    public static final SlimefunItemStack WARP_PLATE_1000 = new SlimefunItemStack(
            "WARP_PLATE_1000",
            Material.WHITE_CARPET,
            "&6Warp Plate &7&l[&4&l1000&7&l]",
            "",
            "&6&lCrouch &7on the plate to teleport",
            "&7to the other destination.",
            "",
            "&a&lDetection&7: &61000 &7Blocks"
    );

    public static void setup(Paradoxium plugin) {
        new WarpPlate(
                Categories.blocksIG,
                WARP_PLATE_100,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        null, null, null,
                        null, new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE), null,
                        null, new ItemStack(Material.ENDER_EYE), null
                },
                new CustomItemStack(WARP_PLATE_100, 2),
                100
        ).register(plugin);

        new WarpPlate(
                Categories.blocksIG,
                WARP_PLATE_500,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        null, null, null,
                        WARP_PLATE_100, WARP_PLATE_100, WARP_PLATE_100,
                        WARP_PLATE_100, new ItemStack(Material.ENDER_EYE), WARP_PLATE_100
                },
                new CustomItemStack(WARP_PLATE_500, 1),
                500
        ).register(plugin);

        new WarpPlate(
                Categories.blocksIG,
                WARP_PLATE_1000,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        null, null, null,
                        WARP_PLATE_500, WARP_PLATE_500, WARP_PLATE_500,
                        new ItemStack(Material.ENDER_EYE), SlimefunItems.MAGIC_EYE_OF_ENDER, new ItemStack(Material.ENDER_EYE)
                },
                new CustomItemStack(WARP_PLATE_1000, 1),
                1000
        ).register(plugin);

    }

}
