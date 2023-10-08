package com.github.heavyrisem.throwable_torch.listener;

import com.github.heavyrisem.throwable_torch.Constant;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Objects;

public class ArrowEventListener extends EventListener {
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow arrow) {
            if (!arrow.hasMetadata(Constant.TORCH_ARROW_META_KEY)) return;

            if (arrow.getShooter() instanceof Player) {
                Block hitBlock = event.getHitBlock();

                BlockFace hitBlockFace = event.getHitBlockFace();
                if (hitBlock != null && hitBlockFace != null) {
                    Block nearBlock = hitBlock.getRelative(hitBlockFace);


                    if (hitBlock.getType().isSolid() && nearBlock.getType().isAir() && !hitBlockFace.equals(BlockFace.DOWN)) {
                        if (hitBlockFace.equals(BlockFace.UP)) {
                            nearBlock.setType(Material.TORCH);
                        } else {
                            nearBlock.setType(Material.WALL_TORCH);
                            Directional directionalBlock = (Directional) nearBlock.getState().getBlockData();
                            directionalBlock.setFacing(hitBlockFace);
                            nearBlock.setBlockData(directionalBlock);
                        }
                    } else {
                        World world = Objects.requireNonNull(event.getHitBlock()).getWorld();
                        Entity droppedItem = world.dropItem(event.getHitBlock().getLocation(), new ItemStack(Material.TORCH));
                        droppedItem.setVelocity(new Vector(0, 1, 0));

                        Location location = event.getHitBlock().getLocation();
                        location.getWorld().playSound(location, Sound.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, 0.1f, 1.0f);
                    }
                }
            }
            arrow.remove();
        }
    }
}
