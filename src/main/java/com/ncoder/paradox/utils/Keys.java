package com.ncoder.paradox.utils;

import com.ncoder.paradox.Paradoxium;
import org.bukkit.NamespacedKey;

public final class Keys {

    /* Categories */
    public static final NamespacedKey PARENT_CATEGORY = new NamespacedKey(Paradoxium.get(), "parent-category");
    public static final NamespacedKey SUB_CATEGORY_MATERIALS = new NamespacedKey(Paradoxium.get(), "sub-category-materials");
    public static final NamespacedKey SUB_CATEGORY_BLOCKS = new NamespacedKey(Paradoxium.get(), "sub-category-blocks");
    public static final NamespacedKey SUB_CATEGORY_GEARS = new NamespacedKey(Paradoxium.get(), "sub-category-gears");
    public static final NamespacedKey SUB_CATEGORY_MACHINES = new NamespacedKey(Paradoxium.get(), "sub-category-machines");
    public static final NamespacedKey SUB_CATEGORY_GENERATORS = new NamespacedKey(Paradoxium.get(), "sub-category-generators");

    /* Recipe Types */
    public static final NamespacedKey GIFT_WRAPPER = new NamespacedKey(Paradoxium.get(), "gift-wrapper");
    public static final NamespacedKey STRIPPING = new NamespacedKey(Paradoxium.get(), "stripping");
    public static final NamespacedKey MINING = new NamespacedKey(Paradoxium.get(), "mining");
    public static final NamespacedKey TILLING = new NamespacedKey(Paradoxium.get(), "tilling");
    public static final NamespacedKey CUTTING = new NamespacedKey(Paradoxium.get(), "cutting");
    public static final NamespacedKey FISHING = new NamespacedKey(Paradoxium.get(), "fishing");

    /* Present */
    public static final NamespacedKey PRESENT_ID = new NamespacedKey(Paradoxium.get(), "present-id");

    /* Shards */
    public static NamespacedKey SHARD_EFFECT_NAME(int n) { return new NamespacedKey(Paradoxium.get(), "seffect-"+n+"-name"); }
    public static NamespacedKey SHARD_EFFECT_LVL(int n) { return new NamespacedKey(Paradoxium.get(), "seffect-"+n+"-lvl"); }

    /* Switcher */
    public static final NamespacedKey SWITCHER_ID = new NamespacedKey(Paradoxium.get(), "switcher-id");
    public static final NamespacedKey SWITCHER_SLOTS = new NamespacedKey(Paradoxium.get(), "switcher-slots");

    /* Item Gears */
    public static final NamespacedKey SHARD_SLOTS = new NamespacedKey(Paradoxium.get(), "shard-slots");

}
