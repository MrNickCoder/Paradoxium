package com.ncoder.paradoxium.utils;

import com.ncoder.paradoxium.Paradoxium;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public final class Utils {

    static CommandSender console = Bukkit.getConsoleSender();

    public static void sendMessage(Player p, String message) {
        p.sendMessage("§7[" + Constants.CHAT_PARADOXIUM + "§7] §f" + ChatColors.color(message));
    }

    public static void log(String message) { console.sendMessage(message); }

    public static void log(int message) { console.sendMessage(String.valueOf(message)); }

    public static void info(String message) {
        Paradoxium.instance().getLogger().info(message);
    }

    public static void info(int message) { info(String.valueOf(message)); }

    public static void debug(String message) { Paradoxium.instance().getLogger().log(Level.OFF, "[Paradox-DEBUG]: " + message); }

    public static void debug(int message) { debug(String.valueOf(message)); }

    public static String getRomanNumeral(int number) {
        String roman = "";
        int remaining = number;
        while (remaining != 0) {
            int c = 0;
            String r = "";

            if (c == 0) {
                if (remaining / 100 != 0) {
                    c = remaining / 100;
                    remaining -= (100 * c);
                    r = "C";
                } else if (remaining / 50 != 0) {
                    c = remaining / 50;
                    remaining -= (50 * c);
                    r = "L";
                } else if (remaining / 10 != 0) {
                    c = remaining / 10;
                    remaining -= (10 * c);
                    r = "X";
                } else if (remaining > 0 && remaining < 4) {
                    c = remaining;
                    remaining = 0;
                    r = "I";
                } else {
                    remaining = 0;
                    switch(remaining) {
                        case 4: roman += "IV"; break;
                        case 5: roman += "V"; break;
                        case 6: roman += "VI"; break;
                        case 7: roman += "VII"; break;
                        case 8: roman += "VIII"; break;
                        case 9: roman += "IX"; break;
                    }
                }
            }

            if (c != 0 && !r.equals("")) {
                for (int i = c; i > 0; i--) {
                    roman += r;
                }
            }
        }
        return roman;
    }

    public static String getEnchantDisplayName(String name) {
        switch(name) {
            case "ARROW_DAMAGE": return "Power";
            case "ARROW_FIRE": return "Flame";
            case "ARROW_INFINITE": return "Infinity";
            case "ARROW_KNOCKBACK": return "Punch";
            case "BINDING_CURSE": return "Curse of Binding";
            case "DAMAGE_ALL": return "Sharpness";
            case "DAMAGE_ARTHROPODS": return "Bane of Arthropods";
            case "DAMAGE_UNDEAD": return "Smite";
            case "DEPTH_STRIDER": return "Depth Strider";
            case "DIG_SPEED": return "Efficiency";
            case "DURABILITY": return "Unbreaking";
            case "FIRE_ASPECT": return "Fire Aspect";
            case "FROST_WALKER": return "Frost Walker";
            case "KNOCKBACK": return "Knockback";
            case "LOOT_BONUS_BLOCKS": return "Fortune";
            case "LOOT_BONUS_MOBS": return "Looting";
            case "LUCK": return "Luck of the Sea";
            case "LURE": return "Lure";
            case "MENDING": return "Mending";
            case "OXYGEN": return "Respiration";
            case "PROTECTION_ENVIRONMENTAL": return "Protection";
            case "PROTECTION_EXPLOSIONS": return "Blast Protection";
            case "PROTECTION_FALL": return "Feather Falling";
            case "PROTECTION_FIRE": return "Fire Protection";
            case "PROTECTION_PROJECTILE": return "Projectile Protection";
            case "SILK_TOUCH": return "Silk Touch";
            case "SWEEPING_EDGE": return "Sweeping Edge";
            case "THORNS": return "Thorns";
            case "VANISHING_CURSE": return "Curse of Vanishing";
            case "WATER_WORKER": return "Aqua Affinity";
            default: return "Unknown";
        }
    }

    public static boolean isGear(ItemStack itemStack) { return isGear(itemStack.getType()); }
    public static boolean isGear(Material material) {
        if (isArmor(material) || isWeapon(material) || isTool(material)) return true;
        return false;
    }

    public static boolean isArmor(ItemStack itemStack) { return isArmor(itemStack.getType()); }
    public static boolean isArmor(Material material) { return material.toString().endsWith("_HELMET") || material.toString().endsWith("_SKULL") || material.toString().endsWith("_HEAD") || material.toString().equals("ELYTRA") || material.toString().endsWith("_CHESTPLATE") || material.toString().endsWith("_LEGGINGS") || material.toString().endsWith("_BOOTS"); }

    public static boolean isWeapon(ItemStack itemStack) { return isWeapon(itemStack.getType()); }
    public static boolean isWeapon(Material material) { return material.toString().endsWith("_SWORD") || material.toString().endsWith("_AXE") || material.toString().endsWith("BOW"); }

    public static boolean isTool(ItemStack itemStack) { return isTool(itemStack.getType()); }
    public static boolean isTool(Material material) { return material.toString().endsWith("_AXE") || material.toString().endsWith("_PICKAXE") || material.toString().endsWith("_SHOVEL") || material.toString().endsWith("_HOE") || material.toString().equals("FISHING_ROD"); }

    public static boolean isCreative(Player player) { return player.getGameMode() == GameMode.CREATIVE; }
    public static boolean isSurvival(Player player) { return player.getGameMode() == GameMode.SURVIVAL; }
    public static boolean isAdventure(Player player) { return player.getGameMode() == GameMode.ADVENTURE; }
    public static boolean isSpectator(Player player) { return player.getGameMode() == GameMode.SPECTATOR; }

}
