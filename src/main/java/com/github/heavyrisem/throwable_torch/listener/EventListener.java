package com.github.heavyrisem.throwable_torch.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class EventListener implements Listener {
    protected Plugin plugin;

    public void setPlugin(Plugin plugin) { this.plugin = plugin; }
}
