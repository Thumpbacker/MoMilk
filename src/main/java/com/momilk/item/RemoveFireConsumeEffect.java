package com.momilk.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public record RemoveFireConsumeEffect() implements ConsumeEffect {

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return null;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {

        if(user.isOnFire())
        {
            user.setRemainingFireTicks(0);
            return true;
        }

        return false;
    }
}
