package com.ncoder.paradox.enchantments;

import com.ncoder.paradox.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParadoxEnchantments {

    /* Breaking */
    public static final Enchantment TELEPATHY = new EnchantmentWrapper("telepathy", "Telepathy", 1);
    public static final Enchantment VEIN_MINER = new EnchantmentWrapper("vein_miner", "Vein Miner", 1);

    /* Fishing */
    public static final Enchantment DOUBLE = new EnchantmentWrapper("double", "Double", 1);

    public static void setup() {
        register(TELEPATHY);
        register(VEIN_MINER);

        register(DOUBLE);
    }

    public static void enchant(ItemStack itemStack, Enchantment enchantment, int lvl) {
        itemStack.addUnsafeEnchantment(enchantment, lvl);

        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + enchantment.getName() + " " + Utils.getRomanNumeral(lvl));
        if (itemMeta.hasLore())
            for(String l : itemMeta.getLore())
                lore.add(l);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    public static void register(Enchantment enchantment) {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment);

        if (!registered) registerEnchantment(enchantment);
    }

    public static void registerEnchantment(Enchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
