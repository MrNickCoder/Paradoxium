package com.ncoder.paradox.utils;

import org.apache.commons.lang.Validate;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum MaterialsList {

    LOG(new Material[] {
            Material.OAK_LOG,
            Material.BIRCH_LOG,
            Material.SPRUCE_LOG,
            Material.JUNGLE_LOG,
            Material.ACACIA_LOG,
            Material.DARK_OAK_LOG,
            Material.WARPED_HYPHAE,
            Material.CRIMSON_HYPHAE
    }),

    AXE(new Material[] {
            Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.GOLDEN_AXE,
            Material.IRON_AXE,
            Material.DIAMOND_AXE,
            Material.NETHERITE_AXE
    });

    private final List<Material> list;

    MaterialsList(Material[] materials) {
        Validate.noNullElements(materials, "The List cannot contain any null elements");

        list = Collections.unmodifiableList(Arrays.asList(materials));
    }

    public List<Material> asList() {
        return list;
    }

    public Material get(int index) {
        Validate.isTrue(index >= 0 && index < 16, "The index must be between 0 and 15 (inclusive).");

        return list.get(index);
    }

    public Material get(@Nonnull DyeColor color) {
        Validate.notNull(color, "Color cannot be null!");

        return get(color.ordinal());
    }

}
