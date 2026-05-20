package com.momilk.entity;

import com.momilk.item.ModItems;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class HotChocolateArrow extends AbstractArrow {

    protected HotChocolateArrow(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public HotChocolateArrow(final Level level, final LivingEntity owner, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        super(ModEntityTypes.HOT_CHOCOLATE_ARROW, owner, level, pickupItemStack, firedFromWeapon);
    }

    public HotChocolateArrow(final Level level, final double x, final double y, final double z, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
        super(ModEntityTypes.HOT_CHOCOLATE_ARROW, x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    protected void doPostHurtEffects(final LivingEntity mob) {
        super.doPostHurtEffects(mob);

        for (int i = 0; i < mob.getActiveEffectsMap().size(); i++) {
            var effect = mob.getActiveEffectsMap().values().stream().toList().get(i).getEffect();

            if(effect.value().getCategory() == MobEffectCategory.NEUTRAL)
            {
                mob.removeEffect(effect);
            }
        }

        if(mob instanceof Player player)
        {
            player.getFoodData().eat(5, 0.5F);
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.HOT_CHOCOLATE_ARROW);
    }
}
