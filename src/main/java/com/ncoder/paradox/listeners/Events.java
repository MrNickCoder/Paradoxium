package com.ncoder.paradox.listeners;

import com.ncoder.paradox.Paradoxium;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
public final class Events implements Listener {

    private static final Listener LISTENER = new Events();

    public static <T extends Event> T call(T event) {
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Paradoxium.get());
    }

    public static <T extends Event> void addHandler(Class<T> eventClass, EventPriority priority, boolean ignoreCancelled, Consumer<T> handler) {
        Bukkit.getPluginManager().registerEvent(eventClass, LISTENER, priority,
                (listener, event) -> handler.accept((T) event), Paradoxium.get(), ignoreCancelled);
    }
}
