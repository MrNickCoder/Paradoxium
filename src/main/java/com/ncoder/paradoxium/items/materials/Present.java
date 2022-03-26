package com.ncoder.paradoxium.items.materials;

import com.ncoder.paradoxium.ParadoxConfig;
import com.ncoder.paradoxium.utils.Constants;
import com.ncoder.paradoxium.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.FireworkUtils;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Present extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {

    @ParametersAreNonnullByDefault
    public Present(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.hidden = true;
    }

    @Override
    public @Nonnull
    ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            e.getClickedBlock().ifPresent(block -> {
                if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;

                ItemStack item = e.getItem();
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer pdc = meta.getPersistentDataContainer();
                if (pdc.get(Keys.PRESENT_ID, PersistentDataType.STRING) != null) {
                    String id = pdc.get(Keys.PRESENT_ID, PersistentDataType.STRING);

                    Block b = block.getRelative(e.getClickedFace());

                    Inventory inv = ParadoxConfig.PRESENTS.getInventory(id, Constants.PRESENTS);
                    inv.forEach(itemStack -> {
                        if (itemStack != null) {
                            b.getLocation().getWorld().dropItemNaturally(b.getLocation(), itemStack);
                        }
                    });

                    ParadoxConfig.PRESENTS.setValue(id, null);
                    ParadoxConfig.PRESENTS.save();
                }

                ItemUtils.consumeItem(e.getItem(), false);

                FireworkUtils.launchRandom(e.getPlayer(), 3);
            });
        };
    }

}
