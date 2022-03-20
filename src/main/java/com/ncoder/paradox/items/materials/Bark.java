package com.ncoder.paradox.items.materials;

import com.ncoder.paradox.listeners.Events;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Bark extends SimpleSlimefunItem<ItemUseHandler> implements Listener {

    public Bark(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        Events.registerListener(this);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!e.getMaterial().toString().contains("_AXE")) return;
        if (e.getClickedBlock().getType().toString().contains("STRIPPED_")
                && (e.getClickedBlock().getType().toString().contains("_LOG")
                || e.getClickedBlock().getType().toString().contains("_WOOD")
                || e.getClickedBlock().getType().toString().contains("_STEM")
                || e.getClickedBlock().getType().toString().contains("_HYPHAE"))) return;

        final Block b = e.getClickedBlock();
        b.getLocation().getWorld().dropItemNaturally(b.getLocation(), Materials.BARK);
    }

    @Override
    public @Nonnull ItemUseHandler getItemHandler() {
        return e -> {
            e.getClickedBlock().ifPresent(block -> {
                final ItemStack item = e.getItem();
                final Material material = block.getType();
                if (!material.toString().contains("STRIPPED_")
                        && (!material.toString().contains("_LOG")
                        || !material.toString().contains("_WOOD")
                        || !material.toString().contains("_STEM")
                        || !material.toString().contains("_HYPHAE"))) return;

                item.setAmount(item.getAmount() - 1);

                block.setType(Material.valueOf(material.toString().substring(9)));
            });
        };
    }

}
