package com.ncoder.paradox.listeners;

import com.ncoder.paradox.enums.ArmorType;
import com.ncoder.paradox.events.ArmorEquipEvent;
import com.ncoder.paradox.events.ArmorEquipEvent.EquipMethod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;

public class DispenserArmorListener implements Listener {

    @EventHandler
    public void onDispenseArmor(BlockDispenseArmorEvent e) {
        ArmorType type = ArmorType.matchType(e.getItem());
        if (type != null) {
            if (e.getTargetEntity() instanceof Player) {
                Player p = (Player) e.getTargetEntity();
                ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(p, EquipMethod.DISPENSER, type, null, e.getItem());
                Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
                if (armorEquipEvent.isCancelled()) e.setCancelled(true);
            }
        }
    }
}
