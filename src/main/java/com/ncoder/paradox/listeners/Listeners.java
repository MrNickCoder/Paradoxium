package com.ncoder.paradox.listeners;

import com.ncoder.paradox.ParadoxConfig;
import com.ncoder.paradox.utils.Utils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.List;

public class Listeners implements Listener {

    @EventHandler
    public void onWarp(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            Player p = e.getPlayer();
            String uuid = p.getUniqueId().toString();
            Block b = p.getLocation().getBlock();

            if (!BlockStorage.hasBlockInfo(b.getLocation())) return;

            if (BlockStorage.checkID(b.getLocation()).startsWith("WARP_PLATE")) {
                if (!BlockStorage.getLocationInfo(b.getLocation(), "owner").equals(uuid)) return;
                if (BlockStorage.getLocationInfo(b.getLocation(), "connector").equals("")) return;
                if (BlockStorage.getLocationInfo(b.getLocation(), "destination").equals("")) return;

                List<String> connectors = ParadoxConfig.WARPS.getStringList(uuid + ".connectors");
                if (!connectors.contains(BlockStorage.getLocationInfo(b.getLocation(), "connector"))) return;

                if (!ParadoxConfig.WARPS.contains(uuid + ".warps." + BlockStorage.getLocationInfo(b.getLocation(), "connector"))) return;

                List<String> plates = ParadoxConfig.WARPS.getStringList(uuid + ".warps." + BlockStorage.getLocationInfo(b.getLocation(), "connector"));
                if (!plates.contains(BlockStorage.getLocationInfo(b.getLocation(), "destination"))) return;

                Location l = Utils.StringLocation(BlockStorage.getLocationInfo(b.getLocation(), "destination"));
                p.teleport(l.add(0.5, 0.75, 0.5));
            }
        }
    }

}
