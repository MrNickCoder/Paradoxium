package com.ncoder.paradox.items.machines;

import com.ncoder.paradox.ParadoxConfig;
import com.ncoder.paradox.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class WarpConnector extends SlimefunItem {
    public WarpConnector(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(
                new BlockPlaceHandler(false) {
                    @Override
                    public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                        final Player player = e.getPlayer();
                        final String uuid = player.getUniqueId().toString();

                        List<String> connectors = ParadoxConfig.WARPS.getStringList(uuid + ".connectors");
                        final String location = Utils.LocationString(e.getBlock().getLocation());

                        if (!connectors.contains(location)) connectors.add(location);

                        ParadoxConfig.WARPS.setValue(uuid + ".connectors", connectors);
                        ParadoxConfig.WARPS.setValue(uuid + ".warps." + location, new ArrayList<>());
                        ParadoxConfig.WARPS.save();

                        BlockStorage.addBlockInfo(e.getBlock().getLocation(), "owner", uuid);
                    }
                },
                new BlockBreakHandler(false, true) {
                    @Override
                    public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                        final String uuid = BlockStorage.getLocationInfo(e.getBlock().getLocation(), "owner");

                        List<String> connectors = ParadoxConfig.WARPS.getStringList(uuid + ".connectors");
                        final String location = Utils.LocationString(e.getBlock().getLocation());

                        if (connectors.contains(location)) connectors.remove(location);

                        if (ParadoxConfig.WARPS.contains(uuid + ".warps." + location)) {
                            List<String> plates = ParadoxConfig.WARPS.getStringList(uuid + ".warps." + location);
                            for (String plate : plates) {
                                Location l = Utils.StringLocation(plate);
                                BlockStorage.addBlockInfo(l, "connector", "");
                            }

                            ParadoxConfig.WARPS.setValue(uuid + ".warps." + location, null);
                        }

                        ParadoxConfig.WARPS.setValue(uuid + ".connectors", connectors);
                        ParadoxConfig.WARPS.save();
                    }
                }
        );
    }
}
