package com.ncoder.paradox.items.materials;

import com.ncoder.paradox.Paradoxium;
import com.ncoder.paradox.items.ParadoxItems;
import com.ncoder.paradox.listeners.Events;
import com.ncoder.paradox.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shards implements Listener {

    public static List<SlimefunItemStack> SHARDS = new ArrayList<>();

    public static final SlimefunItemStack SHARD_SLOT = new SlimefunItemStack(
            "SHARD_SLOT",
            Material.PAPER,
            "&bShard Slot Expander",
            "",
            "&7Expand your gear shard slots."
    );

    public static final SlimefunItemStack EARTH_SHARD = new SlimefunItemStack(
            "EARTH_SHARD",
            Material.BROWN_DYE,
            "&6Shard &7&l[&x&c&1&9&a&6&b&lEarth&7&l]",
            "",
            "&7Came from the earth's core,",
            "&7Harder than titanium."
    );

    public static final SlimefunItemStack FIRE_SHARD = new SlimefunItemStack(
            "FIRE_SHARD",
            Material.RED_DYE,
            "&6Shard &7&l[&x&f&f&5&7&3&3&lFire&7&l]",
            "",
            "&7A rare fire substance that is mined",
            "&7from the netherworld."
    );

    public static final SlimefunItemStack WATER_SHARD = new SlimefunItemStack(
            "WATER_SHARD",
            Material.LIGHT_BLUE_DYE,
            "&6Shard &7&l[&x&0&0&c&e&d&1&lWater&7&l]",
            "",
            "&7Poseidon fragment, stolen at the",
            "&7sacred area of the Atlantis."
    );

    public static final SlimefunItemStack AIR_SHARD = new SlimefunItemStack(
            "AIR_SHARD",
            Material.WHITE_DYE,
            "&6Shard &7&l[&x&f&5&f&5&f&5&lAir&7&l]",
            "",
            "&7This empty space there has to be",
            "&7something in here."
    );

    public static final SlimefunItemStack NATURE_SHARD = new SlimefunItemStack(
            "NATURE_SHARD",
            Material.LIME_DYE,
            "&6Shard &7&l[&x&9&0&e&e&9&0&lNature&7&l]",
            "",
            "&7Did mother nature bestowed something",
            "&7to me?"
    );

    public static final SlimefunItemStack LIGHTNING_SHARD = new SlimefunItemStack(
            "LIGHTNING_SHARD",
            Material.ORANGE_DYE,
            "&6Shard &7&l[&x&f&f&7&f&5&0&lLightning&7&l]",
            "",
            "&7I heard a loud noise of thunder and found",
            "&7this item."
    );

    public static final SlimefunItemStack LIGHT_SHARD = new SlimefunItemStack(
            "LIGHT_SHARD",
            Material.YELLOW_DYE,
            "&6Shard &7&l[&x&f&a&f&a&d&2&lLight&7&l]",
            "",
            "&7This yellow thing is a fragment light",
            "&7of the sun."
    );

    public static final SlimefunItemStack VOID_SHARD = new SlimefunItemStack(
            "VOID_SHARD",
            Material.BLACK_DYE,
            "&6Shard &7&l[&x&6&9&6&9&6&9&lVoid&7&l]",
            "",
            "&7This void shard came from the void",
            "&7monster deep in the end."
    );

    public static final SlimefunItemStack SPIRIT_SHARD = new SlimefunItemStack(
            "SPIRIT_SHARD",
            Material.GRAY_DYE,
            "&6Shard &7&l[&x&d&c&d&c&d&c&lSpirit&7&l]",
            "",
            "&7Oh spirit, Did you just given me a",
            "&7rare item composed of spirits."
    );

    public static final SlimefunItemStack ANCIENT_SHARD = new SlimefunItemStack(
            "ANCIENT_SHARD",
            Material.PURPLE_DYE,
            "&6Shard &7&l[&x&9&9&3&2&c&c&lAncient&7&l]",
            "",
            "&7This thing came from the ancient",
            "&7civilization."
    );

    public static final SlimefunItemStack SACRED_SHARD = new SlimefunItemStack(
            "SACRED_SHARD",
            Material.BLUE_DYE,
            "&6Shard &7&l[&x&7&b&6&8&e&e&lSacred&7&l]",
            "",
            "&7A pure sacred item should be blue."
    );

    public static final SlimefunItemStack MYSTERY_SHARD = new SlimefunItemStack(
            "MYSTERY_SHARD",
            Material.MAGENTA_DYE,
            "&6Shard &7&l[&x&b&a&5&5&d&3&lMystery&7&l]",
            "",
            "&7?????",
            "&7???"
    );

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFishing(PlayerFishEvent e) {
        if (Utils.isCreative(e.getPlayer()) || Utils.isSpectator(e.getPlayer())) return;
        if (isDisabled(WATER_SHARD)) return;
        if (!(e.getCaught() instanceof Item)) return;
        Item item = (Item) e.getCaught();
        Random random = new Random();
        int result = random.nextInt(100);
        if (result >= 0 && result < 50) {
            item.setItemStack(generateShard(WATER_SHARD));
        }
    }

    public Shards(Paradoxium plugin) {
        registerShard(EARTH_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(FIRE_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(WATER_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(AIR_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(NATURE_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(LIGHTNING_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(LIGHT_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(VOID_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(SPIRIT_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(ANCIENT_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(SACRED_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});
        registerShard(MYSTERY_SHARD, new ItemStack[] {null, null, null, null, null, null, null, null, null});

        Events.registerListener(this);
    }

    public static ItemStack generateShard(SlimefunItemStack itemStack) {
        Random random = new Random();

        final ItemStack item = itemStack.clone();
        ItemMeta meta = item.getItemMeta();

        int result = random.nextInt(100);
        if (result == 99) {
            meta = generateEffect(meta, 3);
        } else if (result >= 94) {
            meta = generateEffect(meta, 2);
        } else if (result >= 69) {
            meta = generateEffect(meta, 1);
        }

        item.setItemMeta(meta);

        return item;
    }

    public static ItemMeta generateEffect(ItemMeta itemMeta, int amount) {
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        Enchantment[] possible_enchants = Enchantment.values();
        List<String> lore = new ArrayList<>();

        if (itemMeta.hasLore()) {
            for (String l : itemMeta.getLore()) {
                lore.add(l);
            }
        }

        lore.add("");
        lore.add("§5§lEffects");

        for (int i = 0; i < amount; i++) {
            Random random = new Random();
            Enchantment result = possible_enchants[random.nextInt(possible_enchants.length)];

            String key = result.getKey().getKey();
            int lvl = result.getMaxLevel() == 1 ? 1 : random.nextInt(result.getMaxLevel() - 1) + 1;

            lore.add("§a+" + lvl + " " + Utils.getEnchantDisplayName(result.getName()));

            pdc.set(new NamespacedKey(Paradoxium.get(), "effect-" + (i + 1) + "-name"), PersistentDataType.STRING, key);
            pdc.set(new NamespacedKey(Paradoxium.get(), "effect-" + (i + 1) + "-lvl"), PersistentDataType.INTEGER, lvl);

        }

        itemMeta.setLore(lore);

        return itemMeta;
    }

    public static void registerShard(SlimefunItemStack itemStack, ItemStack[] recipe) {
        if (isDisabled(itemStack)) return;

        SHARDS.add(itemStack);
        ParadoxItems.registerNull(itemStack, recipe);
    }


    public static boolean isShard(ItemStack itemStack) {
        for (SlimefunItemStack item : SHARDS) {
            if (SlimefunUtils.isItemSimilar(itemStack, item, false)) return true;
        }
        return false;
    }

    public static boolean isDisabled(SlimefunItemStack itemStack) {
        List<String> shards = Paradoxium.get().getConfig().getStringList("shards.disabled");

        if (!shards.contains(itemStack.getItemId())) return false;
        return true;
    }
}
