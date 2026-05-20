package com.momilk.item;

import com.momilk.entity.CerealArrow;
import com.momilk.entity.HotChocolateArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class HotChocolateArrowItem extends ArrowItem {
    public HotChocolateArrowItem(Properties properties) {
        super(properties);
    }

    public AbstractArrow createArrow(final Level level, final ItemStack itemStack, final LivingEntity owner, final @Nullable ItemStack firedFromWeapon) {
        return new HotChocolateArrow(level, owner, itemStack.copyWithCount(1), firedFromWeapon);
    }

    public Projectile asProjectile(final Level level, final Position position, final ItemStack itemStack, final Direction direction) {
        HotChocolateArrow arrow = new HotChocolateArrow(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1), (ItemStack)null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}
