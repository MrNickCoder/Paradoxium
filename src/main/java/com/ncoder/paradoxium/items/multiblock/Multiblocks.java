package com.ncoder.paradoxium.items.multiblock;

import com.ncoder.paradoxium.Paradoxium;
import com.ncoder.paradoxium.utils.Categories;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Multiblocks {

    public static final SlimefunItemStack GIFT_WRAPPER = new SlimefunItemStack(
            "GIFT_WRAPPER",
            Material.RED_MUSHROOM_BLOCK,
            "&4Gift Wrapper",
            "",
            "&7Make your own present for other players."
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
    }

}
