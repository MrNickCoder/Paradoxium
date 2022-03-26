package com.ncoder.paradoxium;

import com.ncoder.paradoxium.items.ParadoxItems;
import com.ncoder.paradoxium.utils.Utils;
import com.ncoder.paradoxlib.core.ParadoxAddon;
import com.ncoder.paradoxlib.metrics.bukkit.Metrics;
import com.ncoder.paradoxlib.metrics.charts.SimplePie;

public class Paradoxium extends ParadoxAddon {

    public Paradoxium() {
        super("MrNickCoder", "Paradoxium", "master", "auto-update");
    }

    @Override
    protected void Enable() {
        /* Printing */
        Utils.log("**************************************");
        Utils.log("**                                  **");
        Utils.log("**          [ Paradoxium ]          **");
        Utils.log("**       Created by NickCoder       **");
        Utils.log("**                                  **");
        Utils.log("**************************************");

        Metrics metrics = new Metrics(this, 14752);
        metrics.addCustomChart(new SimplePie("version", () -> getDescription().getVersion()));
        metrics.addCustomChart(new SimplePie("auto_updates", () -> String.valueOf(autoUpdatesEnabled())));

        /* Register Items */
        ParadoxItems.setup(this);
    }

    @Override
    protected void Disable() {

    }

}
