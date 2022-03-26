package com.ncoder.paradoxium.items.multiblock;

import com.ncoder.paradoxium.ParadoxConfig;
import com.ncoder.paradoxium.enums.PresentType;
import com.ncoder.paradoxium.items.materials.Materials;
import com.ncoder.paradoxium.utils.Constants;
import com.ncoder.paradoxium.utils.Keys;
import com.ncoder.paradoxlib.common.Scheduler;
import com.ncoder.paradoxlib.machines.ParadoxInventoryBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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

public class GiftWrapper extends ParadoxInventoryBlock implements RecipeDisplayItem {

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
    }

    private void refreshPresent(@Nonnull BlockMenu menu, @Nonnull Block b) {
        Scheduler.run(() -> {
            if (menu.getItemInSlot(WRAPPER_SLOT) != null && !menu.getItemInSlot(WRAPPER_SLOT).getType().isAir()) {
                if (SlimefunItem.getByItem(menu.getItemInSlot(WRAPPER_SLOT)) == null) {
                    if (PresentType.getByMaterial(menu.getItemInSlot(WRAPPER_SLOT).getType()) != null) {
                        PresentType type = PresentType.getByMaterial(menu.getItemInSlot(WRAPPER_SLOT).getType());
                        if (menu.getItemInSlot(WRAPPER_SLOT).getAmount() >= type.getAmount()) {
                            menu.replaceExistingItem(OUTPUT_SLOT, new CustomItemStack(SlimefunUtils.getCustomHead(type.getTexture().getTexture()), "&e> Click to wrap present."));
                            return;
                        }
                    }
                }
            }
            menu.replaceExistingItem(OUTPUT_SLOT, new CustomItemStack(Material.BARRIER, Constants.NONE));
        });

    }

    protected void wrap(Block b, BlockMenu menu, Player p) {
        ItemStack present = Materials.PRESENT(PresentType.getByMaterial(menu.getItemInSlot(WRAPPER_SLOT).getType()));
        ItemMeta meta = present.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        String id = UUID.randomUUID().toString();
        pdc.set(Keys.PRESENT_ID, PersistentDataType.STRING, id);

        present.setItemMeta(meta);

        Inventory inventory = Bukkit.createInventory(p, 18, Constants.PRESENTS);
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

    @Override
    protected void setup(BlockMenuPreset preset) {
        for (int i : BORDER_ITEMS) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), Constants.EMPTY),
                    (p, slot, item, action) -> false);
        }
        for (int i : BORDER_WRAPPER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), Constants.EMPTY),
                    (p, slot, item, action) -> false);
        }
        for (int i : BORDER_OUTPUT) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), Constants.EMPTY),
                    (p, slot, item, action) -> false);
        }
    }

    @Override
    protected int[] getInputSlots() { return new int[0]; }

    @Override
    protected int[] getOutputSlots() { return new int[0]; }

    @Override
    protected void onNewInstance(BlockMenu menu, Block b) {
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
    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        Location l = menu.getLocation();
        menu.dropItems(l, ITEM_SLOTS);
        menu.dropItems(l, WRAPPER_SLOT);
    }

    @Nonnull
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
}
