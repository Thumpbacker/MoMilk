package com.momilk.entity;

import com.momilk.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class CerealArrow extends AbstractArrow {

    protected CerealArrow(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public CerealArrow(final Level level, final LivingEntity owner, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        super(ModEntityTypes.CEREAL_ARROW, owner, level, pickupItemStack, firedFromWeapon);
    }

    public CerealArrow(final Level level, final double x, final double y, final double z, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        super(ModEntityTypes.CEREAL_ARROW, x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    protected void doPostHurtEffects(final LivingEntity mob) {
        super.doPostHurtEffects(mob);

        if(mob instanceof Player player)
        {
            player.getFoodData().eat(20, 2F);
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.MILK_ARROW);
    }
}
