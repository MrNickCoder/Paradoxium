package com.ncoder.paradox.listeners;

import com.ncoder.paradox.enchantments.ParadoxEnchantments;
import com.ncoder.paradox.events.ArmorEquipEvent;
import com.ncoder.paradox.utils.Utils;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class ShardGearListener implements Listener {
    /*
    ********************
    **     Armor's    **
    ********************
    */

    @EventHandler
    public void onEquipHelmet(ArmorEquipEvent e) {

    }

    @EventHandler
    public void onEquipChestplate(ArmorEquipEvent e) {

    }

    @EventHandler
    public void onEquipLeggings(ArmorEquipEvent e) {

    }

    @EventHandler
    public void onEquipBoots(ArmorEquipEvent e) {

    }

    /*
     ********************
     **    Weapon's    **
     ********************
     */
    @EventHandler
    public void onSwordAttack(EntityDamageByEntityEvent e) {

    }

    @EventHandler
    public void onAxeAttack(EntityDamageByEntityEvent e) {

    }

    @EventHandler
    public void onBowAttack(EntityDamageByEntityEvent e) {

    }

    /*
     ********************
     **     Tool's     **
     ********************
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(ParadoxEnchantments.TELEPATHY)) {
            if (Utils.isCreative(e.getPlayer()) || Utils.isSpectator(e.getPlayer())) return;
            if (e.getPlayer().getInventory().firstEmpty() == -1) return;
            if (e.getBlock().getState() instanceof Container) return;
            e.setDropItems(false);
            Player p = e.getPlayer();
            Block b = e.getBlock();

            Collection<ItemStack> drops = b.getDrops(p.getInventory().getItemInMainHand());
            if (drops.isEmpty()) return;
            p.getInventory().addItem(drops.iterator().next());
        }
    }

    @EventHandler
    public void onFishing(PlayerFishEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getType().name().equals("FISHING_ROD")) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
    }

    @EventHandler
    public void onMining(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getType().name().endsWith("_PICKAXE")) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
    }

    @EventHandler
    public void onDigging(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getType().name().endsWith("_SHOVEL")) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;

    }

    @EventHandler
    public void onCutting(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getType().name().endsWith("_AXE")) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
    }

    @EventHandler
    public void onTilling(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getType().name().endsWith("_HOE")) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
    }



}
