package com.github.heavyrisem.throwable_torch;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class ThrowEventListener implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        if (item.getType().toString().equals("TORCH")) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            ItemStack itemStack = event.getItemDrop().getItemStack();
            itemStack.setAmount(0);

            Arrow arrow = player.launchProjectile(Arrow.class);
            arrow.setVelocity(player.getLocation().getDirection().multiply(5));
            arrow.setGlowing(true);
            arrow.setMetadata(Constant.TORCH_ARROW_META_KEY, new FixedMetadataValue());
        }
    }
}
