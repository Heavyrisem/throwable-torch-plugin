package com.github.heavyrisem.throwable_torch.listener;

import com.github.heavyrisem.throwable_torch.Constant;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ThrowEventListener extends EventListener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        if (item.getItemStack().getType() == Material.TORCH && item.getItemStack().getAmount() == 1) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            ItemStack itemStack = event.getItemDrop().getItemStack();
            itemStack.setAmount(itemStack.getAmount() - 1);

            Arrow arrow = player.launchProjectile(Arrow.class);
            arrow.setDamage(0.2);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
            arrow.setVelocity(player.getLocation().getDirection().multiply(5));
            arrow.setGlowing(true);
            arrow.setMetadata(Constant.TORCH_ARROW_META_KEY, new FixedMetadataValue(plugin, true));
        }
    }
}
