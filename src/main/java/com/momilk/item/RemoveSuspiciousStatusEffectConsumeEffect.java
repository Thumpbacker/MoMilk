package com.momilk.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

import java.util.Objects;

public record RemoveSuspiciousStatusEffectConsumeEffect() implements ConsumeEffect {

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return null;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {

        var effects = stack.get(DataComponents.SUSPICIOUS_STEW_EFFECTS).effects();

        if(!user.getActiveEffects().isEmpty())
        {
            for(int i = 0; i < effects.size(); i++)
            {
                user.removeEffect(effects.get(i).effect());
            }

            return true;
        }

        return false;
    }
}
