package com.ncoder.paradoxium.items.blocks;

import com.google.common.base.Strings;
import com.ncoder.paradoxium.ParadoxAPI;
import com.ncoder.paradoxium.ParadoxConfig;
import com.ncoder.paradoxium.Paradoxium;
import com.ncoder.paradoxium.utils.Constants;
import com.ncoder.paradoxium.utils.Utils;
import com.ncoder.paradoxlib.common.Scheduler;
import com.ncoder.paradoxlib.machines.ParadoxInventoryBlock;
import com.ncoder.paradoxlib.utils.ConversionUtil;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.chat.ChatInput;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class WarpPlate extends ParadoxInventoryBlock {

    private final int[] BORDER = { 0, 1, 2, 3, 5, 6, 7, 8, 12, 14, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
    private final int[] COORDINATE_BORDER = { 9, 11 };
    private final int[] MATERIAL_BORDER = { 15, 17 };

    private final int NAME_SLOT = 4;
    private final int LINKED_SLOT = 13;
    private final int DESTINATION_SLOT = 10;
    private final int MATERIAL_SLOT = 16;

    public static final Material[] materials = {
            Material.WHITE_CARPET,
            Material.ORANGE_CARPET,
            Material.MAGENTA_CARPET,
            Material.LIGHT_BLUE_CARPET,
            Material.YELLOW_CARPET,
            Material.LIME_CARPET,
            Material.PINK_CARPET,
            Material.GRAY_CARPET,
            Material.LIGHT_GRAY_CARPET,
            Material.CYAN_CARPET,
            Material.PURPLE_CARPET,
            Material.BLUE_CARPET,
            Material.BROWN_CARPET,
            Material.GREEN_CARPET,
            Material.RED_CARPET,
            Material.BLACK_CARPET
    };

    private int RANGE = 100;

    private BlockMenuPreset preset;

    public WarpPlate(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput, int range) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
        RANGE = range;
    }

    private void detectConnector(@Nonnull Block b) {
        String uuid = BlockStorage.getLocationInfo(b.getLocation(), "owner");
        String loc = ConversionUtil.LOCATION.toString(b.getLocation());

        if (!BlockStorage.getLocationInfo(b.getLocation(), "connector").isEmpty()) {
            String connector = BlockStorage.getLocationInfo(b.getLocation(), "connector");

            List<String> plates = ParadoxConfig.WARPS.getStringList(uuid + ".warps." + connector);
            if (plates.contains(loc)) plates.remove(loc);

            ParadoxConfig.WARPS.setValue(uuid + ".warps." + connector, plates);
            ParadoxConfig.WARPS.save();

            BlockStorage.addBlockInfo(b.getLocation(), "connector", "");
        }

        List<String> connectors = ParadoxConfig.WARPS.getStringList(uuid + ".connectors");

        String closest = "";
        double closest_distance = 0;
        for (String connector : connectors) {
            Location l = ConversionUtil.LOCATION.toLocation(connector);

            if (!b.getLocation().getWorld().equals(l.getWorld())) continue;
            if (Math.abs(b.getLocation().getBlockX() - l.getBlockX()) > RANGE || Math.abs(b.getLocation().getBlockZ() - l.getBlockZ()) > RANGE) continue;

            double distance = Math.hypot(Math.abs(b.getLocation().getBlockX() - l.getBlockX()), Math.abs(b.getLocation().getBlockZ() - l.getBlockZ()));

            if ((closest.isEmpty() && closest_distance == 0) || closest_distance > distance) {
                closest = connector;
                closest_distance = distance;
            }
        }

        if (closest.isEmpty()) return;

        List<String> plates = ParadoxConfig.WARPS.getStringList(uuid + ".warps." + closest);
        if (!plates.contains(loc)) plates.add(loc);

        ParadoxConfig.WARPS.setValue(uuid + ".warps." + closest, plates);
        ParadoxConfig.WARPS.save();

        BlockStorage.addBlockInfo(b.getLocation(), "connector", closest);
    }

    private void changeMaterial(@Nonnull BlockMenu menu, @Nonnull Block b) {
        Scheduler.run(() -> {
            int val = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "material"));
            menu.replaceExistingItem(MATERIAL_SLOT, new CustomItemStack(materials[val], "&aMaterial", "", "&6&lLeft Click &7to next.", "&6&lRight Click &7to previous."));
            b.setType(materials[val]);
        });
    }

    @Override
    protected void setup(BlockMenuPreset preset) {
        for (int i : BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), Constants.EMPTY),
                    (p, slot, item, action) -> false);
        }
        for (int i : COORDINATE_BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), Constants.EMPTY),
                    (p, slot, item, action) -> false);
        }
        for (int i : MATERIAL_BORDER) {
            preset.addItem(i, new CustomItemStack(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), Constants.EMPTY),
                    (p, slot, item, action) -> false);
        }
        preset.addItem(LINKED_SLOT, new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&6Status&7: &4Unlinked", "", "&6Click &7to re-detect closest connectors"),
                (p, slot, item, action) -> false);

        this.preset = preset;
    }

    @Override
    protected int[] getInputSlots() { return new int[0]; }

    @Override
    protected int[] getOutputSlots() { return new int[0]; }

    @Override
    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        String uuid = BlockStorage.getLocationInfo(e.getBlock().getLocation(), "owner");
        String loc = ConversionUtil.LOCATION.toString(e.getBlock().getLocation());

        if (!BlockStorage.getLocationInfo(e.getBlock().getLocation(), "connector").isEmpty()) {
            String connector = BlockStorage.getLocationInfo(e.getBlock().getLocation(), "connector");

            List<String> plates = ParadoxConfig.WARPS.getStringList(uuid + ".warps." + connector);
            if (plates.contains(loc)) plates.remove(loc);

            ParadoxConfig.WARPS.setValue(uuid + ".warps." + connector, plates);
            ParadoxConfig.WARPS.save();
        }
    }

    @Override
    protected void onPlace(BlockPlaceEvent e, Block b) {
        BlockStorage.addBlockInfo(e.getBlock().getLocation(), "name", "");
        BlockStorage.addBlockInfo(e.getBlock().getLocation(), "material", "0");
        BlockStorage.addBlockInfo(e.getBlock().getLocation(), "owner", e.getPlayer().getUniqueId().toString());
        BlockStorage.addBlockInfo(e.getBlock().getLocation(), "connector", "");
        BlockStorage.addBlockInfo(e.getBlock().getLocation(), "destination", "");

        detectConnector(e.getBlock());
    }

    @Override
    protected void onNewInstance(BlockMenu menu, Block b) {
        menu.addMenuClickHandler(NAME_SLOT, (p, slot, item, action) -> {
            p.closeInventory();
            Utils.sendMessage(p, "Please enter plate name.");
            ChatInput.waitForPlayer(Paradoxium.instance(), p, msg -> {
                BlockStorage.addBlockInfo(b.getLocation(), "name", msg);
                onNewInstance(menu, b);
                menu.open(p);
            });
            return false;
        });
        menu.replaceExistingItem(NAME_SLOT, new CustomItemStack(Material.WHITE_STAINED_GLASS_PANE, "&6Name&7: " + (!BlockStorage.getLocationInfo(b.getLocation(), "name").equals("") ? BlockStorage.getLocationInfo(b.getLocation(), "name") : Constants.EMPTY)));

        menu.addMenuClickHandler(LINKED_SLOT, (p, slot, item, action) -> {
            detectConnector(b);
            onNewInstance(menu, b);
            return false;
        });
        if (!BlockStorage.getLocationInfo(b.getLocation(), "connector").isEmpty()) {
            menu.replaceExistingItem(LINKED_SLOT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&6Status&7: &aLinked", "", "&6Click &7to re-detect closest connectors"));
        } else {
            menu.replaceExistingItem(LINKED_SLOT, new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&6Status&7: &4Unlinked", "", "&6Click &7to re-detect closest connectors"));
        }

        menu.addMenuClickHandler(DESTINATION_SLOT, (p, slot, item, action) -> {
            ParadoxAPI.getWarpConnections().openPlateSelector(p, UUID.fromString(BlockStorage.getLocationInfo(b.getLocation(), "owner")), b, this.preset, menu);
            return false;
        });
        if (!BlockStorage.getLocationInfo(b.getLocation(), "destination").isEmpty()) {
            Location loc = ConversionUtil.LOCATION.toLocation(BlockStorage.getLocationInfo(b.getLocation(), "destination"));
            Material mat = materials[Integer.parseInt(BlockStorage.getLocationInfo(loc, "material"))];
            menu.replaceExistingItem(DESTINATION_SLOT, new CustomItemStack(mat, "&aDestination", "&bWorld&8: &7" + loc.getWorld().getName(), "&bCoordinates&8: &7" + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ(), "", "&6Click &7to change destination."));
        } else {
            menu.replaceExistingItem(DESTINATION_SLOT, new CustomItemStack(Material.BLACK_CARPET, "&aDestination", "&bWorld&8: &7None", "&bCoordinates&8: &7None"));
        }

        changeMaterial(menu, b);
        menu.addMenuClickHandler(MATERIAL_SLOT, (p, slot, item, action) -> {
            int val = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "material"));
            if (action.isRightClicked()) {
                if (val <= 0) val = materials.length - 1;
                else val = val - 1;
            } else {
                if (val >= materials.length - 1) val = 0;
                else val = val + 1;
            }
            BlockStorage.addBlockInfo(b.getLocation(), "material", String.valueOf(val));

            changeMaterial(menu, b);
            return false;
        });
    }
}
