package com.momilk.entity;

import com.momilk.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class SpoiledHogMilkArrow extends AbstractArrow {

    protected SpoiledHogMilkArrow(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public SpoiledHogMilkArrow(final Level level, final LivingEntity owner, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        super(ModEntityTypes.SPOILED_HOG_MILK_ARROW, owner, level, pickupItemStack, firedFromWeapon);
    }

    public SpoiledHogMilkArrow(final Level level, final double x, final double y, final double z, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        super(ModEntityTypes.SPOILED_HOG_MILK_ARROW, x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    protected void doPostHurtEffects(final LivingEntity mob) {
        super.doPostHurtEffects(mob);

        if(!mob.isOnFire())
        {
            mob.setRemainingFireTicks(10 * 20);
        }
        else
        {
            mob.setRemainingFireTicks((mob.getRemainingFireTicks() + 10) * 20);
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.SPOILED_HOG_MILK_ARROW);
    }
}
