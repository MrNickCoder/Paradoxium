package com.ncoder.paradoxium.items;

import com.ncoder.paradoxium.Paradoxium;
import com.ncoder.paradoxium.items.blocks.Blocks;
import com.ncoder.paradoxium.items.gears.Gears;
import com.ncoder.paradoxium.items.generators.Generators;
import com.ncoder.paradoxium.items.machines.Machines;
import com.ncoder.paradoxium.items.materials.Materials;
import com.ncoder.paradoxium.items.multiblock.Multiblocks;
import com.ncoder.paradoxium.items.smarts.SmartItems;
import com.ncoder.paradoxium.utils.Categories;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ParadoxItems {

    public static void setup(@Nonnull Paradoxium plugin) {
        Materials.setup(plugin);
        Blocks.setup(plugin);
        Gears.setup(plugin);

        Machines.setup(plugin);
        Generators.setup(plugin);
        Multiblocks.setup(plugin);
        SmartItems.setup(plugin);
    }

    public static void registerEnhanced(SlimefunItemStack itemStack, ItemStack[] recipe) {
        register(itemStack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
    }

    public static void registerEnhanced(SlimefunItemStack itemStack, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        register(itemStack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe, recipeOutput);
    }

    public static void registerSmeltery(SlimefunItemStack itemStack, ItemStack[] recipe) {
        register(itemStack, RecipeType.SMELTERY, recipe);
    }

    public static void registerSmeltery(SlimefunItemStack itemStack, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        register(itemStack, RecipeType.SMELTERY, recipe, recipeOutput);
    }

    public static void registerNull(SlimefunItemStack itemStack, ItemStack[] recipe) {
        register(itemStack, RecipeType.NULL, recipe);
    }

    public static void registerNull(SlimefunItemStack itemStack, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        register(itemStack, RecipeType.NULL, recipe, recipeOutput);
    }

    public static void register(SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        register(Categories.materialsIG, itemStack, recipeType, recipe, null);
    }

    public static void register(SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        register(Categories.materialsIG, itemStack, recipeType, recipe, recipeOutput);
    }

    public static void register(ItemGroup itemGroup, SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        new SlimefunItem(itemGroup, itemStack, recipeType, recipe, recipeOutput).register(Paradoxium.instance());
    }

}
