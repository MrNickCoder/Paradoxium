package com.ncoder.paradox;

import com.ncoder.paradox.enchantments.ParadoxEnchantments;
import com.ncoder.paradox.items.ParadoxItems;
import com.ncoder.paradox.listeners.*;
import com.ncoder.paradox.utils.Categories;
import com.ncoder.paradox.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public final class Paradoxium extends JavaPlugin implements SlimefunAddon {

    private static Paradoxium instance;

    @Override
    public void onEnable() {
        instance = this;

        /* Printing */
        Utils.log("**************************************");
        Utils.log("**                                  **");
        Utils.log("**          [ Paradoxium ]          **");
        Utils.log("**       Created by NickCoder       **");
        Utils.log("**                                  **");
        Utils.log("**************************************");

        /* Read something from your config.yml */
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {

        }

        /* Register Items */
        ParadoxItems.setup(this);

        new ParadoxAPI();

        ParadoxEnchantments.setup();

        Events.registerListener(new ArmorListener());
        Events.registerListener(new DispenserArmorListener());

        Events.registerListener(new Listeners());
        Events.registerListener(new ShardGearListener());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerEvents(Listener listener) { getServer().getPluginManager().registerEvents(listener, this); }

    @Nonnull
    public static Paradoxium get() { return instance; }

    @Override
    public JavaPlugin getJavaPlugin() { return this; }

    @Override
    public String getBugTrackerURL() { return null; }
}
