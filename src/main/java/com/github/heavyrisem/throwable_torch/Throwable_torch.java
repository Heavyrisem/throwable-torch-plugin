package com.github.heavyrisem.throwable_torch;

import com.github.heavyrisem.throwable_torch.listener.ArrowEventListener;
import com.github.heavyrisem.throwable_torch.listener.ThrowEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Throwable_torch extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ThrowAbleTorch Activate");

        ThrowEventListener throwEventListener = new ThrowEventListener();
        throwEventListener.setPlugin(this);

        ArrowEventListener arrowEventListener = new ArrowEventListener();
        arrowEventListener.setPlugin(this);

        getServer().getPluginManager().registerEvents(throwEventListener, this);
        getServer().getPluginManager().registerEvents(arrowEventListener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("ThrowAbleTorch DeActivate");
    }
}
