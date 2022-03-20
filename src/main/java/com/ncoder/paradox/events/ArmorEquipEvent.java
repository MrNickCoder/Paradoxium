package com.ncoder.paradox.events;

import com.ncoder.paradox.enums.ArmorType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public final class ArmorEquipEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private final EquipMethod equipType;
    private final ArmorType type;
    private ItemStack oldArmorPiece, newArmorPiece;

    public ArmorEquipEvent(final Player player, final EquipMethod equipType, final ArmorType type, final ItemStack oldArmorPiece, final ItemStack newArmorPiece) {
        super(player);
        this.equipType = equipType;
        this.type = type;
        this.oldArmorPiece = oldArmorPiece;
        this.newArmorPiece = newArmorPiece;
    }

    public static HandlerList getHandlerList() { return handlers; }

    @Override
    public final HandlerList getHandlers() { return handlers; }

    public final void setCancelled(final boolean cancel) { this.cancel = cancel; }

    public final boolean isCancelled() { return cancel; }

    public final ArmorType getType() { return type; }

    public final ItemStack getOldArmorPiece() { return  oldArmorPiece; }

    public final void setOldArmorPiece(final ItemStack oldArmorPiece) { this.oldArmorPiece = oldArmorPiece; }

    public final ItemStack getNewArmorPiece() { return  newArmorPiece; }

    public final void setNewArmorPiece(final ItemStack newArmorPiece) { this.oldArmorPiece = newArmorPiece; }

    public EquipMethod getMethod() { return equipType; }

    public enum EquipMethod {
        SHIFT_CLICK,
        DRAG,
        PICK_DROP,
        HOTBAR,
        HOTBAR_SWAP,
        DISPENSER,
        BROKE,
        DEATH
    }

}
