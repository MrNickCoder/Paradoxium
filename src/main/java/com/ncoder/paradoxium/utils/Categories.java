package com.ncoder.paradoxium.utils;

import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;

public class Categories {

    public static final NestedItemGroup parentGroup = new NestedItemGroup(Keys.PARENT_CATEGORY, new CustomItemStack(Material.LODESTONE, Constants.PARADOXIUM));
    public static final SubItemGroup materialsIG = new SubItemGroup(Keys.SUB_CATEGORY_MATERIALS, parentGroup, new CustomItemStack(Material.DIAMOND, Constants.MATERIALS));;
    public static final SubItemGroup blocksIG = new SubItemGroup(Keys.SUB_CATEGORY_BLOCKS, parentGroup, new CustomItemStack(Material.NETHERITE_BLOCK, Constants.BLOCKS));
    public static final SubItemGroup gearsIG = new SubItemGroup(Keys.SUB_CATEGORY_GEARS, parentGroup, new CustomItemStack(Material.LEATHER_CHESTPLATE, Constants.GEARS));
    public static final SubItemGroup machinesIG = new SubItemGroup(Keys.SUB_CATEGORY_MACHINES, parentGroup, new CustomItemStack(Material.LODESTONE, Constants.MACHINES));
    public static final SubItemGroup generatorsIG = new SubItemGroup(Keys.SUB_CATEGORY_GENERATORS, parentGroup, new CustomItemStack(Material.SMOOTH_STONE, Constants.GENERATORS));

}
