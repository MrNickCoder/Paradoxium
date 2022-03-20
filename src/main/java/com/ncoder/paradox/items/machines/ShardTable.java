package com.ncoder.paradox.items.machines;

import com.ncoder.paradox.items.materials.Shards;
import com.ncoder.paradox.utils.Keys;
import com.ncoder.paradox.utils.Scheduler;
import com.ncoder.paradox.utils.Strings;
import com.ncoder.paradox.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.List;

public class ShardTable extends SlimefunItem {

    private final int[] BORDER = { 0, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 51, 52, 53};
    private final int[] BORDER_GEAR = { 48, 50 };

    private final int[] SHARD_SLOTS = {
            1, 10, 19, 28, 37,
            2, 11, 20, 29, 38,
            3, 12, 21, 30, 39,
            4, 13, 22, 31, 40,
            5, 14, 23, 32, 41,
            6, 15, 24, 33, 42,
            7, 16, 25, 34, 43
    };

    private final int GEAR_SLOT = 49;

    public ShardTable(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        new BlockMenuPreset(getId(), item.getItemMeta().getDisplayName()) {

            @Override
            public void init() { constructMenu(this); }

            @Override
            public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
                refreshShards(menu, b);

                menu.addPlayerInventoryClickHandler((p, slot, item, action) -> {
                    if (action.isShiftClicked()) return false;
                    return true;
                });

                menu.addMenuClickHandler(GEAR_SLOT, (p, slot, item, action) -> {
                    String cursorMaterial = p.getItemOnCursor().getType().toString();
                    if (!cursorMaterial.equals("AIR") && !Utils.isGear(p.getItemOnCursor())) return false;
                    refreshShards(menu, b);
                    return true;
                });

            }

            @Override
            public boolean canOpen(@Nonnull Block b, @Nonnull Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) { return new int[0]; }
        };

        addItemHandler(
                new BlockBreakHandler(false, true) {
                    @Override
                    public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                        Block b = e.getBlock();
                        BlockMenu inv = BlockStorage.getInventory(b);

                        if (inv != null) {
                            if (inv.getItemInSlot(GEAR_SLOT) != null && !inv.getItemInSlot(GEAR_SLOT).getType().isAir()) {
                                inv.dropItems(b.getLocation(), GEAR_SLOT);
                            }
                        }
                    }
                }
        );
    }

    private void bindShards(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        for (int i = 0; i < SHARD_SLOTS.length; i++) {
            int SLOT = SHARD_SLOTS[i];
        }

        itemStack.setItemMeta(meta);
    }

    private void unbindShards(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        for (int i = 0; i < SHARD_SLOTS.length; i++) {
            int SLOT = SHARD_SLOTS[i];
        }

        itemStack.setItemMeta(meta);
    }

    private int getGearShardSlots(ItemStack itemStack) {
        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();

        if (pdc.has(Keys.SHARD_SLOTS, PersistentDataType.INTEGER)) {
            return pdc.get(Keys.SHARD_SLOTS, PersistentDataType.INTEGER);
        }
        return 0;
    }

    private void refreshShards(@Nonnull BlockMenu menu, @Nonnull Block b) {
        Scheduler.run(() -> {
            int counter = 0;
            for (int SLOT : SHARD_SLOTS) {
                if (menu.getItemInSlot(GEAR_SLOT) != null && !menu.getItemInSlot(GEAR_SLOT).getType().isAir()) {
                    if (getGearShardSlots(menu.getItemInSlot(GEAR_SLOT)) != 0 && counter < getGearShardSlots(menu.getItemInSlot(GEAR_SLOT))) {
                        menu.replaceExistingItem(SLOT, new CustomItemStack(Material.AIR));
                        menu.addMenuClickHandler(SLOT, (p, slot, item, action) -> {
                            if (!p.getItemOnCursor().getType().isAir() && !Shards.isShard(p.getItemOnCursor()))
                                return false;
                            if (p.getItemOnCursor().getAmount() > 1) return false;
                            if (menu.getItemInSlot(SLOT) != null && !menu.getItemInSlot(SLOT).getType().isAir()) {
                                if (SlimefunUtils.isItemSimilar(p.getItemOnCursor(), menu.getItemInSlot(SLOT), true))
                                    return false;
                            }
                            return true;
                        });
                        counter++;
                        continue;
                    }
                }
                menu.replaceExistingItem(SLOT, new CustomItemStack(Material.BARRIER, Strings.EMPTY));
                menu.addMenuClickHandler(SLOT, (p, slot, item, action) -> false);
                counter++;
            }
        });
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), Strings.EMPTY),
                    (p, slot, item, action) -> false);
        }
        for (int i : BORDER_GEAR) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), Strings.EMPTY),
                    (p, slot, item, action) -> false);
        }
    }

}
