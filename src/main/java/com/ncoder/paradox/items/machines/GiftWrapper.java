package com.ncoder.paradox.items.machines;

import com.ncoder.paradox.ParadoxConfig;
import com.ncoder.paradox.Paradoxium;
import com.ncoder.paradox.items.materials.Materials;
import com.ncoder.paradox.utils.Keys;
import com.ncoder.paradox.utils.Scheduler;
import com.ncoder.paradox.enums.PresentType;
import com.ncoder.paradox.utils.Strings;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class GiftWrapper extends SlimefunItem implements RecipeDisplayItem {

    private final int[] BORDER_ITEMS = { 0, 1, 2, 3, 4, 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 46, 47, 48, 49, 50 };
    private final int[] BORDER_WRAPPER = { 6, 7, 8, 15, 17, 24, 25, 26 };
    private final int[] BORDER_OUTPUT = { 33, 34, 35, 42, 44, 51, 52, 53 };

    private final int[] ITEM_SLOTS = {
            10, 11, 12, 13,
            19, 20, 21, 22,
            28, 29, 30, 31,
            37, 38, 39, 40
    };

    private final int WRAPPER_SLOT = 16;
    private final int OUTPUT_SLOT = 43;

    public GiftWrapper(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        new BlockMenuPreset(getId(), item.getItemMeta().getDisplayName()) {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
                refreshPresent(menu, b);

                menu.addPlayerInventoryClickHandler((p, slot, item, action) -> {
                    if (action.isShiftClicked()) {
                        refreshPresent(menu, b);
                    }
                    return true;
                });

                menu.addMenuClickHandler(OUTPUT_SLOT, (p, slot, item, action) -> {
                    if (menu.getItemInSlot(WRAPPER_SLOT) == null) return false;
                    if (menu.getItemInSlot(WRAPPER_SLOT).getType().isAir()) return false;
                    if (menu.getItemInSlot(OUTPUT_SLOT).getType() == Material.BARRIER) return false;
                    if (menu.getItemInSlot(OUTPUT_SLOT) == null) return false;
                    if (menu.getItemInSlot(OUTPUT_SLOT).getType().isAir()) return false;
                    if (menu.getItemInSlot(WRAPPER_SLOT).getAmount() < PresentType.getByMaterial(menu.getItemInSlot(WRAPPER_SLOT).getType()).getAmount()) return false;

                    int amount = menu.getItemInSlot(WRAPPER_SLOT).getAmount() - PresentType.getByMaterial(menu.getItemInSlot(WRAPPER_SLOT).getType()).getAmount();
                    if (amount <= 0) {
                        menu.getItemInSlot(WRAPPER_SLOT).setType(Material.AIR);
                    } else {
                        menu.getItemInSlot(WRAPPER_SLOT).setAmount(amount);
                    }

                    wrap(b, menu, p);
                    return false;
                });

                menu.addMenuClickHandler(WRAPPER_SLOT, (p, slot, item, action) -> {
                    refreshPresent(menu, b);
                    return true;
                });

                for (int i : ITEM_SLOTS) {
                    menu.addMenuClickHandler(i, (p, slot, item, action) -> {
                        refreshPresent(menu, b);
                        return true;
                    });
                }

            }

            @Override
            public boolean canOpen(@Nonnull Block b, @Nonnull Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }
        };

        addItemHandler(
                new BlockBreakHandler(false, true) {
                    @Override
                    public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                        Block b = e.getBlock();
                        BlockMenu inv = BlockStorage.getInventory(b);

                        if (inv != null) {
                            inv.dropItems(b.getLocation(), ITEM_SLOTS);
                            inv.dropItems(b.getLocation(), WRAPPER_SLOT);
                        }
                    }
                }
        );
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(PresentType.values().length * 2);

        for (PresentType pEnum : PresentType.values()) {
            /* Input */
            displayRecipes.add(new ItemStack(Material.valueOf(pEnum.toString()), pEnum.getAmount()));

            /* Output */
            displayRecipes.add(Materials.PRESENT(pEnum));
        }

        return displayRecipes;
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : BORDER_ITEMS) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), Strings.EMPTY),
                    (p, slot, item, action) -> false);
        }
        for (int i : BORDER_WRAPPER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), Strings.EMPTY),
                    (p, slot, item, action) -> false);
        }
        for (int i : BORDER_OUTPUT) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), Strings.EMPTY),
                    (p, slot, item, action) -> false);
        }
    }

    private void refreshPresent(@Nonnull BlockMenu menu, @Nonnull Block b) {
        Scheduler.run(() -> {
            if (menu.getItemInSlot(WRAPPER_SLOT) != null && !menu.getItemInSlot(WRAPPER_SLOT).getType().isAir()) {
                if (SlimefunItem.getByItem(menu.getItemInSlot(WRAPPER_SLOT)) == null) {
                    if (PresentType.getByMaterial(menu.getItemInSlot(WRAPPER_SLOT).getType()) != null) {
                        PresentType pEnum = PresentType.getByMaterial(menu.getItemInSlot(WRAPPER_SLOT).getType());
                        if (menu.getItemInSlot(WRAPPER_SLOT).getAmount() >= pEnum.getAmount()) {
                            menu.replaceExistingItem(OUTPUT_SLOT, new CustomItemStack(SlimefunUtils.getCustomHead(pEnum.getTexture()), "&e> Click to wrap present."));
                            return;
                        }
                    }
                }
            }
            menu.replaceExistingItem(OUTPUT_SLOT, new CustomItemStack(Material.BARRIER, Strings.NONE));
        });

    }

    protected void wrap(Block b, BlockMenu menu, Player p) {
        ItemStack present = Materials.PRESENT(PresentType.getByMaterial(menu.getItemInSlot(WRAPPER_SLOT).getType()));
        ItemMeta meta = present.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        String id = UUID.randomUUID().toString();
        pdc.set(Keys.PRESENT_ID, PersistentDataType.STRING, id);

        present.setItemMeta(meta);

        Inventory inventory = Bukkit.createInventory(p, 18, Strings.PRESENTS);
        for (int SLOTS : ITEM_SLOTS) {
            if (menu.getItemInSlot(SLOTS) == null) continue;
            if (menu.getItemInSlot(SLOTS).getType().isAir()) continue;

            inventory.addItem(menu.getItemInSlot(SLOTS));

            menu.replaceExistingItem(SLOTS, new ItemStack(Material.AIR));
        }

        ParadoxConfig.PRESENTS.setValue(id, inventory);
        ParadoxConfig.PRESENTS.save();

        p.getInventory().addItem(present);
    }

}
