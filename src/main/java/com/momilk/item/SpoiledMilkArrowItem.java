package com.momilk.item;

import com.momilk.entity.ChocolateMilkArrow;
import com.momilk.entity.SpoiledMilkArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class SpoiledMilkArrowItem extends ArrowItem {
    public SpoiledMilkArrowItem(Properties properties) {
        super(properties);
    }

    public AbstractArrow createArrow(final Level level, final ItemStack itemStack, final LivingEntity owner, final @Nullable ItemStack firedFromWeapon) {
        return new SpoiledMilkArrow(level, owner, itemStack.copyWithCount(1), firedFromWeapon);
    }

    public Projectile asProjectile(final Level level, final Position position, final ItemStack itemStack, final Direction direction) {
        SpoiledMilkArrow arrow = new SpoiledMilkArrow(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1), (ItemStack)null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}
