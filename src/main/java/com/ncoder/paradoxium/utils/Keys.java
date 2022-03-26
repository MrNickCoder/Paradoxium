package com.ncoder.paradoxium.utils;

import com.ncoder.paradoxium.Paradoxium;
import org.bukkit.NamespacedKey;

public class Keys {

    /* Categories */
    public static final NamespacedKey PARENT_CATEGORY = Paradoxium.createKey("parent-category");
    public static final NamespacedKey SUB_CATEGORY_MATERIALS = Paradoxium.createKey("sub-category-materials");
    public static final NamespacedKey SUB_CATEGORY_BLOCKS = Paradoxium.createKey("sub-category-blocks");
    public static final NamespacedKey SUB_CATEGORY_GEARS = Paradoxium.createKey("sub-category-gears");
    public static final NamespacedKey SUB_CATEGORY_MACHINES = Paradoxium.createKey("sub-category-machines");
    public static final NamespacedKey SUB_CATEGORY_GENERATORS = Paradoxium.createKey("sub-category-generators");

    /* Recipe Types */
    public static final NamespacedKey GIFT_WRAPPER = Paradoxium.createKey("gift-wrapper");
    public static final NamespacedKey STRIPPING = Paradoxium.createKey("stripping");
    public static final NamespacedKey MINING = Paradoxium.createKey("mining");
    public static final NamespacedKey TILLING = Paradoxium.createKey("tilling");
    public static final NamespacedKey CUTTING = Paradoxium.createKey("cutting");
    public static final NamespacedKey FISHING = Paradoxium.createKey("fishing");

    /* Present */
    public static final NamespacedKey PRESENT_ID = Paradoxium.createKey("present-id");

    /* Shards */
    public static NamespacedKey SHARD_EFFECT_NAME(int n) { return Paradoxium.createKey("seffect-"+n+"-name"); }
    public static NamespacedKey SHARD_EFFECT_LVL(int n) { return Paradoxium.createKey("seffect-"+n+"-lvl"); }

    /* Switcher */
    public static final NamespacedKey SWITCHER_ID = Paradoxium.createKey("switcher-id");
    public static final NamespacedKey SWITCHER_SLOTS = Paradoxium.createKey("switcher-slots");

    /* Item Gears */
    public static final NamespacedKey SHARD_SLOTS = Paradoxium.createKey("shard-slots");

}
