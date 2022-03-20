package com.ncoder.paradox.items;

import com.ncoder.paradox.Paradoxium;
import com.ncoder.paradox.items.blocks.Blocks;
import com.ncoder.paradox.items.generators.Generators;
import com.ncoder.paradox.items.machines.Machines;
import com.ncoder.paradox.items.materials.Materials;
import com.ncoder.paradox.utils.Categories;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class ParadoxItems {

    public static void setup(@Nonnull Paradoxium plugin) {
        Materials.setup(plugin);
        Machines.setup(plugin);
        Generators.setup(plugin);
        Blocks.setup(plugin);
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
        new SlimefunItem(itemGroup, itemStack, recipeType, recipe, recipeOutput).register(Paradoxium.get());
    }

}
