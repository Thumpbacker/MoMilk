package com.momilk.item;

import com.momilk.entity.HogMilkArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class HogMilkArrowItem extends ArrowItem {
    public HogMilkArrowItem(Properties properties) {
        super(properties);
    }

    public AbstractArrow createArrow(final Level level, final ItemStack itemStack, final LivingEntity owner, final @Nullable ItemStack firedFromWeapon) {
        return new HogMilkArrow(level, owner, itemStack.copyWithCount(1), firedFromWeapon);
    }

    public Projectile asProjectile(final Level level, final Position position, final ItemStack itemStack, final Direction direction) {
        HogMilkArrow arrow = new HogMilkArrow(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1), (ItemStack)null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}
