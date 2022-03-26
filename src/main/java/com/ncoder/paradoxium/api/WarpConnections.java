package com.ncoder.paradoxium.api;

import com.ncoder.paradoxium.ParadoxConfig;
import com.ncoder.paradoxium.items.blocks.WarpPlate;
import com.ncoder.paradoxium.utils.Constants;
import com.ncoder.paradoxlib.utils.ConversionUtil;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class WarpConnections {

    private final int[] BORDER = { 0, 1, 2, 3, 5, 6, 7, 8, 45, 47, 48, 49, 50, 51, 53 };
    private final int[] INVENTORY = { 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44 };

    private final int CANCEL = 4;
    private final int PREVIOUS = 46;
    private final int NEXT = 52;

    private int page = 1;

    public void openPlateSelector(Player p, UUID uuid, Block b, BlockMenuPreset preset, BlockMenu bm) {
        ChestMenu menu = new ChestMenu(ChatColor.GOLD + "Select Plates");

        for (int i : BORDER) {
            menu.addItem(i, new CustomItemStack(Material.GRAY_STAINED_GLASS_PANE, Constants.EMPTY), ChestMenuUtils.getEmptyClickHandler());
        }

        menu.addItem(CANCEL, new CustomItemStack(Material.BARRIER, "&4Cancel"));
        menu.addMenuClickHandler(CANCEL, (pl, slot, item, action) -> {
            pl.closeInventory();
            preset.newInstance(bm, b);
            bm.open(pl);
            return false;
        });

        int amount = computePages(uuid, b);

        createPage(uuid, menu, b, preset, bm);

        if (amount == 1) {
            menu.addItem(PREVIOUS, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "&f◄ &6Previous", "("+page+"/"+amount+")"), ChestMenuUtils.getEmptyClickHandler());
            menu.addItem(NEXT, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "&6Next &f►", "("+page+"/"+amount+")"), ChestMenuUtils.getEmptyClickHandler());
        } else {
            if (page <= 1) menu.addItem(PREVIOUS, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "&f◄ &6Previous", "("+page+"/"+amount+")"), ChestMenuUtils.getEmptyClickHandler());
            else {
                menu.addItem(PREVIOUS, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&f◄ &6Previous", "("+page+"/"+amount+")"));
                menu.addMenuClickHandler(PREVIOUS, (pl, slot, item, action) -> {
                    page -= 1;
                    createPage(uuid, menu, b, preset, bm);
                    return false;
                });
            }

            if (page >= amount) menu.addItem(NEXT, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "&6Next &f►", "("+page+"/"+amount+")"), ChestMenuUtils.getEmptyClickHandler());
            else {
                menu.addItem(NEXT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&6Next &f►", "("+page+"/"+amount+")"));
                menu.addMenuClickHandler(NEXT, (pl, slot, item, action) -> {
                    page += 1;
                    createPage(uuid, menu, b, preset, bm);
                    return false;
                });
            }
        }

        menu.open(p);
    }

    public void createPage(UUID uuid, ChestMenu menu, Block b, BlockMenuPreset preset, BlockMenu bm) {
        String l = BlockStorage.getLocationInfo(b.getLocation(), "connector");

        if (ParadoxConfig.WARPS.contains(uuid + ".warps." + l)) {
            List<String> plates = ParadoxConfig.WARPS.getStringList(uuid + ".warps." + l);
            int counter = (page - 1) * INVENTORY.length;
            for (int i : INVENTORY) {
                try {
                    final String plate = plates.get(counter);
                    Location loc = ConversionUtil.LOCATION.toLocation(plate);

                    String name = BlockStorage.getLocationInfo(loc, "name");
                    Material material = WarpPlate.materials[Integer.parseInt(BlockStorage.getLocationInfo(loc, "material"))];

                    menu.addItem(i, new CustomItemStack(material, !name.equals("") ? name : Constants.EMPTY, "&bWorld&8: &7" + loc.getWorld().getName(), "&bCoordinates&8: &7" + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ()));
                    menu.addMenuClickHandler(i, (pl, s, item, action) -> {
                        pl.closeInventory();
                        BlockStorage.addBlockInfo(b, "destination", plate);
                        preset.newInstance(bm, b);
                        bm.open(pl);
                        return false;
                    });
                } catch (IndexOutOfBoundsException e) {
                    menu.addMenuClickHandler(i, ChestMenuUtils.getEmptyClickHandler());
                }
                counter++;
            }
        } else {
            for (int i : INVENTORY) {
                menu.addMenuClickHandler(i, ChestMenuUtils.getEmptyClickHandler());
            }
        }
    }

    public int computePages(UUID uuid, Block b) {
        String l = BlockStorage.getLocationInfo(b.getLocation(), "connector");
        int amount = 1;

        if (ParadoxConfig.WARPS.contains(uuid + ".warps." + l)) {
            List<String> plates = ParadoxConfig.WARPS.getStringList(uuid + ".warps." + l);
            amount += plates.size() / INVENTORY.length;
        }

        return amount;
    }

}
