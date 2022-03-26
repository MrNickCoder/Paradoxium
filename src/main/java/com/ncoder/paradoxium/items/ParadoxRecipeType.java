package com.ncoder.paradoxium.items;

import com.ncoder.paradoxium.items.machines.Machines;
import com.ncoder.paradoxium.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;

public class ParadoxRecipeType {

    public static RecipeType GIFT_WRAPPER = new RecipeType(Keys.GIFT_WRAPPER, Machines.GIFT_WRAPPER, "&a&oCraft it using the Gift Wrapper");

    public static RecipeType STRIPPING = new RecipeType(Keys.STRIPPING, new CustomItemStack(Material.DIAMOND_AXE, "Stripping", "&a&oStrip a log to get the item."));
    public static RecipeType MINING = new RecipeType(Keys.MINING, new CustomItemStack(Material.DIAMOND_PICKAXE, "Mining", "&a&oMine the specified block to get the item."));
    public static RecipeType TILLING = new RecipeType(Keys.TILLING, new CustomItemStack(Material.DIAMOND_HOE, "Tilling", "&a&oTill the specified block to get the item."));
    public static RecipeType CUTTING = new RecipeType(Keys.CUTTING, new CustomItemStack(Material.DIAMOND_AXE, "Cutting", "&a&oCut the specified block to get the item."));
    public static RecipeType FISHING = new RecipeType(Keys.FISHING, new CustomItemStack(Material.FISHING_ROD, "Fishing", "&a&oFish to get the item."));

}
