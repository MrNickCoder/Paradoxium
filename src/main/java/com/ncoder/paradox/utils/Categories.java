package com.ncoder.paradox.utils;

import com.ncoder.paradox.Paradoxium;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public final class Categories {

    public static final NestedItemGroup parentGroup = new NestedItemGroup(Keys.PARENT_CATEGORY, new CustomItemStack(Material.LODESTONE, Strings.PARADOXIUM));
    public static final SubItemGroup materialsIG = new SubItemGroup(Keys.SUB_CATEGORY_MATERIALS, parentGroup, new CustomItemStack(Material.DIAMOND, Strings.MATERIALS));;
    public static final SubItemGroup blocksIG = new SubItemGroup(Keys.SUB_CATEGORY_BLOCKS, parentGroup, new CustomItemStack(Material.NETHERITE_BLOCK, Strings.BLOCKS));
    public static final SubItemGroup gearsIG = new SubItemGroup(Keys.SUB_CATEGORY_GEARS, parentGroup, new CustomItemStack(Material.LEATHER_CHESTPLATE, Strings.GEARS));
    public static final SubItemGroup machinesIG = new SubItemGroup(Keys.SUB_CATEGORY_MACHINES, parentGroup, new CustomItemStack(Material.LODESTONE, Strings.MACHINES));
    public static final SubItemGroup generatorsIG = new SubItemGroup(Keys.SUB_CATEGORY_GENERATORS, parentGroup, new CustomItemStack(Material.SMOOTH_STONE, Strings.GENERATORS));

}
