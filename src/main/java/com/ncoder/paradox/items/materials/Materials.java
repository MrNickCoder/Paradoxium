package com.ncoder.paradox.items.materials;

import com.ncoder.paradox.Paradoxium;
import com.ncoder.paradox.enums.PresentType;
import com.ncoder.paradox.items.ParadoxRecipeType;
import com.ncoder.paradox.utils.Categories;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Materials {

    public static final SlimefunItemStack BARK = new SlimefunItemStack(
            "BARK",
            Material.PAPER,
            "&fBark",
            "",
            "&6&lRight Click &7a stripped log to turn it back to normal log."
    );

    public static SlimefunItemStack PRESENT(PresentType pEnum) {
        return new SlimefunItemStack(
                "PRESENT_" + pEnum.toString(),
                pEnum.getTexture(),
                "&6Present &7(&b"+pEnum.getName()+"&7)",
                "",
                "&6&lRight Click &7to open Present"
        );
    }

    public static void setup(Paradoxium plugin) {
        new Bark(
                Categories.materialsIG, BARK, ParadoxRecipeType.STRIPPING,
                new ItemStack[] {
                        null, null, null,
                        null, new ItemStack(Material.STRIPPED_OAK_LOG), null,
                        null, null, null
                }
        ).register(plugin);

        /* Shards */
        new Shards(plugin);

        /* Presents */
        for (PresentType pEnum : PresentType.values()) {
            SlimefunItemStack item = PRESENT(pEnum);
            new Present(
                    Categories.blocksIG,
                    item,
                    ParadoxRecipeType.GIFT_WRAPPER,
                    new ItemStack[] {
                            null, null, null,
                            null, null, null,
                            null, null, null
                    }
            ).register(plugin);
        }
    }

}
